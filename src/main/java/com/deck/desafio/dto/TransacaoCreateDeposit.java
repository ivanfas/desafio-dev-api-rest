package com.deck.desafio.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Data
public class TransacaoCreateDeposit {

	@NotEmpty
	Long idConta;

	@NotNull
	BigDecimal valor;
}
