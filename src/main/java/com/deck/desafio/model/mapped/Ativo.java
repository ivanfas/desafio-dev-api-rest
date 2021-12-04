package com.deck.desafio.model.mapped;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class Ativo {
	
	@NotNull
	boolean flagAtivo = true;
	
}
