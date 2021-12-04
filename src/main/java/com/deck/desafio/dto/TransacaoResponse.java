package com.deck.desafio.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransacaoResponse {

    Long id;
    String name;
    String address;
    String email;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
