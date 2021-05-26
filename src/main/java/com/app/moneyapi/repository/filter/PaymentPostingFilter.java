package com.app.moneyapi.repository.filter;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class PaymentPostingFilter {

    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDateStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDateEnd;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getExpirationDateStart() {
        return expirationDateStart;
    }

    public void setExpirationDateStart(LocalDate expirationDateStart) {
        this.expirationDateStart = expirationDateStart;
    }

    public LocalDate getExpirationDateEnd() {
        return expirationDateEnd;
    }

    public void setExpirationDateEnd(LocalDate expirationDateEnd) {
        this.expirationDateEnd = expirationDateEnd;
    }
}
