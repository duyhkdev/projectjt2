package com.duyhk.apibanhang.service.iplm;

import com.duyhk.apibanhang.dto.LoaiSanPhamDTO;
import com.duyhk.apibanhang.entity.LoaiSanPham;
import com.duyhk.apibanhang.repository.LoaiSanPhamRepository;
import com.duyhk.apibanhang.service.LoaiSanPhamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LoaiSanPhamServiceIplm implements LoaiSanPhamService {
    private final LoaiSanPhamRepository loaiSanPhamRepo;

    @Override
    public List<LoaiSanPhamDTO> getAll() {
        List<LoaiSanPham> listEntity = loaiSanPhamRepo.findAll();
        return mapToDto(listEntity);
    }

    @Override
    public LoaiSanPhamDTO getById(Long id) {
        LoaiSanPham loaiSanPham = loaiSanPhamRepo.findById(id).orElse(null);
        return new LoaiSanPhamDTO(loaiSanPham.getId(), loaiSanPham.getTen());
    }

    @Override
    public void create(LoaiSanPhamDTO loaiSanPhamDTO) { // mauSacDTO sẽ có tên
        LoaiSanPham loaiSanPham = new LoaiSanPham();
        loaiSanPham.setTen(loaiSanPhamDTO.getTen());
        // insert into ...
        loaiSanPhamRepo.save(loaiSanPham);
    }

    @Override
    public void update(LoaiSanPhamDTO loaiSanPhamDTO, Long id) {
        LoaiSanPham entity = new LoaiSanPham();
        entity.setTen(loaiSanPhamDTO.getTen());
        entity.setId(id);
        loaiSanPhamRepo.save(entity); // có cả id và tên
    }

    @Override
    public void delete(Long id) {
        // delete from mau_sac where id = 3
        loaiSanPhamRepo.deleteById(id);
    }

    private List<LoaiSanPhamDTO> mapToDto(List<LoaiSanPham> listEntity){
        List<LoaiSanPhamDTO> list = new ArrayList<>();
        for(LoaiSanPham entity : listEntity){
            list.add(new LoaiSanPhamDTO(entity.getId(), entity.getTen()));
        }
        return list;
    }
}
