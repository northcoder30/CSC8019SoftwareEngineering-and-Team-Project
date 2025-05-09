package com.ebooksystem.ebookmanagementsystem.services;

import com.ebooksystem.ebookmanagementsystem.config.JwtConfig;
import com.ebooksystem.ebookmanagementsystem.entities.Role;
import com.ebooksystem.ebookmanagementsystem.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * JwtService that implements the logic behind token based authentication.
 */
@AllArgsConstructor
@Service
public class JwtService {
    private final JwtConfig jwtConfig;

    /**
     * Generates a new access token
     * @param user
     * @return
     */
    public Jwt generateAccessToken(User user) {

        return generateToken(user, jwtConfig.getAccessTokenExpiration());
    }

    /**
     * Generates a new refresh token
     * @param user
     * @return
     */
    public Jwt generateRefreshToken(User user) {

        return generateToken(user, jwtConfig.getRefreshTokenExpiration());
    }

    /**
     * Helper function that generates either an access or refresh token, based on the request
     * @param user
     * @param tokenExpiration
     * @return
     */
    private Jwt generateToken(User user, long tokenExpiration) {
        var claims = Jwts.claims()
                .subject(user.getId().toString())
                .add("email", user.getEmail())
                .add("name", user.getName())
                .add("role", user.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * tokenExpiration))
                .build();

        return new Jwt(claims, jwtConfig.getSecretKey());
    }

    /**
     * Parses the token
     * @param token
     * @return
     */
    public Jwt parseToken(String token) {
        try{
            var claims = getClaims(token);
            return new Jwt(claims, jwtConfig.getSecretKey());
        }
        catch (JwtException e){
            return null;
        }
    }

    /**
     * Gets claims of the token
     * @param token
     * @return
     */
    private Claims getClaims(String token) {
        return Jwts.parser()
                    .verifyWith(jwtConfig.getSecretKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
    }

}
