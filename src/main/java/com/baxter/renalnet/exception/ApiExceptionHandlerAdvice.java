package com.baxter.renalnet.exception;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandlerAdvice {

    private final Logger log = LoggerFactory.getLogger(ApiExceptionHandlerAdvice.class);

    @Autowired
    private ModelMapper modelMapper;

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleException(ApiException e) {
        log.debug("Erro esperado.", e);
        ApiExceptionDto dto = modelMapper.map(e, ApiExceptionDto.class);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }
}
