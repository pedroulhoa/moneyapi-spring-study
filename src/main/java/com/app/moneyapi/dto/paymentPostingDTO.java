package com.app.moneyapi.dto;

import com.app.moneyapi.entity.enums.paymentPostingType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class paymentPostingDTO {

    private Long id;
    private String description;
    private LocalDate expirationDate;
    private LocalDate paymentDate;
    private BigDecimal amount;
    private paymentPostingType type;
    private String category;
    private String people;

    public paymentPostingDTO(Long id, String description, LocalDate expirationDate, LocalDate paymentDate, BigDecimal amount, paymentPostingType type, String category, String people) {
        this.id = id;
        this.description = description;
        this.expirationDate = expirationDate;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.people = people;
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

    public paymentPostingType getType() {
        return type;
    }

    public void setType(paymentPostingType type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }
}
