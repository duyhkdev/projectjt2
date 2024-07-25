package com.duyhk.apibanhang.service;

import com.duyhk.apibanhang.dto.GioHangChiTietDTO;
import com.duyhk.apibanhang.dto.TaiKhoanDTO;

import java.util.List;

public interface GioHangChiTietService {
    List<GioHangChiTietDTO> getAll(Integer page, Integer size);

    GioHangChiTietDTO getById(Long id);

    void create(GioHangChiTietDTO dto);

    void update(GioHangChiTietDTO dto, Long id );
}
