package com.duyhk.apibanhang.controller;

import com.duyhk.apibanhang.dto.GioHangChiTietDTO;
import com.duyhk.apibanhang.dto.ResponseDTO;
import com.duyhk.apibanhang.dto.TaiKhoanDTO;
import com.duyhk.apibanhang.service.GioHangChiTietService;
import com.duyhk.apibanhang.service.TaiKhoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gio-hang-chi-tiet")
@RequiredArgsConstructor
public class GioHangChiTietController {
    private final GioHangChiTietService gioHangChiTietService;

    @PostMapping
    public ResponseDTO<String> create(@RequestBody GioHangChiTietDTO dto){
        gioHangChiTietService.create(dto);
        return ResponseDTO.<String>builder()
                .message("Them vao gio hang thanh cong")
                .status(200)
                .build();
    }
}
