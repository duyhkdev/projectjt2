package com.duyhk.apibanhang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoaDonDTO {
    private Long id;
    private String maHoaDon;
    private Long tongSanPham;
    private Long tongTien;
    private String soDienThoai; // đặt hàng
    private String diaChi; // đặt hàng
    private String hoVaTen; // đặt hàng
    private String maNhanVien; // lưu lai thông tin khi bán hàng tại quầy
    private LocalDate ngayTao; // online: ngày bắt đầu đặt, khi mà nhân viên tạo hóa đơn chờ
    private LocalDate ngayHoanThanh; // online: ngày khi mà kh nhận được hàng, khi mà nhân viên thành toán okela
    private String lyDoHuy; // nếu mà hủy online thì cần nhập lý do hủy
    private Integer trangThai; // 0: đã hủy, 1: đang chờ, 2: chờ lấy hàng, 3: Đang giao hàng, 4: Đã hoàn thành
    private Integer loaiHoadon;
    private Long taiKhoanId;
}
