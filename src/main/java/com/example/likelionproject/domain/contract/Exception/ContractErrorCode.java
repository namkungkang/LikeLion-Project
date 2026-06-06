package com.example.likelionproject.domain.contract.Exception;

public class ContractErrorCode extends RuntimeException {
    public ContractErrorCode(String message) {
        super(message);
    }
}