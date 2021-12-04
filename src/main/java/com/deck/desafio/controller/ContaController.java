package com.deck.desafio.controller;

import com.deck.desafio.exception.ApiException;
import com.deck.desafio.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;

public interface ContaController {

    @GetMapping("/{id}/saldo")
    ResponseEntity<?> findOneSaldo(@PathVariable Long id);

    @PostMapping
    ResponseEntity<ApiResponse> create(
            @Valid @RequestBody ContaCreate dto
    ) throws URISyntaxException, ApiException;

    @PatchMapping("/{id}")
    public ResponseEntity<?> block(@PathVariable Long id) throws ApiException;

}