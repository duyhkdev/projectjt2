package com.duyhk.apibanhang.service;

import com.duyhk.apibanhang.dto.LoaiSanPhamDTO;
import com.duyhk.apibanhang.dto.MauSacDTO;

import java.util.List;

public interface LoaiSanPhamService {
    List<LoaiSanPhamDTO> getAll();

    LoaiSanPhamDTO getById(Long id);

    void create(LoaiSanPhamDTO dto);

    void update(LoaiSanPhamDTO dto, Long id);

    void delete(Long id);
}
