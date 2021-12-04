package com.deck.desafio.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ApiExceptionDto {

	private String entity;
	
	private String id;
	
	private ApiExceptionDomain.ExceptionType type;

	private String message;
	
}
