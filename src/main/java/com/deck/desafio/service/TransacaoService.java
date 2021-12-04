package com.deck.desafio.service;

import com.deck.desafio.dto.*;
import com.deck.desafio.exception.ApiException;
import com.deck.desafio.exception.ApiExceptionDomain;
import com.deck.desafio.model.Conta;

import com.deck.desafio.model.Transacao;
import com.deck.desafio.repository.TransacaoRepository;
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
public class TransacaoService {

	@Autowired
	private TransacaoRepository transacaoRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	public List<TransacaoResponse> find(TransacaoFilter filter) {
		Transacao transacaoExample = convertToEntity(filter);

		List<TransacaoResponse> result = new ArrayList<>();

		int page = filter.getPage() != null ? filter.getPage() : 0;
		int size = filter.getSize() != null && filter.getSize() < 1000 ? filter.getSize() : 1000;
		Pageable basePage = PageRequest.of(page, size);

		/*
		ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAll()
				.withMatcher("comment", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher("active", ExampleMatcher.GenericPropertyMatchers.exact());
		 */

		Example<Transacao> example = Example.of(transacaoExample);

		Page<Transacao> queryResult = transacaoRepository.findAll(example, basePage);
		queryResult.map(transacao -> result.add(convertToResponse(transacao)));

		return result;
	}

	public Optional<TransacaoResponse> find(Long id) {
		return transacaoRepository.findById(id).map(this::convertToResponse);
	}

	public Long createDeposit(@Valid TransacaoCreateDeposit dto) {
		/*
		Transacao transacao = convertToEntity(dto);
		LocalDateTime now = LocalDateTime.now();
		transacao.setDataAtualizacao(now);
		transacao.setDataCriacao(now);
		//transacao.setIdConta(null);
		transacao.setFlagAtivo(true);
		transacaoRepository.save(transacao);
		return transacao.getIdTransacao();

		 */
		return null;
	}

	public Long createWithdrawal(@Valid TransacaoCreateWithdrawal dto) {
		/*
		Transacao transacao = convertToEntity(dto);
		LocalDateTime now = LocalDateTime.now();
		transacao.setDataAtualizacao(now);
		transacao.setDataCriacao(now);
		//transacao.setIdConta(null);
		transacao.setFlagAtivo(true);
		transacaoRepository.save(transacao);
		return transacao.getIdTransacao();
		 */
		return null;
	}

	private TransacaoResponse convertToResponse(Transacao transacao) {
		return modelMapper.map(transacao, TransacaoResponse.class);
	}

	private Transacao convertToEntity(TransacaoCreateDeposit dto) {
		return modelMapper.map(dto, Transacao.class);
	}

	private Transacao convertToEntity(TransacaoFilter filter) {
		return modelMapper.map(filter, Transacao.class);
	}

}
