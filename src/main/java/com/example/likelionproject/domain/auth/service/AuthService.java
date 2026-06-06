package com.example.likelionproject.domain.auth.service;

import com.example.likelionproject.domain.auth.Exception.AuthErrorCode;
import com.example.likelionproject.domain.auth.dto.request.LoginRequest;
import com.example.likelionproject.domain.auth.dto.response.LoginResponse;
import com.example.likelionproject.global.exception.CustomException;
import com.example.likelionproject.global.security.CustomUserDetails;
import com.example.likelionproject.global.security.CustomUserDetailsService;
import com.example.likelionproject.global.security.JwtProvider;
import com.example.likelionproject.domain.contract.Exception.ContractErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public LoginResponse login(LoginRequest request) {

        CustomUserDetails userDetails =
                (CustomUserDetails) customUserDetailsService.loadUserByUsername(request.getEmail());

        if(!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
            throw new CustomException(AuthErrorCode.AUTH_NOT_FOUND);
        }

        String accessToken = jwtProvider.createAccessToken(userDetails);

        return LoginResponse.builder()
                .accesToken(accessToken)
                .build();
    }
}
