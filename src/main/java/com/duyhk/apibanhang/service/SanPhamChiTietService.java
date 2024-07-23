package com.duyhk.apibanhang.service;

import com.duyhk.apibanhang.dto.SanPhamChiTietDTO;
import com.duyhk.apibanhang.dto.SanPhamDTO;

import java.io.IOException;
import java.util.List;

public interface SanPhamChiTietService {
    List<SanPhamChiTietDTO> getAll();

    SanPhamChiTietDTO getById(Long id);

    void create(SanPhamChiTietDTO dto) throws IOException;

    void update(SanPhamChiTietDTO dto, Long id) throws IOException;

    void delete(Long id);
}
