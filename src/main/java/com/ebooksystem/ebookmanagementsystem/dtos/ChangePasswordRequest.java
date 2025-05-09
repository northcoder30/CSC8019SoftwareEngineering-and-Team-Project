package com.ebooksystem.ebookmanagementsystem.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Structure of the body of the change password request. Annotated so that it can only take certain values, restricts others
 */
@Data
public class ChangePasswordRequest {
    @NotNull
    @Min(value = 6)
    @Max(value = 25)
    private String oldPassword;
    @NotNull
    @Min(value = 6)
    @Max(value = 25)
    private String newPassword;
}
