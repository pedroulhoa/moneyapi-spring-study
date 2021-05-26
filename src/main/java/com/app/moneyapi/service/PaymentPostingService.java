package com.app.moneyapi.service;

import com.app.moneyapi.entity.PaymentPosting;
import com.app.moneyapi.entity.People;
import com.app.moneyapi.exception.NonExistentOrInactivePersonException;
import com.app.moneyapi.repository.PaymentPostingRepository;
import com.app.moneyapi.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentPostingService {

    @Autowired
    private PaymentPostingRepository paymentPostingRepository;

    @Autowired
    private PeopleRepository peopleRepository;

    public PaymentPosting save(PaymentPosting paymentPosting) {
        People people = peopleRepository.findById(paymentPosting.getPeople().getId())
                .filter(p -> !p.isInactive())
                .orElseThrow(() -> {
                    throw new NonExistentOrInactivePersonException();
                });

        return paymentPostingRepository.save(paymentPosting);
    }
}
