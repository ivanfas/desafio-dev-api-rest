package com.deck.desafio.controller;

import com.deck.desafio.dto.*;
import com.deck.desafio.exception.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.Collection;

public interface TransacaoController {

    @GetMapping
    Collection<TransacaoResponse> find(TransacaoFilter filter);

    @PostMapping("/deposit")
    ResponseEntity<ApiResponse> createDeposit(
            @Valid @RequestBody TransacaoCreateDeposit dto
    ) throws URISyntaxException, ApiException;

    @PostMapping("/withdrawal")
    ResponseEntity<ApiResponse> createWithdrawal(
            @Valid @RequestBody TransacaoCreateWithdrawal dto
    ) throws URISyntaxException, ApiException;

}