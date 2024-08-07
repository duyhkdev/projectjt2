package com.duyhk.apibanhang.repository;

import com.duyhk.apibanhang.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HoaDonRepository extends JpaRepository<HoaDon, Long> {
    Optional<HoaDon> findByIdAndTrangThai(Long id, Integer status);
}
