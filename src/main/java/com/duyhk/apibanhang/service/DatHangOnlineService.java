package com.duyhk.apibanhang.service;

import com.duyhk.apibanhang.dto.HoaDonDTO;
import com.duyhk.apibanhang.dto.ThongTinHoaDonDTO;
import org.springframework.http.ResponseEntity;

public interface DatHangOnlineService {
    ResponseEntity<ThongTinHoaDonDTO> getById(Long id);

    ResponseEntity<String> create(HoaDonDTO dto);

    ResponseEntity<String> updateStatus(Long id, Integer trangThai);
}
