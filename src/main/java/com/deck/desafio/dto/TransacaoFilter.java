package com.deck.desafio.dto;

import lombok.Data;

@Data
public class TransacaoFilter {
    Integer page;
    Integer size;
    String name;
    String address;
    String email;
    Boolean active;
}
