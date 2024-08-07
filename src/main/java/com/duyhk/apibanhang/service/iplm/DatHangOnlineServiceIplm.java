package com.duyhk.apibanhang.service.iplm;

import com.duyhk.apibanhang.dto.HoaDonChiTietDTO;
import com.duyhk.apibanhang.dto.HoaDonDTO;
import com.duyhk.apibanhang.dto.ThongTinHoaDonDTO;
import com.duyhk.apibanhang.entity.*;
import com.duyhk.apibanhang.repository.*;
import com.duyhk.apibanhang.service.DatHangOnlineService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DatHangOnlineServiceIplm implements DatHangOnlineService {
    private final TaiKhoanRepository taiKhoanRepo;
    private final HoaDonRepository hoaDonRepo;
    private final GioHangRepository gioHangRepo;
    private final GioHangChiTietRepository gioHangChiTietRepo;
    private final HoaDonChiTietRepository hoaDonChiTietRepo;

    @Override
    public ResponseEntity<ThongTinHoaDonDTO> getById(Long id) {
        HoaDon hoaDon = hoaDonRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Khong tim thay hoa don "));
        List<HoaDonChiTiet> hoaDonChiTietList = hoaDonChiTietRepo.findByHoaDonId(id)
                .orElse(new ArrayList<>());


        return mapToDto(hoaDon,hoaDonChiTietList);
    }
    private ResponseEntity<ThongTinHoaDonDTO> mapToDto(HoaDon hoaDon, List<HoaDonChiTiet> list){
        ThongTinHoaDonDTO dto = new ThongTinHoaDonDTO();
        HoaDonDTO hoaDonDTO = new ModelMapper().map(hoaDon, HoaDonDTO.class);
        List<HoaDonChiTietDTO> listHdct = list.stream()
                .map(x -> new ModelMapper().map(x, HoaDonChiTietDTO.class)).collect(Collectors.toList());
        dto.setHoaDon(hoaDonDTO);
        dto.setHoaDonChiTietList(listHdct);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<String> create(HoaDonDTO dto) {
        GioHang gioHang = gioHangRepo.findByTaiKhoanId(dto.getTaiKhoanId())
                .orElseThrow(() -> new RuntimeException("..."));
        mapGioHangToHoaDon(gioHang, new HoaDon(), dto);
        return ResponseEntity.ok("Đặt hàng thành công");
    }

    @Override
    public ResponseEntity<String> updateStatus(Long id, Integer trangThai) {
        checkStatus(id, trangThai);

        return ResponseEntity.ok("Thay doi trang thai thanh cong");
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
    public void checkStatus(Long id, Integer trangThai){
        if(trangThai != 0){
            HoaDon hoaDon = hoaDonRepo.findByIdAndTrangThai(id,trangThai - 1)
                    .orElseThrow(() -> new RuntimeException("Trang thai khong phai cho"));
            hoaDon.setTrangThai(trangThai);
            if(trangThai == 4){
                hoaDon.setNgayHoanThanh(LocalDate.now());
            }
            hoaDonRepo.save(hoaDon);
        }
        else{
            //
        }

    }
}
