package com.duyhk.apibanhang.repository;

import com.duyhk.apibanhang.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SanPhamRepository extends JpaRepository<SanPham, Long> {



    //tìm kiếm theo tên và mã
    @Query("""
        select s from SanPham s 
        where (:ten is null or s.ten like concat('%',:ten,'%'))
        and (:ma is null or s.ma like concat('%',:ma,'%'))
        and (:idLoaiSanPham is null or s.loaiSanPham.id = :idLoaiSanPham)
    """)
    Page<SanPham> getAll(@Param("ten") String ten,
                         @Param("ma") String ma,
                         @Param("idLoaiSanPham") Long idLoaiSanPham,
                         Pageable pageable);
}
