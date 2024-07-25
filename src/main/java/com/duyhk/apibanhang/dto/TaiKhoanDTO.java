package com.duyhk.apibanhang.dto;

import com.duyhk.apibanhang.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaiKhoanDTO {
    private Long id;
    private String ma; // KH001
    private String email; // ngươi dùng tự nhập
    private String matKhau; //
    private String hoVaTen; //
    private Role role; //''
    private Long tongHoaDon; //  Khi tạo tk = 0 ->
    private Long tongTien; // Khi tạo tk = 0
    // khi tạo ra 1 tk khách hàng mặc định là kh thường
    // VIP: tongHoaDon > 75 và tongTien > 15tr
    private Integer hangTaiKhoan; // 1: KH thường, 2 KH VIP
    private Integer trangThai; // 0 - 1
}
