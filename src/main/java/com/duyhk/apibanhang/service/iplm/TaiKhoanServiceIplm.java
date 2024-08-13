package com.duyhk.apibanhang.service.iplm;

import com.duyhk.apibanhang.dto.TaiKhoanDTO;
import com.duyhk.apibanhang.entity.GioHang;
import com.duyhk.apibanhang.entity.TaiKhoan;
import com.duyhk.apibanhang.repository.GioHangRepository;
import com.duyhk.apibanhang.repository.TaiKhoanRepository;
import com.duyhk.apibanhang.service.TaiKhoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaiKhoanServiceIplm implements TaiKhoanService {

    private final TaiKhoanRepository taiKhoanRepo;
    private final GioHangRepository gioHangRepo;
    private final EmailService emailService;
    private final PasswordEncoder encoder;

    @Override
    public List<TaiKhoanDTO> getAll(Integer page, Integer size) {
//        Page<>
        return null;
    }

    @Override
    public TaiKhoanDTO getById(Long id) {
        return null;
    }

    @Override
    public void create(TaiKhoanDTO dto) {
        TaiKhoan entity = new TaiKhoan();
        mapToEntity(entity, dto);
        entity.setTongHoaDon(0l);
        entity.setTongTien(0l);
        entity.setHangTaiKhoan(1);
        entity.setTrangThai(1);
        TaiKhoan tkSave = taiKhoanRepo.save(entity); // luu vao db sau do select lai
        GioHang gioHang = new GioHang(0l, 0l, tkSave);
        gioHangRepo.save(gioHang);
//        emailService.sendEmail(
//                entity.getEmail(),
//                "Đăng ký taì khoản",
//                "Hello " + entity.getHoVaTen());
    }

    @Override
    public void update(TaiKhoanDTO dto, Long id ) {

    }

    private void mapToEntity(TaiKhoan entity, TaiKhoanDTO dto) {
        entity.setMa(dto.getMa());
        entity.setEmail(dto.getEmail());
        entity.setMatKhau(encoder.encode(dto.getMatKhau()));
        entity.setHoVaTen(dto.getHoVaTen());
        entity.setRole(dto.getRole());
    }
}
