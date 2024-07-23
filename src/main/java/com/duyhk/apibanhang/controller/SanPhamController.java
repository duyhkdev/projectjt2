package com.duyhk.apibanhang.controller;

import com.duyhk.apibanhang.dto.MauSacDTO;
import com.duyhk.apibanhang.dto.ResponseDTO;
import com.duyhk.apibanhang.dto.SanPhamDTO;
import com.duyhk.apibanhang.service.SanPhamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/san-pham")
public class SanPhamController {

    private final SanPhamService sanPhamService;

    @GetMapping
    public ResponseDTO<List<SanPhamDTO>> getAll() {
        ResponseDTO<List<SanPhamDTO>> responseDTO = new ResponseDTO<>();
        responseDTO.setData(sanPhamService.getAll());
        responseDTO.setStatus(200);
        return responseDTO;
    }
    @GetMapping("/tim-kiem")
    public ResponseDTO<List<SanPhamDTO>> search(
            @RequestParam(value = "ten", required = false) String ten,
            @RequestParam(value = "ma", required = false) String ma,
            @RequestParam(value = "idLoaiSanPham", required = false) Long idLoaiSanPham,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size
    ) {
        ResponseDTO<List<SanPhamDTO>> responseDTO = new ResponseDTO<>();
        responseDTO.setData(sanPhamService.search(ten,ma,idLoaiSanPham, page, size));
        responseDTO.setStatus(200);
        return responseDTO;
    }

    @PostMapping
    public ResponseDTO<Void> create(@ModelAttribute @Valid SanPhamDTO dto) throws IOException {// tên: xanh { id: null, ten: null }
        sanPhamService.create(dto);
        return ResponseDTO.<Void>builder()
                .status(201)
                .message("Tao thanh cong")
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDTO<Void> update(@PathVariable  Long id, @ModelAttribute @Valid SanPhamDTO dto) throws IOException { // update mau_sac set ten = 'Tím' where id = 1
        sanPhamService.update(dto, id);
        return ResponseDTO.<Void>builder()
                .status(201)
                .message("Sửa thanh cong")
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseDTO<Void> delete(@PathVariable Long id){
        sanPhamService.delete(id);
        return ResponseDTO.<Void>builder()
                .status(201)
                .message("Xóa thanh cong")
                .build();
    }

}
