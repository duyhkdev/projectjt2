package com.duyhk.apibanhang.controller;

import com.duyhk.apibanhang.dto.MauSacDTO;
import com.duyhk.apibanhang.dto.ResponseDTO;
import com.duyhk.apibanhang.service.MauSacService;
import com.duyhk.apibanhang.service.iplm.MauSacServiceIplm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mau-sac")
@RequiredArgsConstructor
public class MauSacController {

    // controller -> service -> repository -> thao tác với csdl
    // constructor , setter, interface
    private final MauSacService mauSacService ;

    @GetMapping
    public ResponseDTO<List<MauSacDTO>> getAll() {
        ResponseDTO<List<MauSacDTO>> responseDTO = new ResponseDTO<>();
        responseDTO.setData(mauSacService.getAll());
        responseDTO.setStatus(200);
        return responseDTO;
    }

    @GetMapping("/{id}")
    public ResponseDTO<MauSacDTO> getById(@PathVariable Long id) {
        ResponseDTO<MauSacDTO> responseDTO = new ResponseDTO<>();
        responseDTO.setData(mauSacService.getById(id));
        responseDTO.setStatus(200);
        return responseDTO;
    }

    @PostMapping
    public ResponseDTO<Void> create(@RequestBody @Valid MauSacDTO dto){// tên: xanh { id: null, ten: null }
        mauSacService.create(dto);
        return ResponseDTO.<Void>builder()
                .status(201)
                .message("Tao thanh cong mau sac")
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDTO<Void> update(@PathVariable  Long id, @RequestBody @Valid MauSacDTO dto){ // update mau_sac set ten = 'Tím' where id = 1
        mauSacService.update(dto, id);
        return ResponseDTO.<Void>builder()
                .status(201)
                .message("Sửa thanh cong mau sac")
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseDTO<Void> delete(@PathVariable Long id){
        mauSacService.delete(id);
        return ResponseDTO.<Void>builder()
                .status(201)
                .message("Xóa thanh cong mau sac")
                .build();
    }

}
