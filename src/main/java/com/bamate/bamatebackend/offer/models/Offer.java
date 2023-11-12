package com.bamate.bamatebackend.offer.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Offer {

    private @Id @GeneratedValue Long id;

    // TODO: Somehow link offer to supervisor/account
    private String email;
    private String name;
    private Degree degree;
    private String description;
    private Boolean isAvailable;

    public Offer(String email, String name, Degree degree, String description, Boolean isAvailable) {
        this.email = email;
        this.name = name;
        this.degree = degree;
        this.description = description;
        this.isAvailable = isAvailable;
    }

    public Offer() {
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Degree getDegree() {
        return degree;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (!(o instanceof Offer)) {
            return false;
        }
        Offer offer = (Offer) o;
        return Objects.equals(this.id, offer.id) && Objects.equals(this.email, offer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.email);
    }

    @Override
    public String toString() {
        return "Offer{" + "id=" + this.id + ", name='" + this.name + '\'' + ", email='" + this.email + '\'' + '}';
    }
}
