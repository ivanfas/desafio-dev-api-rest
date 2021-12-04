package com.deck.desafio.model;

import com.deck.desafio.model.mapped.Vivo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
@Entity
public class Pessoa extends Vivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idPessoa;

    @NotEmpty
    String nome;

    @NotEmpty
    String cpf;

    @NotEmpty
    LocalDate dataNascimento;
}


