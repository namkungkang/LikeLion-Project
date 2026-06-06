package com.example.likelionproject.domain.contract.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(title = "UserStatusResponse DTO", description = "유저 정보 및 계약 상태 응답")
public class UserStatusResponse {

    @Schema(description = "유저 ID", example = "10")
    private Long userId;

    @Schema(description = "유저 이름", example = "홍길동")
    private String name;

    @Schema(description = "유저 이메일", example = "ㅇㄴㅇㄴㅇㄴ@example.com")
    private String email;

    @Schema(description = "계약 상태", example = "계약 완료")
    private String contractStatus; // "계약 전" 또는 "계약 완료"
}