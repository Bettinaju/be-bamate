package com.bamate.bamatebackend.account.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "supervisor")
public class Supervisor extends Account {

    private String description;
    private boolean available = true;

    public Supervisor(String email, String firstName, String lastName, String password, Role role, String description) {
        super(email, firstName, lastName, password, role);
        this.description = description;
    }

    public Supervisor() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailability(boolean available) {
        this.available = available;
    }
}
