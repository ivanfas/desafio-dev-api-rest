package com.deck.desafio.dto;

import com.deck.desafio.model.Conta;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;


@Data
public class ContaCreate {

	@NotEmpty
	Long idPessoa;

	@NotEmpty
	BigDecimal saldo;

	@NotEmpty
	BigDecimal limiteSaqueDiario;

	@NotEmpty
	Conta.TipoConta tipoConta;

}
