package com.baxter.renalnet.exception;

import lombok.*;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class ApiException extends Exception {

    private static final long serialVersionUID = 1L;

    @NotNull
    private final String entity;

	@NotNull
    private final String id;

	@NotNull
    private final ApiExceptionDomain.ExceptionType type;

    private String message;

}
