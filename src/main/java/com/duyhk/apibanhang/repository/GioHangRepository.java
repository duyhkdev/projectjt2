package com.duyhk.apibanhang.repository;

import com.duyhk.apibanhang.entity.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GioHangRepository extends JpaRepository<GioHang, Long> {
    Optional<GioHang> findByTaiKhoanId(Long id);
}
