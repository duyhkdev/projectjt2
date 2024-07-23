package com.duyhk.apibanhang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamChiTietDTO {
    private Long id;
    private String ma;
    private String ten;
    private Long gia;
    private Long soLuongTonKho;
    private Long soLuongDaBan;
    private Integer trangThai;
    private SanPhamDTO sanPham;
    private MauSacDTO mauSac;
    private KichCoDTO kichCo;
}
