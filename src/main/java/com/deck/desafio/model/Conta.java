package com.deck.desafio.model;

import com.deck.desafio.model.mapped.Vivo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
@Entity
public class Conta extends Vivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idConta;

    @ManyToOne
    @JoinColumn(name="id_pessoa", nullable=false)
    Pessoa pessoa;

    @NotEmpty
    BigDecimal saldo;

    @NotEmpty
    BigDecimal limiteSaqueDiario;

    @NotEmpty
    Conta.TipoConta tipoConta;

    public enum TipoConta {
        ASD, DSA;
    }

}


