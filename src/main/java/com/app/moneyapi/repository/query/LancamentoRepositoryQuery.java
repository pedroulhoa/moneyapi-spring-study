package com.app.moneyapi.repository.query;

import com.app.moneyapi.entity.Lancamento;
import com.app.moneyapi.repository.filter.LancamentoFilter;

import java.util.List;

public interface LancamentoRepositoryQuery {

    public List<Lancamento> searchByFilter(LancamentoFilter lancamentoFilter);
}
