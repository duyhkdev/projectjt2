package com.duyhk.apibanhang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThongTinHoaDonDTO {
    private HoaDonDTO hoaDon;
    private List<HoaDonChiTietDTO> hoaDonChiTietList;
}
