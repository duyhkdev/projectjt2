package com.duyhk.apibanhang.controller;

import com.duyhk.apibanhang.dto.MauSacDTO;
import com.duyhk.apibanhang.dto.ResponseDTO;
import com.duyhk.apibanhang.dto.SanPhamDTO;
import com.duyhk.apibanhang.service.SanPhamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @PostMapping
    public ResponseDTO<Void> create(@ModelAttribute @Valid SanPhamDTO dto) throws IOException {// tên: xanh { id: null, ten: null }
        sanPhamService.create(dto);
        return ResponseDTO.<Void>builder()
                .status(201)
                .message("Tao thanh cong")
                .build();
    }
}
