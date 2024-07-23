package com.duyhk.apibanhang.service;

import com.duyhk.apibanhang.dto.MauSacDTO;
import com.duyhk.apibanhang.dto.SanPhamDTO;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface SanPhamService {
    List<SanPhamDTO> getAll();

    List<SanPhamDTO> search(String ten, String ma, Long idLoaiSanPham, Integer page, Integer size);

    SanPhamDTO getById(Long id);

    void create(SanPhamDTO dto) throws IOException;

    void update(SanPhamDTO dto, Long id) throws IOException;

    void delete(Long id);
}
