
package com.bamate.bamatebackend.supervisor.models;

import com.bamate.bamatebackend.account.models.Account;
import com.bamate.bamatebackend.account.models.Role;
import jakarta.persistence.*;
import java.util.EnumSet;
import java.util.Set;

/**
 * This class represents the {@code supervisor} entity.
 * It extends the {@code Account} class, which represents a user account.
 */
@Entity
@Table(name = "supervisor")
public class Supervisor extends Account {

    private String recentJob;
    private String jobReference;
    private String previousJob;
    private boolean available = true;

    @ElementCollection(targetClass = Interest.class)
    @CollectionTable(name = "supervisor_interests", joinColumns = @JoinColumn(name = "supervisor_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "interest", nullable = true)
    private Set<Interest> interests = EnumSet.noneOf(Interest.class);

    /**
     * Constructs a {@code Supervisor} object with the provided attributes.
     * @param email The {@code email} of the {@code Supervisor}.
     * @param firstName The {@code firstName} of the {@code Supervisor}.
     * @param lastName The {@code lastName} of the {@code Supervisor}.
     * @param password The {@code password} of the {@code Supervisor}.
     * @param role The {@code role} of the {@code Supervisor}.
     * @param recentJob The {@code description} of the {@code Supervisor}.
     * @param previousJob The {@code previousJob} of the {@code Supervisor}.
     * @param jobReference The {@code jobReference} of the {@code Supervisor}.
     * @param interests The {@code interests} of the {@code Supervisor}.
     */
    public Supervisor(String email, String firstName, String lastName, String password, Role role, String recentJob, String previousJob, String jobReference, Set<Interest> interests) {
        super(email, firstName, lastName, password, role);
        this.recentJob = recentJob;
        this.previousJob = previousJob;
        this.jobReference = jobReference;
        this.interests = interests;
    }

    /**
     * Constructs a {@code Supervisor} object from an {@code Account} object.
     * @param account The {@code Account} object from which to construct the {@code Supervisor}.
     */
    public Supervisor(Account account)
    {
        super(account.getEmail(), account.getFirstName(), account.getLastName(), account.getPassword(), Role.SUPERVISOR);
        this.recentJob = "";
        this.previousJob = "";
        this.jobReference = "";
    }
    /**
     * Default constructor for {@code Supervisor} class.
     */
    public Supervisor() {}


    /**
     * The getter method for {@code recentJob}.
     * @return The {@code recentJob} of the {@code Supervisor}.
     */
    public String getRecentJob() {
        return recentJob;
    }

    /**
     * The setter method for {@code recentJob}.
     * @param recentJob The {@code recentJob} of the {@code Supervisor}.
     */
    public void setRecentJob(String recentJob) {
        this.recentJob = recentJob;
    }

    /**
     * The setter method for {@code previousJob}.
     * @param previousJob The {@code previousJob} of the {@code Supervisor}.
     */
    public void setPreviousJob(String previousJob) {
        this.previousJob = previousJob;
    }
    /**
     * The getter method for {@code previousJob}.
     * @return The {@code previousJob} of the {@code Supervisor}.
     */
    public String getPreviousJob() {
        return previousJob;
    }

    /**
     * The setter method for {@code jobReference}.
     * @param jobReference The {@code jobReference} of the {@code Supervisor}.
     */
    public void setJobReference(String jobReference) {
        this.jobReference = jobReference;
    }

    /**
     * The getter method for {@code jobReference}.
     * @return The {@code jobReference} of the {@code Supervisor}.
     */
    public String getJobReference() {
        return jobReference;
    }

    /**
     * Checks if the {@code Supervisor} is available.
     * @return True if the {@code Supervisor} is available, false otherwise.
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Sets the {@code availability} of the {@code Supervisor}.
     * @param available The {@code availability} to set.
     */
    public void setAvailability(boolean available) {
        this.available = available;
    }

    /**
     * Retrieves the {@code interests} of the {@code Supervisor}.
     * @return The {@code interests} of the {@code Supervisor}.
     */
    public Set<Interest> getInterests() {
        return interests;
    }

    /**
     * Sets the {@code interests} of the {@code Supervisor}.
     * @param interests The {@code interests} to set.
     */
    public void setInterests(Set<Interest> interests) {
        this.interests = interests;
    }

    /**
     * Retrieves the predefined {@code interests} available for {@code Supervisor}.
     * @return A set of predefined {@code interests}.
     */
    public static Set<Interest> getPredefinedInterests() {
        return EnumSet.allOf(Interest.class);
    }
}