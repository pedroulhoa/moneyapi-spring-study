package com.app.moneyapi.service;

import com.app.moneyapi.entity.Pessoa;
import com.app.moneyapi.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa update(Long codigo, Pessoa pessoa) {
        Pessoa pessoaSave = pessoaRepository.findById(codigo)
                .orElseThrow(() -> {
                    throw new EmptyResultDataAccessException(1);
                });
        BeanUtils.copyProperties(pessoa, pessoaSave, "id");
        return pessoaRepository.save(pessoaSave);
    }
}
