package com.ebooksystem.ebookmanagementsystem.mappers;

import com.ebooksystem.ebookmanagementsystem.dtos.RegisterUserRequest;
import com.ebooksystem.ebookmanagementsystem.dtos.UpdateUserRequest;
import com.ebooksystem.ebookmanagementsystem.dtos.UserDto;
import com.ebooksystem.ebookmanagementsystem.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
/**
 * Mapper for the Ebook class.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    /**
     * Translates User object into its DTO object
     * @param user
     * @return
     */
    UserDto toDto(User user);

    /**
     * Translates the Register request into a User Object. Used for inputting new user into the database
     * @param request
     * @return
     */
    User toEntity(RegisterUserRequest request);

    /**
     * Updated user
     * @param request
     * @param user
     */
    void update(UpdateUserRequest request, @MappingTarget User user);
}
