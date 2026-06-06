package com.example.likelionproject.domain.contract.service;


import com.example.likelionproject.domain.contract.Exception.ContractErrorCode;
import com.example.likelionproject.domain.contract.dto.ContractRequestDto;
import com.example.likelionproject.domain.contract.dto.ContractResponseDto;
import com.example.likelionproject.domain.contract.dto.UserStatusResponse;
import com.example.likelionproject.domain.contract.entity.Contract;
import com.example.likelionproject.domain.contract.repository.ContractRepository;
import com.example.likelionproject.domain.user.entity.User;
import com.example.likelionproject.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContractService {

    private final ContractRepository contractRepository;
    private final UserRepository userRepository; // 외래키 조회를 위해 주입

    @Transactional
    public ContractResponseDto createContract(ContractRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        Contract contract = Contract.builder()
                .user(user)
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .imageUrl(requestDto.getImageUrl())
                .place(requestDto.getPlace())
                .build();

        Contract savedContract = contractRepository.save(contract);
        return new ContractResponseDto(savedContract);
    }


    public ContractResponseDto getContract(Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new ContractErrorCode("해당 계약서를 찾을 수 없습니다. ID: " + id));
        return new ContractResponseDto(contract);
    }

    @Transactional(readOnly = true)
    public UserStatusResponse getUserContractStatus(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));

        boolean hasContract = contractRepository.existsByUserId(userId);
        String status = hasContract ? "계약 완료" : "계약 전";

        return new UserStatusResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                status
        );
    }
}