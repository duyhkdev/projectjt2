package com.duyhk.apibanhang.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamDTO {
    private Long id;
    private String ma;
    private String ten;
    private Long gia;
    private Long soLuongTonKho;
    private Long soLuongDaBan;
    private String moTa;
    private Integer trangThai;

    private LoaiSanPhamDTO loaiSanPham;

    private List<String> images;

    // MultipartFile hứng cái file mà người dùng tải lên
    @JsonIgnore
    private List<MultipartFile> files;
}
