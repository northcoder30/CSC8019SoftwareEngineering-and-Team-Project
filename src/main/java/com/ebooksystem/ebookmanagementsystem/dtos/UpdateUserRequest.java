package com.ebooksystem.ebookmanagementsystem.dtos;

import com.ebooksystem.ebookmanagementsystem.validation.Lowercase;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Strucutre of the update user request body. Annotated so that it can only take certain values, restricts others
 */
@Data
public class UpdateUserRequest {
    @NotNull
    public String name;
    @NotNull
    @Lowercase
    public String email;
}
