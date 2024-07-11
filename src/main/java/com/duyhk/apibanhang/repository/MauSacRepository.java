package com.duyhk.apibanhang.repository;

import com.duyhk.apibanhang.entity.LoaiSanPham;
import com.duyhk.apibanhang.entity.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MauSacRepository extends JpaRepository<MauSac, Long> {
}
