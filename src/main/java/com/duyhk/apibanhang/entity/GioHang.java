package com.duyhk.apibanhang.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GioHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long tongSanPham; // 1
    private Long tongSoTien;

    @OneToOne
    @JoinColumn(name = "tai_khoan_id")
    private TaiKhoan taiKhoan;

    public GioHang(Long tongSanPham, Long tongSoTien, TaiKhoan taiKhoan) {
        this.tongSanPham = tongSanPham;
        this.tongSoTien = tongSoTien;
        this.taiKhoan = taiKhoan;
    }
}
