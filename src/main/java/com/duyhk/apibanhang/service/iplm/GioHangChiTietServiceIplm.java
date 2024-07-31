package com.duyhk.apibanhang.service.iplm;

import com.duyhk.apibanhang.dto.GioHangChiTietDTO;
import com.duyhk.apibanhang.entity.GioHang;
import com.duyhk.apibanhang.entity.GioHangChiTiet;
import com.duyhk.apibanhang.entity.SanPhamChiTiet;
import com.duyhk.apibanhang.repository.GioHangChiTietRepository;
import com.duyhk.apibanhang.repository.GioHangRepository;
import com.duyhk.apibanhang.repository.SanPhamChiTietRepository;
import com.duyhk.apibanhang.service.GioHangChiTietService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GioHangChiTietServiceIplm implements GioHangChiTietService {

    private final GioHangChiTietRepository gioHangChiTietRepo;
    private final SanPhamChiTietRepository sanPhamChiTietRepo;
    private final GioHangRepository gioHangRepo;

    @Override
    public List<GioHangChiTietDTO> getAll(Integer page, Integer size) {
        return null;
    }

    @Override
    public GioHangChiTietDTO getById(Long id) {
        return null;
    }

    @Override
    public void create(GioHangChiTietDTO dto) {
        GioHangChiTiet entity = new GioHangChiTiet();
        mapToEntity(entity, dto);
        gioHangChiTietRepo.save(entity);
    }

    @Override
    public void update(Long soLuong, Long id) {
        GioHangChiTiet ghct = gioHangChiTietRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found ghct"));
        ghct.setSoLuong(soLuong);
        GioHang gioHang = ghct.getGioHang();
        Long thanhTienMoi = soLuong * ghct.getSanPhamChiTiet().getGia();
        /*
            Giỏ hàng đang có giá 1000000 -> 1100000
            Giỏ hàng chi tiết cũ 200000
            Sửa số lượng -> Giỏ hàng chi tiết -> 300000
         */
        gioHang.setTongSoTien(
                gioHang.getTongSoTien() - ghct.getThanhTien() + thanhTienMoi
        );
        ghct.setThanhTien(thanhTienMoi);
        gioHangRepo.save(gioHang);
        gioHangChiTietRepo.save(ghct);

    }

    @Override
    public void delete(Long id) {
        GioHangChiTiet ghct = gioHangChiTietRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found ghct"));
        GioHang gioHang = ghct.getGioHang();
        gioHang.setTongSanPham(gioHang.getTongSanPham() - 1);
        gioHang.setTongSoTien(gioHang.getTongSoTien() - ghct.getThanhTien());
        gioHangRepo.save(gioHang);
        gioHangChiTietRepo.deleteById(id);
    }

    public void mapToEntity(GioHangChiTiet entity, GioHangChiTietDTO dto) {
        SanPhamChiTiet sanPhamChiTiet =
                sanPhamChiTietRepo.findById(dto.getSanPhamChiTiet().getId())
                        .orElseThrow(() -> new RuntimeException("Khong tim thay san pham"));
        entity.setSanPhamChiTiet(sanPhamChiTiet);
        GioHang gioHang =
                gioHangRepo.findById(dto.getGioHangId())
                        .orElseThrow(() -> new RuntimeException("Khong tim thay gio hang"));
        entity.setGioHang(gioHang);
        Long gia = sanPhamChiTiet.getGia();
        // tinh thanh tien
        Long thanhTien = gia * dto.getSoLuong();


        // luc dau co 1 sp khi them 1 sp khac => 1 + 1

        gioHang.setTongSoTien(gioHang.getTongSoTien() + thanhTien);

        // kiem tra co ton tai san pham trong gio hang chưa
        GioHangChiTiet isExist = gioHangChiTietRepo.findByGioHangIdAndSanPhamChiTietId(dto.getGioHangId(), sanPhamChiTiet.getId())
                .orElse(null);

        if (isExist != null) {
            // neu ton tai trong gio hang roi thi cong don
            entity.setId(isExist.getId());
            entity.setSoLuong(isExist.getSoLuong() + dto.getSoLuong());
            entity.setThanhTien(isExist.getThanhTien() + thanhTien);
        }else{
            // neu chua ton tai trong gio hang thi them moi
            entity.setSoLuong(dto.getSoLuong());
            gioHang.setTongSanPham(gioHang.getTongSanPham() + 1);
            entity.setThanhTien(thanhTien);
        }
    }
}
