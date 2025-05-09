package com.ebooksystem.ebookmanagementsystem.controllers;

import com.ebooksystem.ebookmanagementsystem.dtos.*;
import com.ebooksystem.ebookmanagementsystem.entities.Role;
import com.ebooksystem.ebookmanagementsystem.exceptions.UserNotFoundException;
import com.ebooksystem.ebookmanagementsystem.exceptions.WishlistNotFoundException;
import com.ebooksystem.ebookmanagementsystem.mappers.UserMapper;
import com.ebooksystem.ebookmanagementsystem.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.Set;

/**
 * /**
 *  Controller for users. Allows the frontend to send requests regarding users by exposing the nodes
 *  and performs operations in the backend and database layers based on those requests. Sends appropriate responses.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    /**
     * Gets all users. Can sort the response based on the request parameter, if no soting parameter is passed, returns
     * all users as they are in the database.
     * @param sortBy
     * @return
     */
    @GetMapping
    public Iterable<UserDto> getAllUsers(
            @RequestParam(required = false, defaultValue = "", name = "sort") String sortBy
    ) {

        if (!Set.of("name", "email").contains(sortBy)) {
            sortBy = "name";
        }

        return userRepository.findAll(Sort.by(sortBy))
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Gets user by their Id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        var user =  userRepository.findById(id).orElse(null);
        if (user == null) {

            return ResponseEntity.notFound().build();
        }


        return ResponseEntity.ok(userMapper.toDto(user));
    }

    /**
     * Handles user registration. Uses a password encoder using a secret key before saving it in the database
     * @param request
     * @param uriBuilder
     * @return
     */
    @PostMapping
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody RegisterUserRequest request,
            UriComponentsBuilder uriBuilder) {
        if (userRepository.existsByEmail(request.getEmail())){
            return ResponseEntity.badRequest().body(
                    Map.of("email", "Email is already registered"));
        }

        var user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);

        var userDto = userMapper.toDto(user);
        var uri = uriBuilder.path("/users/{id}").buildAndExpand(userDto.getId()).toUri();
        return ResponseEntity.created(uri).body(userDto);
    }

    /**
     * Updates the information of the user, finds user by id
     * @param id
     * @param request
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable(name = "id") Long id,
            @RequestBody UpdateUserRequest request){
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        userMapper.update(request, user);
        userRepository.save(user);

        return ResponseEntity.ok(userMapper.toDto(user));


    }

    /**
     * Gets a user by their id and deletes them from the database
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        userRepository.delete(user);
        return ResponseEntity.noContent().build();
    }

    /**
     * Handles password changing, finds user by id. Requires the old and new password,
     * validates that old password is correct and then updates the user's password to the new one
     * @param id
     * @param request
     * @return
     */
    @PostMapping("/{id}/change-password")
    public ResponseEntity<Void> changePassword(
            @PathVariable Long id,
            @RequestBody ChangePasswordRequest request){
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        if (!user.getPassword().equals(request.getOldPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        user.setPassword(request.getNewPassword());
        userRepository.save(user);

        return ResponseEntity.noContent().build();
    }



    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "User not found."));
    }

}
