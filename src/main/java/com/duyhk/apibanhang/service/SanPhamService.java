package com.duyhk.apibanhang.service;

import com.duyhk.apibanhang.dto.MauSacDTO;
import com.duyhk.apibanhang.dto.SanPhamDTO;

import java.io.IOException;
import java.util.List;

public interface SanPhamService {
    List<SanPhamDTO> getAll();

    SanPhamDTO getById(Long id);

    void create(SanPhamDTO dto) throws IOException;

    void update(SanPhamDTO dto, Long id);

    void delete(Long id);
}
