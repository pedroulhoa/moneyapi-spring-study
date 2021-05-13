package com.app.moneyapi.repository;

import com.app.moneyapi.entity.Lancamento;
import com.app.moneyapi.repository.query.LancamentoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {
}
