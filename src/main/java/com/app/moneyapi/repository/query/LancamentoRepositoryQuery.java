package com.app.moneyapi.repository.query;

import com.app.moneyapi.entity.Lancamento;
import com.app.moneyapi.repository.filter.LancamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LancamentoRepositoryQuery {

    public Page<Lancamento> searchByFilter(LancamentoFilter lancamentoFilter, Pageable pageable);
}
