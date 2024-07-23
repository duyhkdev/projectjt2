package com.duyhk.apibanhang.service.iplm;

import com.duyhk.apibanhang.dto.*;
import com.duyhk.apibanhang.entity.*;
import com.duyhk.apibanhang.repository.KichCoRepository;
import com.duyhk.apibanhang.repository.MauSacRepository;
import com.duyhk.apibanhang.repository.SanPhamChiTietRepository;
import com.duyhk.apibanhang.repository.SanPhamRepository;
import com.duyhk.apibanhang.service.SanPhamChiTietService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SanPhamChiTietServiceIplm implements SanPhamChiTietService {

    private final SanPhamChiTietRepository sanPhamChiTietRepo;
    private final MauSacRepository mauSacRepo;
    private final KichCoRepository kichCoRepo;
    private final SanPhamRepository sanPhamRepo;

    @Override
    public List<SanPhamChiTietDTO> getAll() {
        List<SanPhamChiTietDTO> list = mapToDto(sanPhamChiTietRepo.findAll());
        return list;
    }

    @Override
    public SanPhamChiTietDTO getById(Long id) {
        return null;
    }

    @Override
    public void create(SanPhamChiTietDTO dto) throws IOException {
        SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet();
        mapToEntity(sanPhamChiTiet, dto);
        sanPhamChiTietRepo.save(sanPhamChiTiet);
    }

    @Override
    public void update(SanPhamChiTietDTO dto, Long id) throws IOException {
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Khong tim thay spct"));
        mapToEntity(sanPhamChiTiet, dto);
        sanPhamChiTietRepo.save(sanPhamChiTiet);
    }

    @Override
    public void delete(Long id) {
        sanPhamChiTietRepo.deleteById(id);
    }

    public void mapToEntity(SanPhamChiTiet entity, SanPhamChiTietDTO dto){
        entity.setGia(dto.getGia());
        entity.setTen(dto.getTen());
        entity.setMa(dto.getMa());
        entity.setSoLuongTonKho(dto.getSoLuongTonKho());
        entity.setTrangThai(dto.getTrangThai());
        MauSac mauSac = mauSacRepo.findById(dto.getMauSac().getId())
                .orElseThrow(() -> new RuntimeException("Khong tim thay mau sac"));
        entity.setMauSac(mauSac);
        KichCo kichCo = kichCoRepo.findById(dto.getKichCo().getId())
                .orElseThrow(() -> new RuntimeException("Khong tim thay kich co"));
        entity.setKichCo(kichCo);
        SanPham sanPham = sanPhamRepo.findById(dto.getSanPham().getId())
                        .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));
        entity.setSanPham(sanPham);

    }

    private List<SanPhamChiTietDTO> mapToDto(List<SanPhamChiTiet> listEntity){
        List<SanPhamChiTietDTO> list = new ArrayList<>();
        for(SanPhamChiTiet entity : listEntity){
            SanPhamChiTietDTO dto = new SanPhamChiTietDTO();
            dto.setId(entity.getId());
            dto.setMa(entity.getMa());
            dto.setTen(entity.getTen());
            dto.setGia(entity.getGia());
            dto.setSoLuongTonKho(entity.getSoLuongTonKho());
            dto.setSoLuongDaBan(entity.getSoLuongDaBan());
            dto.setTrangThai(entity.getTrangThai());
            dto.setSanPham(new SanPhamDTO(entity.getSanPham().getId(),
                    entity.getSanPham().getMa(),
                    entity.getSanPham().getTen(),
                    entity.getSanPham().getGia(),
                    entity.getSanPham().getSoLuongTonKho(),
                    entity.getSanPham().getSoLuongDaBan(),
                    entity.getSanPham().getMoTa(),
                    entity.getSanPham().getTrangThai(),
                    new LoaiSanPhamDTO(
                            entity.getSanPham().getLoaiSanPham().getId(),
                            entity.getSanPham().getLoaiSanPham().getTen()),
                    entity.getSanPham().getImages(),
                    null));
            dto.setMauSac(new MauSacDTO(entity.getMauSac().getId(), entity.getMauSac().getTen()));
            dto.setKichCo(new KichCoDTO(entity.getKichCo().getId(), entity.getKichCo().getTen()));
            list.add(dto);
        }
        return list;
    }

}
