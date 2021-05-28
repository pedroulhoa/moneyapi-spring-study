package com.app.moneyapi.dto.response;

import com.app.moneyapi.entity.Address;
import com.app.moneyapi.entity.People;

import javax.validation.constraints.NotNull;

public class PeopleResponse {

    private Long id;

    @NotNull
    private String name;

    private Address address;

    @NotNull
    private Boolean active;

    public PeopleResponse(People people) {
        this.id = people.getId();
        this.name = people.getName();
        this.address = people.getAddress();
        this.active = people.getActive();
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
}
