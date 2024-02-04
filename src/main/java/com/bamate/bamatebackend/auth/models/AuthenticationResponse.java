package com.bamate.bamatebackend.auth.models;

import com.bamate.bamatebackend.account.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an authentication response sent to the user after successful authentication.
 * This class encapsulates the {@code token}, {@code role}, and {@code email} associated with the authenticated user.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
    private Role role;
    private String email;
}
