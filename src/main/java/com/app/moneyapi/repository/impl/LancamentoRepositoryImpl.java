package com.app.moneyapi.repository.impl;

import com.app.moneyapi.dto.LancamentoDTO;
import com.app.moneyapi.entity.Lancamento;
import com.app.moneyapi.repository.filter.LancamentoFilter;
import com.app.moneyapi.repository.query.LancamentoRepositoryQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Lancamento> searchByFilter(LancamentoFilter lancamentoFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
        Root<Lancamento> root = criteria.from(Lancamento.class);

        Predicate[] predicates = criarFiltrosLancamento(lancamentoFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Lancamento> query = manager.createQuery(criteria);
        addPaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter));
    }

    @Override
    public Page<LancamentoDTO> getResumeLancamento(LancamentoFilter lancamentoFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<LancamentoDTO> criteria = builder.createQuery(LancamentoDTO.class);
        Root<Lancamento> root = criteria.from(Lancamento.class);

        criteria.select(builder.construct(LancamentoDTO.class,
                root.get("id"),
                root.get("descricao"),
                root.get("dataVencimento"),
                root.get("dataPagamento"),
                root.get("valor"),
                root.get("tipo"),
                root.get("categoria").get("nome"),
                root.get("pessoa").get("nome")));

        Predicate[] predicates = criarFiltrosLancamento(lancamentoFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<LancamentoDTO> query = manager.createQuery(criteria);
        addPaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter));
    }

    private Predicate[] criarFiltrosLancamento(LancamentoFilter lancamentoFilter, CriteriaBuilder builder, Root<Lancamento> root) {

        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(lancamentoFilter.getDescricao())) {
            predicates.add(
                    builder.like(builder.lower(root.get("descricao")),
                            "%" + lancamentoFilter.getDescricao().toLowerCase() + "%")
            );
        }

        if (lancamentoFilter.getDataVencimentoInicio() != null) {
            predicates.add(
                    builder.greaterThanOrEqualTo(root.get("dataVencimento"), lancamentoFilter.getDataVencimentoInicio())
            );
        }

        if (lancamentoFilter.getDataVencimentoFim() != null) {
            predicates.add(
                    builder.lessThanOrEqualTo(root.get("dataVencimento"), lancamentoFilter.getDataVencimentoFim())
            );
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void addPaginacao(TypedQuery<?> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }

    private Long total(LancamentoFilter lancamentoFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Lancamento> root = criteria.from(Lancamento.class);

        Predicate[] predicates = criarFiltrosLancamento(lancamentoFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }
}
