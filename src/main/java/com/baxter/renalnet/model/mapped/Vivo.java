package com.baxter.renalnet.model.mapped;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class Vivo extends Ativo {
	
	@NotNull
	LocalDateTime dataCriacao;
	
	@NotNull
	LocalDateTime dataAtualizacao;
	
}
