package com.duyhk.apibanhang.controller;

import com.duyhk.apibanhang.dto.HoaDonChiTietTaiQuayDTO;
import com.duyhk.apibanhang.service.BanHangTaiQuayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tai-quay")
public class BanHangTaiQuayController {

    private final BanHangTaiQuayService taiQuayService;

    @PostMapping("/tao-hoa-don")
    public ResponseEntity<String> taoHoadon(){
        return taiQuayService.taoHoadon();
    }

    @PostMapping("/them-vao-gio")
    public ResponseEntity<String> themVaoGioHang(@RequestBody HoaDonChiTietTaiQuayDTO dto){
        return taiQuayService.themSanPhamVaoGioHang(dto);
    }
}
