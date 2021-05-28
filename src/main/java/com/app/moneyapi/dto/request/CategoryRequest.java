package com.app.moneyapi.dto.request;

import com.app.moneyapi.entity.Category;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoryRequest {

    private Long id;

    @NotNull
    @Size(min = 3, max = 20)
    private String name;

    public CategoryRequest(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryRequest(Category category) {
        this.id = category.getId();
        this.name = category.getName();
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

}
