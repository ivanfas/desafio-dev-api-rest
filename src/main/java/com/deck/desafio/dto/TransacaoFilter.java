package com.deck.desafio.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransacaoFilter {
    Integer page;
    Integer size;
    Long idTransacao;
    Long idConta;
    LocalDate startTime;
    LocalDate endTime;
}
