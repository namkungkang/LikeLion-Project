package com.example.likelionproject.domain.contract.controller;

import com.example.likelionproject.domain.contract.dto.ContractRequestDto;
import com.example.likelionproject.domain.contract.dto.ContractResponseDto;
import com.example.likelionproject.domain.contract.dto.UserStatusResponse;
import com.example.likelionproject.domain.contract.service.ContractService;
import com.example.likelionproject.global.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contracts")
@Tag(name = "Contract", description = "계약서 관련 API")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    @Operation(summary = "계약서 작성", description = "계약서를 생성하는 API")
    @PostMapping
    public ResponseEntity<ContractResponseDto> createContract(@Valid @RequestBody ContractRequestDto requestDto) {
        ContractResponseDto response = contractService.createContract(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "계약서 조회", description = "계약서를 조회하는 API")
    @GetMapping("/{id}")
    public ResponseEntity<ContractResponseDto> getContract(@PathVariable Long id) {
        ContractResponseDto response = contractService.getContract(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "계약서 상태 조회", description = "계약서 작성 유무 조회 API")
    @GetMapping("/users/{userId}/status")
    public ResponseEntity<BaseResponse<UserStatusResponse>> getUserStatus(@PathVariable Long userId) {
        UserStatusResponse data = contractService.getUserContractStatus(userId);
        return ResponseEntity.ok(BaseResponse.success(data));
    }
}