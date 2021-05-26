package com.app.moneyapi.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "permission")
public class Permission {

    @Id
    private Long id;
    private String description;

    public Permission() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission permission = (Permission) o;
        return Objects.equals(id, permission.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
