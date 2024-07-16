package com.duyhk.apibanhang.controller;

import com.duyhk.apibanhang.dto.KichCoDTO;
import com.duyhk.apibanhang.dto.MauSacDTO;
import com.duyhk.apibanhang.dto.ResponseDTO;
import com.duyhk.apibanhang.service.KichCoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kich-co")
@RequiredArgsConstructor
public class KichCoController {

    // controller -> service -> repository -> thao tác với csdl
    // constructor , setter, interface
    private final KichCoService kichCoService ;

    @GetMapping
    public ResponseDTO<List<KichCoDTO>> getAll() {
        ResponseDTO<List<KichCoDTO>> responseDTO = new ResponseDTO<>();
        responseDTO.setData(kichCoService.getAll());
        responseDTO.setStatus(200);
        return responseDTO;
    }

    @GetMapping("/{id}")
    public ResponseDTO<KichCoDTO> getById(@PathVariable Long id) {
        ResponseDTO<KichCoDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setData(kichCoService.getById(id));
        responseDTO.setStatus(200);
        return responseDTO;
    }

    @PostMapping
    public ResponseDTO<Void> create(@RequestBody @Valid KichCoDTO dto){// tên: xanh { id: null, ten: null }
        kichCoService.create(dto);
        return ResponseDTO.<Void>builder()
                .status(201)
                .message("Tao thanh cong ")
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDTO<Void> update(@PathVariable  Long id, @RequestBody @Valid KichCoDTO dto){ // update mau_sac set ten = 'Tím' where id = 1
        kichCoService.update(dto, id);
        return ResponseDTO.<Void>builder()
                .status(201)
                .message("Sửa thanh cong ")
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseDTO<Void> delete(@PathVariable Long id){
        kichCoService.delete(id);
        return ResponseDTO.<Void>builder()
                .status(201)
                .message("Xóa thanh cong")
                .build();
    }

}
