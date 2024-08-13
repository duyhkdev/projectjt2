package com.duyhk.apibanhang.service.iplm;

import com.duyhk.apibanhang.entity.TaiKhoan;
import com.duyhk.apibanhang.repository.TaiKhoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInfoService implements UserDetailsService {

    private final TaiKhoanRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<TaiKhoan> userDetail = repository.findByEmail(username);
        return userDetail.orElseThrow(() -> new RuntimeException("Not found user " + username));
    }

}
