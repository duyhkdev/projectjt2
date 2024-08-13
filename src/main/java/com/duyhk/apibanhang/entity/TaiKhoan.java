package com.duyhk.apibanhang.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaiKhoan implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ma; // KH001
    private String email; // ngươi dùng tự nhập
    private String matKhau; //
    private String hoVaTen; //
    private Role role; //''
    private Long tongHoaDon; //  Khi tạo tk = 0 ->
    private Long tongTien; // Khi tạo tk = 0
    // khi tạo ra 1 tk khách hàng mặc định là kh thường
    // VIP: tongHoaDon > 75 và tongTien > 15tr
    private Integer hangTaiKhoan; // 1: KH thường, 2 KH VIP
    private Integer trangThai; // 0 - 1

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return matKhau;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
