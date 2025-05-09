package com.ebooksystem.ebookmanagementsystem.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Strucutre of the Login Request body. Annotated so that it can only take certain values, restricts others
 */
@Data
public class LoginRequest {
    @NotBlank(message = "Email is required")
    @Email
    private String email;
    @NotBlank(message = "Password is required")
    private String password;
}
