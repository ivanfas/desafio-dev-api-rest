package com.deck.desafio.service;

import com.deck.desafio.dto.*;
import com.deck.desafio.exception.ApiException;
import com.deck.desafio.exception.ApiExceptionDomain;
import com.deck.desafio.model.Conta;
import com.deck.desafio.repository.ContaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	public Optional<ContaResponse> find(Long id) {
		return contaRepository.findById(id).map(this::convertToResponse);
	}

	public Long create(@Valid ContaCreate dto) {
		Conta conta = convertToEntity(dto);
		LocalDateTime now = LocalDateTime.now();
		conta.setDataAtualizacao(now);
		conta.setDataCriacao(now);
		conta.setIdConta(null);
		conta.setFlagAtivo(true);
		contaRepository.save(conta);
		return conta.getIdConta();
	}

	public void remove(@Valid Long id) throws ApiException {
		Conta original = contaRepository.findById(id)
				.orElseThrow(() -> new ApiException(Conta.class.getSimpleName(), id.toString(), ApiExceptionDomain.ExceptionType.NOT_FOUND));
		original.setFlagAtivo(false);
		original.setDataAtualizacao(LocalDateTime.now());
		contaRepository.save(original);
	}

	private ContaResponse convertToResponse(Conta conta) {
		return modelMapper.map(conta, ContaResponse.class);
	}

	private Conta convertToEntity(ContaCreate contaDto) {
		return modelMapper.map(contaDto, Conta.class);
	}

	private Conta convertToEntity(ContaFilter filter) {
		return modelMapper.map(filter, Conta.class);
	}

}
