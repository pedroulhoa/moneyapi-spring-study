package com.app.moneyapi.service;

import com.app.moneyapi.entity.Lancamento;
import com.app.moneyapi.entity.Pessoa;
import com.app.moneyapi.exception.PessoaInexistenteOuInativaException;
import com.app.moneyapi.repository.LancamentoRepository;
import com.app.moneyapi.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Lancamento save(Lancamento lancamento) {
        Pessoa pessoa = pessoaRepository.findById(lancamento.getPessoa().getId())
                .filter(p -> !p.isInativo())
                .orElseThrow(() -> {
                    throw new PessoaInexistenteOuInativaException();
                });

        return lancamentoRepository.save(lancamento);
    }
}
