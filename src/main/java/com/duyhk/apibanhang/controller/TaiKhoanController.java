package com.duyhk.apibanhang.controller;

import com.duyhk.apibanhang.dto.ResponseDTO;
import com.duyhk.apibanhang.dto.TaiKhoanDTO;
import com.duyhk.apibanhang.service.TaiKhoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tai-khoan")
@RequiredArgsConstructor
public class TaiKhoanController {

    private final TaiKhoanService taiKhoanService;

    @PostMapping
    public ResponseDTO<String> create(@RequestBody TaiKhoanDTO dto){
        taiKhoanService.create(dto);
        return ResponseDTO.<String>builder()
                .message("Tao tai khoan thanh cong")
                .status(200)
                .build();
    }
}
