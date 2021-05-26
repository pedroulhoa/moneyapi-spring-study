package com.app.moneyapi.entity;

import com.app.moneyapi.entity.enums.paymentPostingType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "payment_posting")
public class PaymentPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String description;

    @NotNull
    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @NotNull
    private BigDecimal amount;

    private String note;

    @NotNull
    @Enumerated(EnumType.STRING)
    private paymentPostingType type;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_code")
    private Category category;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "people_code")
    private People people;

    public PaymentPosting() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentPosting that = (PaymentPosting) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
