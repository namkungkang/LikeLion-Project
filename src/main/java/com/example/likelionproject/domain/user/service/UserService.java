package com.example.likelionproject.domain.user.service;

import com.example.likelionproject.domain.auth.Exception.AuthErrorCode;
import com.example.likelionproject.global.exception.CustomException;
import com.example.likelionproject.domain.user.Exception.UserErrorCode;
import com.example.likelionproject.domain.user.dto.SignUpRequest;
import com.example.likelionproject.domain.user.dto.SignUpResponse;
import com.example.likelionproject.domain.user.entity.Role;
import com.example.likelionproject.domain.user.entity.User;
import com.example.likelionproject.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignUpResponse signUp(SignUpRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new CustomException(UserErrorCode.USER_NOT_FOUND);
        }

        User user =
                User.builder()
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .name(request.getName())
                        .role(Role.USER)
                        .build();

        User savedUser = userRepository.save(user);

        return SignUpResponse.builder()
                .userId(savedUser.getId())
                .email(savedUser.getEmail())
                .name(savedUser.getName())
                .build();
    }
}