package com.deck.desafio.controller.impl;

import com.deck.desafio.controller.ContaController;
import com.deck.desafio.exception.ApiException;
import com.deck.desafio.service.ContaService;
import com.deck.desafio.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api/contas")
class ContaControllerImpl implements ContaController {

	private final Logger log = LoggerFactory.getLogger(ContaControllerImpl.class);

	@Autowired
	private ContaService contaService;


	@Override
	public ResponseEntity<ApiResponse> create(ContaCreate dto) throws URISyntaxException {
		log.trace("Request to create conta: {}", dto);
		Long id = contaService.create(dto);
		return ResponseEntity.created(new URI("/api/contas/" + id))
				.body(new ApiResponse(id, HttpStatus.CREATED));
	}

	@Override
	public ResponseEntity<?> findOneSaldo(Long id) {
		Optional<ContaResponse> result = contaService.find(id);
		return result.map(response -> ResponseEntity.ok().body(response)).orElse(ResponseEntity.notFound().build());
	}

	@Override
	public ResponseEntity<?> block(Long id) throws ApiException {
		log.trace("Request to block conta: {}", id);
		contaService.remove(id);
		return ResponseEntity.ok().body("conta bloqueada: " + id);
	}

}