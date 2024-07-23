package com.duyhk.apibanhang.service.iplm;

import com.duyhk.apibanhang.dto.LoaiSanPhamDTO;
import com.duyhk.apibanhang.dto.MauSacDTO;
import com.duyhk.apibanhang.dto.SanPhamDTO;
import com.duyhk.apibanhang.entity.LoaiSanPham;
import com.duyhk.apibanhang.entity.MauSac;
import com.duyhk.apibanhang.entity.SanPham;
import com.duyhk.apibanhang.repository.LoaiSanPhamRepository;
import com.duyhk.apibanhang.repository.SanPhamRepository;
import com.duyhk.apibanhang.service.SanPhamService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SanPhamServiceIplm implements SanPhamService {

    private final SanPhamRepository sanPhamRepo;

    private final LoaiSanPhamRepository loaiSanPhamRepo;


    @Override
    public List<SanPhamDTO> getAll() {
        return mapToDto(sanPhamRepo.findAll());
    }

    @Override
    public List<SanPhamDTO> search(String ten, String ma, Long idLoaiSanPham, Integer page, Integer size) {
        // 0 1 2
        page = page == null ? 0 : page; // vị trí trang
        size = size == null ? 5 : size; // số phần tử của trang
        Page<SanPham> pageEntity = sanPhamRepo.getAll(ten, ma, idLoaiSanPham, PageRequest.of(page, size));

        List<SanPhamDTO> listDto = mapToDto(pageEntity.getContent());

        return listDto;
    }

    @Override
    public SanPhamDTO getById(Long id) {
        return null;
    }

    @Override
    public void create(SanPhamDTO dto) throws IOException {
        List<String> images = new ArrayList<>();
        if(dto.getFiles() != null){
            // dto.getFiles() là 1 List<MultipartFile> anhok.png
            for(MultipartFile multipartFile : dto.getFiles()){
                String name = multipartFile.getOriginalFilename();
                images.add(name);
                String path = "D:/workspace/std/images";
                File folder = new File(path);
                if(!folder.exists()){
                    folder.mkdirs();
                }
                // vi du mn upload file ten là anh.png
                // D:/workspace/std/images/anh.png
                File file = new File(path + "/" + name);
                multipartFile.transferTo(file);
            }
        }else{
            throw new RuntimeException("Vui lòng chọn ảnh");
        }
        SanPham sanPham = new SanPham();
        mapToEntitySave(sanPham, dto);
        sanPham.setImages(images);
        sanPham.setSoLuongDaBan(0l);
        sanPham.setTrangThai(1);
        sanPhamRepo.save(sanPham);
    }

    @Override
    public void update(SanPhamDTO dto, Long id) throws IOException {
        SanPham sanPham = sanPhamRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Khong tim thay san pham"));
        List<String> images = new ArrayList<>();
        if(dto.getFiles() != null){
            // dto.getFiles() là 1 List<MultipartFile> anhok.png
            for(MultipartFile multipartFile : dto.getFiles()){
                String name = multipartFile.getOriginalFilename();
                images.add(name);
                String path = "D:/workspace/std/images";
                File folder = new File(path);
                if(!folder.exists()){
                    folder.mkdirs();
                }
                // vi du mn upload file ten là anh.png
                // D:/workspace/std/images/anh.png
                File file = new File(path + "/" + name);
                multipartFile.transferTo(file);
            }
            sanPham.setImages(images);
        }
        mapToEntitySave(sanPham, dto);
        sanPham.setTrangThai(dto.getTrangThai());
        sanPhamRepo.save(sanPham);
    }

    @Override
    public void delete(Long id) {
        sanPhamRepo.deleteById(id);
    }

    private void mapToEntitySave(SanPham sanPham, SanPhamDTO dto){
        sanPham.setMa(dto.getMa());
        sanPham.setGia(dto.getGia());
        sanPham.setSoLuongTonKho(dto.getSoLuongTonKho());
        sanPham.setMoTa(dto.getMoTa());
        sanPham.setTen(dto.getTen());
        LoaiSanPham loaiSanPham = loaiSanPhamRepo.findById(dto.getLoaiSanPham().getId())
                .orElseThrow(() -> new RuntimeException("Khong tim thay loai san pham"));
        sanPham.setLoaiSanPham(loaiSanPham);
    }

    private List<SanPhamDTO> mapToDto(List<SanPham> listEntity){
        List<SanPhamDTO> list = new ArrayList<>();
        for(SanPham entity : listEntity){
            SanPhamDTO dto = new SanPhamDTO();
            dto.setId(entity.getId());
            dto.setMa(entity.getMa());
            dto.setTen(entity.getTen());
            dto.setGia(entity.getGia());
            dto.setSoLuongTonKho(entity.getSoLuongTonKho());
            dto.setSoLuongDaBan(entity.getSoLuongDaBan());
            dto.setMoTa(entity.getMoTa());
            dto.setTrangThai(entity.getTrangThai());
            dto.setImages(entity.getImages());
            LoaiSanPham loaiSanPham = entity.getLoaiSanPham();
            dto.setLoaiSanPham(
                    new LoaiSanPhamDTO(loaiSanPham.getId(), loaiSanPham.getTen()));
            list.add(dto);
        }
        return list;
    }
}
