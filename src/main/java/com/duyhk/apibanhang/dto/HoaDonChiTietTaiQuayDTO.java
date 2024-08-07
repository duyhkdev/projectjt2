package com.duyhk.apibanhang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoaDonChiTietTaiQuayDTO {
    private Long hoaDonId;
    private Long sanPhamChiTietId;
    private Long soLuong;
}
