package com.bamate.bamatebackend.account.dto;

import com.bamate.bamatebackend.account.models.Role;
import com.bamate.bamatebackend.supervisor.models.Interest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupervisorDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private String description;
    private boolean available;
    private Set<Interest> interests;
}
