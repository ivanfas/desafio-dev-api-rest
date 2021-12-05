package com.deck.desafio.model;

import com.deck.desafio.model.mapped.Vivo;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
@Entity
@Builder
public class Transacao extends Vivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idTransacao;

    @ManyToOne
    @JoinColumn(name="id_conta", nullable=false)
    Conta conta;

    @NotNull
    BigDecimal valor;

    @NotEmpty
    LocalDateTime dataTransacao;
}


