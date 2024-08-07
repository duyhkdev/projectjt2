package com.duyhk.apibanhang.service;

import com.duyhk.apibanhang.dto.HoaDonChiTietTaiQuayDTO;
import com.duyhk.apibanhang.dto.HoaDonDTO;
import org.springframework.http.ResponseEntity;

public interface BanHangTaiQuayService {
    ResponseEntity<String> taoHoadon();

    ResponseEntity<String> themSanPhamVaoGioHang(HoaDonChiTietTaiQuayDTO dto);
}
