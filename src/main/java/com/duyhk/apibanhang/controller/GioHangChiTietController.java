package com.duyhk.apibanhang.controller;

import com.duyhk.apibanhang.dto.GioHangChiTietDTO;
import com.duyhk.apibanhang.dto.ResponseDTO;
import com.duyhk.apibanhang.service.GioHangChiTietService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/{id}")
    public ResponseDTO<String> update(@RequestParam Long soLuong, @PathVariable Long id){
        gioHangChiTietService.update(soLuong, id);
        return ResponseDTO.<String>builder()
                .message("Sua gio hang thanh cong")
                .status(200)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseDTO<String> delete( @PathVariable Long id){
        gioHangChiTietService.delete(id);
        return ResponseDTO.<String>builder()
                .message("Xóa khoi gio hang thanh cong")
                .status(200)
                .build();
    }
}
