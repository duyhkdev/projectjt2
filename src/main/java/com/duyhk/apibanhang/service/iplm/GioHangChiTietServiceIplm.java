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
    public void update(GioHangChiTietDTO dto, Long id) {

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
