package com.duyhk.apibanhang.service.iplm;

import com.duyhk.apibanhang.dto.KichCoDTO;
import com.duyhk.apibanhang.entity.KichCo;
import com.duyhk.apibanhang.repository.KichCoRepository;
import com.duyhk.apibanhang.service.KichCoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class KichCoServiceIplm implements KichCoService {
    private final KichCoRepository kichCoRepo;

    @Override
    public List<KichCoDTO> getAll() {
        List<KichCo> listEntity = kichCoRepo.findAll();
        return mapToDto(listEntity);
    }

    @Override
    public KichCoDTO getById(Long id) {
        KichCo kichCo = kichCoRepo.findById(id).orElse(null);
        return new KichCoDTO(kichCo.getId(), kichCo.getTen());
    }

    @Override
    public void create(KichCoDTO kichCoDto) { // mauSacDTO sẽ có tên
        KichCo kichCo = new KichCo();
        kichCo.setTen(kichCoDto.getTen());
        // insert into ...
        kichCoRepo.save(kichCo);
    }

    @Override
    public void update(KichCoDTO kichCoDto, Long id) {
        KichCo kichCo = new KichCo();
        kichCo.setTen(kichCoDto.getTen());
        kichCo.setId(id);
        kichCoRepo.save(kichCo); // có cả id và tên
    }

    @Override
    public void delete(Long id) {
        // delete from mau_sac where id = 3
        kichCoRepo.deleteById(id);
    }

    private List<KichCoDTO> mapToDto(List<KichCo> listEntity){
        List<KichCoDTO> list = new ArrayList<>();
        for(KichCo entity : listEntity){
            list.add(new KichCoDTO(entity.getId(), entity.getTen()));
        }
        return list;
    }
}
