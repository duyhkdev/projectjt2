package com.duyhk.apibanhang.service;

import com.duyhk.apibanhang.dto.HoaDonDTO;
import org.springframework.http.ResponseEntity;

public interface DatHangOnlineService {
    ResponseEntity<String> create(HoaDonDTO dto);
}
