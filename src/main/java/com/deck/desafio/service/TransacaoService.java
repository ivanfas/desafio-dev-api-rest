package com.deck.desafio.service;

import com.deck.desafio.dto.*;
import com.deck.desafio.exception.ApiException;
import com.deck.desafio.exception.ApiExceptionDomain;
import com.deck.desafio.model.Conta;

import com.deck.desafio.model.Transacao;
import com.deck.desafio.repository.ContaRepository;
import com.deck.desafio.repository.TransacaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransacaoService {

	@Autowired
	private TransacaoRepository transacaoRepository;

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<TransacaoResponse> find(TransacaoFilter filter) {
		Transacao transacaoExample = convertToEntity(filter);

		List<TransacaoResponse> result = new ArrayList<>();

		int page = filter.getPage() != null ? filter.getPage() : 0;
		int size = filter.getSize() != null && filter.getSize() < 1000 ? filter.getSize() : 1000;
		Pageable basePage = PageRequest.of(page, size);

		Example<Transacao> example = Example.of(transacaoExample);

		Page<Transacao> queryResult = transacaoRepository.findAll(example, basePage);
		queryResult.map(transacao -> result.add(convertToResponse(transacao)));
		return result;
	}

	public Optional<TransacaoResponse> find(Long id) {
		return transacaoRepository.findById(id).map(this::convertToResponse);
	}

	@Transactional
	public Long createDeposit(@Valid TransacaoCreateDeposit dto) throws ApiException {
		Conta conta = validateConta(dto.getIdConta());
		BigDecimal newValue = dto.getValor().add(conta.getSaldo());
		conta.setSaldo(newValue);

		Transacao transacao = Transacao.builder()
				.conta(conta)
				.dataTransacao(LocalDateTime.now())
				.valor(dto.getValor())
				.build();

		contaRepository.save(conta);
		transacaoRepository.save(transacao);
		return transacao.getIdTransacao();
	}

	public Long createWithdrawal(@Valid TransacaoCreateWithdrawal dto) throws ApiException {
		Conta conta = validateConta(dto.getIdConta());
		BigDecimal newValue = dto.getValor().subtract(conta.getSaldo());
		conta.setSaldo(newValue);

		Transacao transacao = Transacao.builder()
				.conta(conta)
				.dataTransacao(LocalDateTime.now())
				.valor(dto.getValor().negate())
				.build();

		contaRepository.save(conta);
		transacaoRepository.save(transacao);
		return transacao.getIdTransacao();
	}

	private Conta validateConta(Long idConta) throws ApiException {
		Conta conta = contaRepository.findById(idConta)
				.orElseThrow(() -> new ApiException(Conta.class.getSimpleName(), idConta.toString(), ApiExceptionDomain.ExceptionType.NOT_FOUND));
		if(!conta.isFlagAtivo()){
			throw new ApiException(Conta.class.getSimpleName(), idConta.toString(), ApiExceptionDomain.ExceptionType.NOT_ACTIVE);
		}
		return conta;
	}

	private TransacaoResponse convertToResponse(Transacao transacao) {
		return modelMapper.map(transacao, TransacaoResponse.class);
	}

	private Transacao convertToEntity(TransacaoFilter filter) {
		return modelMapper.map(filter, Transacao.class);
	}

}
