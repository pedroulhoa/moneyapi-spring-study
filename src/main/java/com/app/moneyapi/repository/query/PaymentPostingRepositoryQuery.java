package com.app.moneyapi.repository.query;

import com.app.moneyapi.dto.paymentPostingDTO;
import com.app.moneyapi.entity.PaymentPosting;
import com.app.moneyapi.repository.filter.PaymentPostingFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaymentPostingRepositoryQuery {

    public Page<PaymentPosting> searchByFilter(PaymentPostingFilter paymentPostingFilter, Pageable pageable);
    public Page<paymentPostingDTO> getResumePaymentPosting(PaymentPostingFilter paymentPostingFilter, Pageable pageable);

}
