package com.app.moneyapi.entity;

import com.app.moneyapi.dto.request.PeopleRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "people")
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @Embedded
    private Address address;

    @NotNull
    private Boolean active;

    public People() {
    }

    public People(PeopleRequest peopleRequest) {
        this.id = peopleRequest.getId();
        this.name = peopleRequest.getName();
        this.address = peopleRequest.getAddress();
        this.active = peopleRequest.getActive();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @JsonIgnore
    @Transient
    public boolean isInactive() {
        return !this.active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        People people = (People) o;
        return Objects.equals(id, people.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
