package com.duyhk.apibanhang.repository;

import com.duyhk.apibanhang.entity.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, Long> {

    // select * from gio_hang_chi_tiet where gio_hang_id = ? and san_pham_chi_tiet_id = ?
    Optional<GioHangChiTiet> findByGioHangIdAndSanPhamChiTietId(Long gioHangId, Long spctId);
}
