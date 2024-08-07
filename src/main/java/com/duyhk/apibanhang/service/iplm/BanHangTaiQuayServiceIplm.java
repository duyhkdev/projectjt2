package com.duyhk.apibanhang.service.iplm;

import com.duyhk.apibanhang.dto.HoaDonChiTietTaiQuayDTO;
import com.duyhk.apibanhang.dto.HoaDonDTO;
import com.duyhk.apibanhang.entity.*;
import com.duyhk.apibanhang.repository.HoaDonChiTietRepository;
import com.duyhk.apibanhang.repository.HoaDonRepository;
import com.duyhk.apibanhang.repository.SanPhamChiTietRepository;
import com.duyhk.apibanhang.repository.SanPhamRepository;
import com.duyhk.apibanhang.service.BanHangTaiQuayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
@RequiredArgsConstructor
public class BanHangTaiQuayServiceIplm implements BanHangTaiQuayService {
    private final HoaDonRepository hoaDonRepo;
    private final SanPhamChiTietRepository sanPhamChiTietRepo;
    private final SanPhamRepository sanPhamRepo;
    private final HoaDonChiTietRepository hoaDonChiTietRepo;

    @Override
    public ResponseEntity<String> taoHoadon() {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setTongSanPham(0l);
        hoaDon.setTongTien(0l);
        hoaDon.setNgayTao(LocalDate.now());
        hoaDon.setTrangThai(1);
        hoaDon.setLoaiHoadon(2);
        hoaDonRepo.save(hoaDon);
        return ResponseEntity.ok("Tao hoa don cho thanh cong");
    }

    @Override
    public ResponseEntity<String> themSanPhamVaoGioHang(HoaDonChiTietTaiQuayDTO dto) {
        HoaDon hoaDon = hoaDonRepo.findById(dto.getHoaDonId())
                .orElseThrow(() -> new RuntimeException("Khong tim thay gio hang"));
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepo.findById(dto.getSanPhamChiTietId())
                .orElseThrow(() -> new RuntimeException("Khong tim thay sanPhamChiTiet"));
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        hoaDonChiTiet.setHoaDon(hoaDon);
        hoaDonChiTiet.setSanPhamChiTiet(sanPhamChiTiet);
        hoaDonChiTiet.setSoLuong(dto.getSoLuong());
        hoaDonChiTiet.setDonGia(sanPhamChiTiet.getGia());
        hoaDonChiTiet.setThanhTien(sanPhamChiTiet.getGia() * dto.getSoLuong());
        if(sanPhamChiTiet.getSoLuongTonKho() < dto.getSoLuong()){
            throw new RuntimeException("San pham chi con ...");
        }
        hoaDonChiTietRepo.save(hoaDonChiTiet);

        sanPhamChiTiet.setSoLuongTonKho(sanPhamChiTiet.getSoLuongTonKho() - dto.getSoLuong());
        sanPhamChiTiet.setSoLuongDaBan(sanPhamChiTiet.getSoLuongDaBan() + dto.getSoLuong());
        sanPhamChiTiet.setTrangThai(sanPhamChiTiet.getSoLuongTonKho() == 0 ? 0 : 1);
        SanPham sanPham = sanPhamChiTiet.getSanPham();
        sanPham.setSoLuongTonKho(sanPham.getSoLuongTonKho() - dto.getSoLuong());
        sanPham.setSoLuongDaBan(sanPham.getSoLuongDaBan() + dto.getSoLuong());
        sanPham.setTrangThai(sanPham.getSoLuongTonKho() == 0 ? 0 : 1);
        sanPhamChiTietRepo.save(sanPhamChiTiet);
        sanPhamRepo.save(sanPham);

        hoaDon.setTongSanPham(hoaDon.getTongSanPham() + 1);
        hoaDon.setTongTien(hoaDon.getTongTien() + hoaDonChiTiet.getThanhTien());
        hoaDonRepo.save(hoaDon);
        return ResponseEntity.ok("Them vao thanh cong");
    }


}
