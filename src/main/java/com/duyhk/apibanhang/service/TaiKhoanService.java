package com.duyhk.apibanhang.service;

import com.duyhk.apibanhang.dto.TaiKhoanDTO;

import java.util.List;

public interface TaiKhoanService {

    List<TaiKhoanDTO> getAll(Integer page, Integer size);

    TaiKhoanDTO getById(Long id);

    void create(TaiKhoanDTO dto);

    void update(TaiKhoanDTO dto, Long id );

}
