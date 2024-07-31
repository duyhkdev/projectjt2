package com.duyhk.apibanhang.service.iplm;

import com.duyhk.apibanhang.dto.HoaDonDTO;
import com.duyhk.apibanhang.entity.*;
import com.duyhk.apibanhang.repository.*;
import com.duyhk.apibanhang.service.DatHangOnlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DatHangOnlineServiceIplm implements DatHangOnlineService {
    private final TaiKhoanRepository taiKhoanRepo;
    private final HoaDonRepository hoaDonRepo;
    private final GioHangRepository gioHangRepo;
    private final GioHangChiTietRepository gioHangChiTietRepo;
    private final HoaDonChiTietRepository hoaDonChiTietRepo;

    @Override
    public ResponseEntity<String> create(HoaDonDTO dto) {
        GioHang gioHang = gioHangRepo.findByTaiKhoanId(dto.getTaiKhoanId())
                .orElseThrow(() -> new RuntimeException("..."));
        mapGioHangToHoaDon(gioHang, new HoaDon(), dto);
        return ResponseEntity.ok("Đặt hàng thành công");
    }

    private void mapGioHangToHoaDon(GioHang gioHang, HoaDon hoaDon, HoaDonDTO dto){
        hoaDon.setSoDienThoai(dto.getSoDienThoai());
        hoaDon.setDiaChi(dto.getDiaChi());
        hoaDon.setHoVaTen(dto.getHoVaTen());
        hoaDon.setLoaiHoadon(1);
        hoaDon.setTongTien(gioHang.getTongSoTien());
        gioHang.setTongSoTien(0l);
        hoaDon.setTongSanPham(gioHang.getTongSanPham());
        gioHang.setTongSanPham(0l);
        hoaDon.setTaiKhoan(gioHang.getTaiKhoan());
        hoaDon.setTrangThai(1);
        hoaDon.setNgayTao(LocalDate.now());
        HoaDon hds = hoaDonRepo.save(hoaDon);
        gioHangRepo.save(gioHang);
        List<GioHangChiTiet> lgh = gioHangChiTietRepo.findByGioHangId(gioHang.getId());
        List<HoaDonChiTiet> lhd = new ArrayList<>();
        for(GioHangChiTiet x : lgh){
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setDonGia(x.getSanPhamChiTiet().getGia());
            hoaDonChiTiet.setSoLuong(x.getSoLuong());
            hoaDonChiTiet.setThanhTien(x.getSoLuong() * x.getSanPhamChiTiet().getGia());
            hoaDonChiTiet.setSanPhamChiTiet(x.getSanPhamChiTiet());
            hoaDonChiTiet.setHoaDon(hds);
            lhd.add(hoaDonChiTiet);
            gioHangChiTietRepo.deleteById(x.getId());
        }
        hoaDonChiTietRepo.saveAll(lhd);
    }
}
