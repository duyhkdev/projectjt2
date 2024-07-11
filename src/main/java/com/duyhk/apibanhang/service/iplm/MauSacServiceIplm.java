package com.duyhk.apibanhang.service.iplm;

import com.duyhk.apibanhang.dto.MauSacDTO;
import com.duyhk.apibanhang.entity.MauSac;
import com.duyhk.apibanhang.repository.MauSacRepository;
import com.duyhk.apibanhang.service.MauSacService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MauSacServiceIplm implements MauSacService {
    // controller -> service -> repository -> thao tác với csdl

    private final MauSacRepository mauSacRepo;

    @Override
    public List<MauSacDTO> getAll() {
        List<MauSac> listEntity = mauSacRepo.findAll();
        return mapToDto(listEntity);
    }

    @Override
    public MauSacDTO getById(Long id) {
        MauSac mauSac = mauSacRepo.findById(id).orElse(null);
        if(mauSac == null){
            throw new RuntimeException("Khong tim thay mau sac");
        }
        return new MauSacDTO(mauSac.getId(), mauSac.getTen());
    }

    @Override
    public void create(MauSacDTO mauSacDTO) { // mauSacDTO sẽ có tên
        MauSac mauSac = new MauSac();
        mauSac.setTen(mauSacDTO.getTen());
        // insert into ...
        mauSacRepo.save(mauSac);
    }

    @Override
    public void update(MauSacDTO mauSacDTO, Long id) {
        // xem co id khong
        MauSac mauSac = new MauSac();
        mauSac.setTen(mauSacDTO.getTen());
        mauSac.setId(id);
        mauSacRepo.save(mauSac); // có cả id và tên
    }

    @Override
    public void delete(Long id) {
        // delete from mau_sac where id = 3
        // khoong thay id bao id khong ton tai
        mauSacRepo.findById(id).orElseThrow(() -> new RuntimeException("Not found mau sac id"));
        mauSacRepo.deleteById(id);
    }

    private List<MauSacDTO> mapToDto(List<MauSac> listEntity){
        List<MauSacDTO> list = new ArrayList<>();
        for(MauSac entity : listEntity){
            list.add(new MauSacDTO(entity.getId(), entity.getTen()));
        }
        return list;
    }

    /*
      <phạm vi truy câp> <Kiểu dữ liệu trả về> <tên phương thức> (<Các tham số truyền vào>){
        <Công việc>
      }

      private         List<MauSacDTO> mapToDto(List<MauSac> listEntity){
     */

}
