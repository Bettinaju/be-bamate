package com.bamate.bamatebackend.supervisor.models;

import com.bamate.bamatebackend.account.models.Account;
import com.bamate.bamatebackend.account.models.Role;
import jakarta.persistence.*;
import java.util.EnumSet;
import java.util.Set;

@Entity
@Table(name = "supervisor")
public class Supervisor extends Account {

    private String description;
    private boolean available = true;

    @ElementCollection(targetClass = Interest.class)
    @CollectionTable(name = "supervisor_interests", joinColumns = @JoinColumn(name = "supervisor_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "interest", nullable = true)
    private Set<Interest> interests = EnumSet.noneOf(Interest.class);

    public Supervisor(String email, String firstName, String lastName, String password, Role role, String description, Set<Interest> interests) {
        super(email, firstName, lastName, password, role);
        this.description = description;
        this.interests = interests;
    }

    public Supervisor(Account account)
    {
        super(account.getEmail(), account.getFirstName(), account.getLastName(), account.getPassword(), Role.SUPERVISOR);
        this.description = "";
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

    public Set<Interest> getInterests() {
        return interests;
    }

    public void setInterests(Set<Interest> interests) {
        this.interests = interests;
    }

    public static Set<Interest> getPredefinedInterests() {
        return EnumSet.allOf(Interest.class);
    }
}
