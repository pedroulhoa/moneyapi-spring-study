package com.app.moneyapi.repository;

import com.app.moneyapi.entity.PaymentPosting;
import com.app.moneyapi.repository.query.PaymentPostingRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentPostingRepository extends JpaRepository<PaymentPosting, Long>, PaymentPostingRepositoryQuery {
}
