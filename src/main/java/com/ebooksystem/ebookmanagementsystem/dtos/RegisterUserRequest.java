package com.ebooksystem.ebookmanagementsystem.dtos;

import com.ebooksystem.ebookmanagementsystem.validation.Lowercase;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Structure of the Register Request Body. Annotated so that it can only take certain values, restricts others
 */
@Data
public class RegisterUserRequest {
    @NotBlank(message = "Name is required") // Not empty string or whitespace
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Lowercase(message = "Email must be in lowercase")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max= 25, message = "Password must be between 6 and 25 characters long")
    private String password;
}
