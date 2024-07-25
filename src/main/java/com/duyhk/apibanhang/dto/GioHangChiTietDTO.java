package com.duyhk.apibanhang.dto;

import com.duyhk.apibanhang.entity.GioHang;
import com.duyhk.apibanhang.entity.SanPhamChiTiet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GioHangChiTietDTO {
    private Long id;
    private Long soLuong; //
    private Long thanhTien;
    private SanPhamChiTietDTO sanPhamChiTiet;
    private Long gioHangId;
}
