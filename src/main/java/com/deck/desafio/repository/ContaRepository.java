package com.deck.desafio.repository;

import com.deck.desafio.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {

}
