package com.duyhk.apibanhang.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ma;
    private String ten;
    private Long gia;
    private Long soLuongTonKho;
    private Long soLuongDaBan;
    private String moTa;
    private Integer trangThai;

    @ManyToOne
    @JoinColumn(name = "loai_san_pham_id")
    private LoaiSanPham loaiSanPham;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "anh_san_pham")
    private List<String> images; // 3 bản ghi
}
