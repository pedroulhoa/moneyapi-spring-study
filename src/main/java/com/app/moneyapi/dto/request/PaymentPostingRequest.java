package com.app.moneyapi.dto.request;

import com.app.moneyapi.entity.Category;
import com.app.moneyapi.entity.PaymentPosting;
import com.app.moneyapi.entity.People;
import com.app.moneyapi.entity.enums.paymentPostingType;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class PaymentPostingRequest {

    private Long id;

    @NotNull
    private String description;

    @NotNull
    private LocalDate expirationDate;

    private LocalDate paymentDate;

    @NotNull
    private BigDecimal amount;

    private String note;

    @NotNull
    private paymentPostingType type;

    @NotNull
    private Category category;

    @NotNull
    private People people;

    public PaymentPostingRequest(Long id, String description, LocalDate expirationDate, LocalDate paymentDate, BigDecimal amount, String note, paymentPostingType type, Category category, People people) {
        this.id = id;
        this.description = description;
        this.expirationDate = expirationDate;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.note = note;
        this.type = type;
        this.category = category;
        this.people = people;
    }

    public PaymentPostingRequest(PaymentPosting paymentPosting) {
        this.id = paymentPosting.getId();
        this.description = paymentPosting.getDescription();
        this.expirationDate = paymentPosting.getExpirationDate();
        this.paymentDate = paymentPosting.getPaymentDate();
        this.amount = paymentPosting.getAmount();
        this.note = paymentPosting.getNote();
        this.type = paymentPosting.getType();
        this.category = paymentPosting.getCategory();
        this.people = paymentPosting.getPeople();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public paymentPostingType getType() {
        return type;
    }

    public void setType(paymentPostingType type) {
        this.type = type;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }
}
