package com.bamate.bamatebackend.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * {@code JwtService} is a service class responsible for generating, validating, and extracting
 * information from JSON Web Tokens (JWT).
 */
@Service
public class JwtService {
    private static final String SECRET_KEY = "gCwJlrkIPIEjVRlp9QqWOgboY4tDncW9IPIEjVRlp9QqWOggCwJlrkIadasdasda9"; // FIXME: USE secure secret
    private static final int HALF_HOUR = 1000 * 60 * 30;

    /**
     * Extracts the {@code username} from the JWT token.
     * @param token The JWT token from which the {@code username} is to be extracted.
     * @return The {@code username} extracted from the token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Generates a JWT token for the provided {@code UserDetails}.
     * @param userDetails The {@code UserDetails} object containing user information.
     * @return The generated JWT token.
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Generates a JWT token with extra claims for the provided {@code UserDetails}.
     * @param extraClaims Additional claims to include in the token.
     * @param userDetails The {@code UserDetails} object containing user information.
     * @return The generated JWT token.
     */
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + HALF_HOUR))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Checks if the JWT token is valid for the provided {@code UserDetails}.
     * @param token The JWT token to validate.
     * @param userDetails The {@code UserDetails} object to compare against the token.
     * @return True if the token is valid for the {@code user}, false otherwise.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * Checks if the JWT token has expired.
     * @param token The JWT token to check for expiration.
     * @return True if the token has expired, false otherwise.
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extracts the expiration date from the JWT token.
     * @param token The JWT token from which to extract the expiration date.
     * @return The expiration date of the token.
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extracts a specific claim from the JWT token.
     * @param token The JWT token from which to extract the claim.
     * @param claimsResolver Function to resolve the claim from the token.
     * @param <T> The type of the claim.
     * @return The resolved claim.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extracts all claims from the JWT token.
     * @param token The JWT token from which to extract the claims.
     * @return All claims extracted from the token.
     */
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Retrieves the signing key used to validate the JWT token.
     * @return The signing key.
     */
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
