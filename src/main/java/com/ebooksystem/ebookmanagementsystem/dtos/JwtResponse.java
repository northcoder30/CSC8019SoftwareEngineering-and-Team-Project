package com.ebooksystem.ebookmanagementsystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Strucutre of the JWT Response, used for authentication, sends a valid authentication token
 */
@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
}
