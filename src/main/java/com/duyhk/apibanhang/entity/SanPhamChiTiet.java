package com.duyhk.apibanhang.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ma;
    private String ten;
    private Long gia;
    private Long soLuongTonKho;
    private Long soLuongDaBan;
    private Integer trangThai;

    @ManyToOne
    private SanPham sanPham;
    @ManyToOne
    private MauSac mauSac;
    @ManyToOne
    private KichCo kichCo;
}
