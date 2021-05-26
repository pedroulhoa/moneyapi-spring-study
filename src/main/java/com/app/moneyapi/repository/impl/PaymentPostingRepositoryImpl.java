package com.app.moneyapi.repository.impl;

import com.app.moneyapi.dto.paymentPostingDTO;
import com.app.moneyapi.entity.PaymentPosting;
import com.app.moneyapi.repository.filter.PaymentPostingFilter;
import com.app.moneyapi.repository.query.PaymentPostingRepositoryQuery;
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

public class PaymentPostingRepositoryImpl implements PaymentPostingRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<PaymentPosting> searchByFilter(PaymentPostingFilter paymentPostingFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<PaymentPosting> criteria = builder.createQuery(PaymentPosting.class);
        Root<PaymentPosting> root = criteria.from(PaymentPosting.class);

        Predicate[] predicates = createFiltersPaymentPosting(paymentPostingFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<PaymentPosting> query = manager.createQuery(criteria);
        addPagination(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(paymentPostingFilter));
    }

    @Override
    public Page<paymentPostingDTO> getResumePaymentPosting(PaymentPostingFilter paymentPostingFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<paymentPostingDTO> criteria = builder.createQuery(paymentPostingDTO.class);
        Root<PaymentPosting> root = criteria.from(PaymentPosting.class);

        criteria.select(builder.construct(paymentPostingDTO.class,
                root.get("id"),
                root.get("description"),
                root.get("expirationDate"),
                root.get("paymentDate"),
                root.get("amount"),
                root.get("type"),
                root.get("category").get("name"),
                root.get("people").get("name")));

        Predicate[] predicates = createFiltersPaymentPosting(paymentPostingFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<paymentPostingDTO> query = manager.createQuery(criteria);
        addPagination(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(paymentPostingFilter));
    }

    private Predicate[] createFiltersPaymentPosting(PaymentPostingFilter paymentPostingFilter, CriteriaBuilder builder, Root<PaymentPosting> root) {

        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(paymentPostingFilter.getDescription())) {
            predicates.add(
                    builder.like(builder.lower(root.get("description")),
                            "%" + paymentPostingFilter.getDescription().toLowerCase() + "%")
            );
        }

        if (paymentPostingFilter.getExpirationDateStart() != null) {
            predicates.add(
                    builder.greaterThanOrEqualTo(root.get("expirationDate"), paymentPostingFilter.getExpirationDateStart())
            );
        }

        if (paymentPostingFilter.getExpirationDateEnd() != null) {
            predicates.add(
                    builder.lessThanOrEqualTo(root.get("expirationDate"), paymentPostingFilter.getExpirationDateEnd())
            );
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void addPagination(TypedQuery<?> query, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int totalRecordsPerPage = pageable.getPageSize();
        int firstPageRecord = currentPage * totalRecordsPerPage;

        query.setFirstResult(firstPageRecord);
        query.setMaxResults(totalRecordsPerPage);
    }

    private Long total(PaymentPostingFilter paymentPostingFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<PaymentPosting> root = criteria.from(PaymentPosting.class);

        Predicate[] predicates = createFiltersPaymentPosting(paymentPostingFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }
}
