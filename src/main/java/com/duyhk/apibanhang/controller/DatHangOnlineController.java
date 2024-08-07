package com.duyhk.apibanhang.controller;

import com.duyhk.apibanhang.dto.HoaDonDTO;
import com.duyhk.apibanhang.dto.ThongTinHoaDonDTO;
import com.duyhk.apibanhang.service.DatHangOnlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dat-hang")
@RequiredArgsConstructor
public class DatHangOnlineController {
    private final DatHangOnlineService datHangOnlineService;
    @GetMapping("/{id}")
    public ResponseEntity<ThongTinHoaDonDTO> getById(@PathVariable Long id){
        return datHangOnlineService.getById(id);
    }
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody HoaDonDTO dto){
        return datHangOnlineService.create(dto);
    }
    // localhost:8080/api/dat-hang/update-status?id=1&status=2
    // localhost:8080/api/dat-hang/update-status/2/1
    @PutMapping("/update-status/{id}/{status}")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @PathVariable Integer status){
        return datHangOnlineService.updateStatus(id, status);
    }
}
