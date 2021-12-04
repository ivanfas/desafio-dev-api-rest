package com.deck.desafio.controller.impl;

import com.deck.desafio.controller.TransacaoController;
import com.deck.desafio.dto.*;
import com.deck.desafio.exception.ApiException;
import com.deck.desafio.service.TransacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

@RestController
@RequestMapping("/api/transacoes")
class TransacaoControllerImpl implements TransacaoController {

	private final Logger log = LoggerFactory.getLogger(TransacaoControllerImpl.class);

	@Autowired
	private TransacaoService transacaoService;

	@Override
	public ResponseEntity<ApiResponse> createDeposit(TransacaoCreateDeposit dto) throws URISyntaxException, ApiException {
		log.trace("Request to create deposit: {}", dto);
		Long id = transacaoService.createDeposit(dto);
		return ResponseEntity.created(new URI("/api/transacoes/" + id))
				.body(new ApiResponse(id, HttpStatus.CREATED));
	}

	@Override
	public ResponseEntity<ApiResponse> createWithdrawal(TransacaoCreateWithdrawal dto) throws URISyntaxException, ApiException {
		log.trace("Request to create withdrawal: {}", dto);
		Long id = transacaoService.createWithdrawal(dto);
		return ResponseEntity.created(new URI("/api/transacoes/" + id))
				.body(new ApiResponse(id, HttpStatus.CREATED));
	}

	@Override
	public Collection<TransacaoResponse> find(TransacaoFilter filter) {
		return transacaoService.find(filter);
	}
}