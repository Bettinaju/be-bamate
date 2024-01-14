package com.bamate.bamatebackend.account.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "account")
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {

    private @Id @GeneratedValue Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Role role;

    public Account(String email, String firstName, String lastName, String password, Role role) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
    }

    public Account() {}


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) { this.role = role; }

    public Role getRole() {
        return role;
    }

    public Long getId() {return id; }



    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (!(o instanceof Account)) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(this.id, account.id) && Objects.equals(this.email, account.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstName, this.lastName, this.email);
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + this.id + ", firstName='" + this.firstName + '\'' + ", lastName='" + this.lastName + '\'' + ", email='" + this.email + '\'' + '}';
    }


}
