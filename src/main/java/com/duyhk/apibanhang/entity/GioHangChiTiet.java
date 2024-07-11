package com.duyhk.apibanhang.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GioHangChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long soLuong; //
    private Long thanhTien;

    @ManyToOne
    @JoinColumn(name = "san_pham_chi_tiet_id")
    private SanPhamChiTiet sanPhamChiTiet; // sp a giá 10 nghìn số lượng 20

    @ManyToOne
    @JoinColumn(name = "gio_hang_id")
    private GioHang gioHang;

}
