package com.app.moneyapi.resource;

import com.app.moneyapi.dto.paymentPostingDTO;
import com.app.moneyapi.entity.PaymentPosting;
import com.app.moneyapi.repository.filter.PaymentPostingFilter;
import com.app.moneyapi.event.ResourceCreateEvent;
import com.app.moneyapi.repository.PaymentPostingRepository;
import com.app.moneyapi.service.PaymentPostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/payments-posting")
public class PaymentPostingResource {

    @Autowired
    private PaymentPostingRepository paymentPostingRepository;

    @Autowired
    private PaymentPostingService paymentPostingService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_SEARCH_PAYMENTPOSTING') and #oauth2.hasScope('read')")
    public List<PaymentPosting> getAll() {
        return paymentPostingRepository.findAll();
    }

    @GetMapping(params = "resume")
    @PreAuthorize("hasAuthority('ROLE_SEARCH_PAYMENTPOSTING') and #oauth2.hasScope('read')")
    public Page<paymentPostingDTO> getResumePaymentPosting(PaymentPostingFilter paymentPostingFilter, Pageable pageable) {
        return paymentPostingRepository.getResumePaymentPosting(paymentPostingFilter, pageable);
    }

    @GetMapping("/search")
    @PreAuthorize("hasAuthority('ROLE_SEARCH_PAYMENTPOSTING') and #oauth2.hasScope('read')")
    public Page<PaymentPosting> search(PaymentPostingFilter paymentPostingFilter, Pageable pageable) {
        return paymentPostingRepository.searchByFilter(paymentPostingFilter, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_SEARCH_PAYMENTPOSTING') and #oauth2.hasScope('read')")
    public ResponseEntity<PaymentPosting> get(@PathVariable Long id) {
        return paymentPostingRepository.findById(id)
                .map(l -> ResponseEntity.ok().body(l))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CREATE_PAYMENTPOSTING') and #oauth2.hasScope('white')")
    public ResponseEntity<PaymentPosting> create(@Valid @RequestBody PaymentPosting paymentPosting, HttpServletResponse response) {
        PaymentPosting paymentPostingSave = paymentPostingService.save(paymentPosting);
        publisher.publishEvent(new ResourceCreateEvent(this, response, paymentPostingSave.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentPostingSave);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_REMOVE_PAYMENTPOSTING') and #oauth2.hasScope('white')")
    public void delete(@PathVariable Long id) {
        paymentPostingRepository.deleteById(id);
    }

}
