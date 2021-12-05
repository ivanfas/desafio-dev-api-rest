package com.deck.desafio.repository;

import com.deck.desafio.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {


}
