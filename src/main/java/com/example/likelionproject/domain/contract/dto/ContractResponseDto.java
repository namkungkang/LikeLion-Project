package com.example.likelionproject.domain.contract.dto;


import com.example.likelionproject.domain.contract.entity.Contract;
import lombok.Getter;

@Getter
public class ContractResponseDto {
    private final Long id;
    private final Long userId;
    private final String title;
    private final String content;
    private final String imageUrl;
    private final String place;

    public ContractResponseDto(Contract contract) {
        this.id = contract.getId();
        this.userId = contract.getUser().getId();
        this.title = contract.getTitle();
        this.content = contract.getContent();
        this.imageUrl = contract.getImageUrl();
        this.place = contract.getPlace();
    }
}

