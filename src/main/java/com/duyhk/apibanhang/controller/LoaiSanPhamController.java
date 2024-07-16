package com.duyhk.apibanhang.controller;

import com.duyhk.apibanhang.dto.KichCoDTO;
import com.duyhk.apibanhang.dto.LoaiSanPhamDTO;
import com.duyhk.apibanhang.dto.MauSacDTO;
import com.duyhk.apibanhang.dto.ResponseDTO;
import com.duyhk.apibanhang.service.KichCoService;
import com.duyhk.apibanhang.service.LoaiSanPhamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loai-san-pham")
@RequiredArgsConstructor
public class LoaiSanPhamController {

    // controller -> service -> repository -> thao tác với csdl
    // constructor , setter, interface
    private final LoaiSanPhamService loaiSanPhamService ;

    @GetMapping
    public ResponseDTO<List<LoaiSanPhamDTO>> getAll() {
        ResponseDTO<List<LoaiSanPhamDTO>> responseDTO = new ResponseDTO<>();
        responseDTO.setData(loaiSanPhamService.getAll());
        responseDTO.setStatus(200);
        return responseDTO;
    }

    @GetMapping("/{id}")
    public ResponseDTO<LoaiSanPhamDTO> getById(@PathVariable Long id) {
        ResponseDTO<LoaiSanPhamDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setData(loaiSanPhamService.getById(id));
        responseDTO.setStatus(200);
        return responseDTO;
    }

    @PostMapping
    public ResponseDTO<Void> create(@RequestBody @Valid LoaiSanPhamDTO dto){// tên: xanh { id: null, ten: null }
        loaiSanPhamService.create(dto);
        return ResponseDTO.<Void>builder()
                .status(201)
                .message("Tao thanh cong ")
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDTO<Void> update(@PathVariable  Long id, @RequestBody @Valid LoaiSanPhamDTO dto){ // update mau_sac set ten = 'Tím' where id = 1
        loaiSanPhamService.update(dto, id);
        return ResponseDTO.<Void>builder()
                .status(201)
                .message("Sửa thanh cong ")
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseDTO<Void> delete(@PathVariable Long id){
        loaiSanPhamService.delete(id);
        return ResponseDTO.<Void>builder()
                .status(201)
                .message("Xóa thanh cong")
                .build();
    }

}
