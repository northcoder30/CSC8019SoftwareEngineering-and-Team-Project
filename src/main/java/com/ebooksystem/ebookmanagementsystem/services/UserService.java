package com.ebooksystem.ebookmanagementsystem.services;

import com.ebooksystem.ebookmanagementsystem.dtos.WishlistDTO;
import com.ebooksystem.ebookmanagementsystem.exceptions.UserNotFoundException;
import com.ebooksystem.ebookmanagementsystem.exceptions.WishlistNotFoundException;
import com.ebooksystem.ebookmanagementsystem.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

/**
 * UserService class implements the logic required to process the frontend requests received by the controller.
 * Implements built in UserDetailsService and adds extra functions.
 */
@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Uses spring framework User object defined in security package,
        // not the User object defined in this program
        return new User(user.getEmail(), user.getPassword(), Collections.emptyList());
    }
}


