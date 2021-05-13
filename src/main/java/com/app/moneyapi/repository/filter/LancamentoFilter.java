package com.app.moneyapi.repository.filter;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class LancamentoFilter {

    private String descricao;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataVencimentoInicio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataVencimentoFim;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataVencimentoInicio() {
        return dataVencimentoInicio;
    }

    public void setDataVencimentoInicio(LocalDate dataVencimentoInicio) {
        this.dataVencimentoInicio = dataVencimentoInicio;
    }

    public LocalDate getDataVencimentoFim() {
        return dataVencimentoFim;
    }

    public void setDataVencimentoFim(LocalDate dataVencimentoFim) {
        this.dataVencimentoFim = dataVencimentoFim;
    }
}
