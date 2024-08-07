package com.duyhk.apibanhang.repository;

import com.duyhk.apibanhang.entity.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, Long> {
    Optional<List<HoaDonChiTiet>> findByHoaDonId(Long id);
}
