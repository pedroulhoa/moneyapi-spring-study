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

    public Pessoa update(Long id, Pessoa pessoa) {
        Pessoa pessoaSave = getPessoaById(id);
        BeanUtils.copyProperties(pessoa, pessoaSave, "id");
        return pessoaRepository.save(pessoaSave);
    }

    public void updatePropertieAtivo(Long id, Boolean ativo) {
        Pessoa pessoaSave = getPessoaById(id);
        pessoaSave.setAtivo(ativo);
        pessoaRepository.save(pessoaSave);
    }

    private Pessoa getPessoaById(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> {
                    throw new EmptyResultDataAccessException(1);
                });
    }


}
