package com.duyhk.apibanhang.repository;

import com.duyhk.apibanhang.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatHangOnlineRepository extends JpaRepository<HoaDon, Long> {
}
