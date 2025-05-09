package com.ebooksystem.ebookmanagementsystem.controllers;

import com.ebooksystem.ebookmanagementsystem.config.JwtConfig;
import com.ebooksystem.ebookmanagementsystem.dtos.JwtResponse;
import com.ebooksystem.ebookmanagementsystem.dtos.LoginRequest;
import com.ebooksystem.ebookmanagementsystem.dtos.UserDto;
import com.ebooksystem.ebookmanagementsystem.mappers.UserMapper;
import com.ebooksystem.ebookmanagementsystem.repositories.UserRepository;
import com.ebooksystem.ebookmanagementsystem.services.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Authentication controller class. Has methods for logging in, refreshing the authentication tokens and getting
 * the existing token for a logged in user. Exposes the nodes of the api for frontend integration
 */
@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final JwtConfig jwtConfig;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * Takes care of logging in an existing user. Checks if the user from the POST request exists, if such user exists,
     * sends an access token as a response, and a refresh token in the cookies
     * @param loginRequest
     * @param response
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest loginRequest,
                                             HttpServletResponse response) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();

        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        var cookie = new Cookie("refreshToken", refreshToken.toString());
        cookie.setHttpOnly(true);
        cookie.setPath("/auth/refresh");
        cookie.setMaxAge(jwtConfig.getRefreshTokenExpiration()); // Expires after 7 days
        cookie.setSecure(true);
        response.addCookie(cookie);

       return ResponseEntity.ok(new JwtResponse(accessToken.toString()));
    }

    /**
     * Refreshes the users acces token by using their refresh token and generates a new access token
     * @param refreshToken
     * @return
     */
    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> refresh(@CookieValue(value = "refreshToken") String refreshToken) {
        var jwt = jwtService.parseToken(refreshToken);
        if(jwt == null || jwt.isExpired()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        var user = userRepository.findById(jwt.getUserId()).orElseThrow();
        var accessToken = jwtService.generateAccessToken(user);

        return ResponseEntity.ok(new JwtResponse(accessToken.toString()));
    }

    /**
     * Verifies if the user is logged in
     * @return
     */
    @GetMapping("/me")
    public ResponseEntity<UserDto> me(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userID = (Long) authentication.getPrincipal();

        var user = userRepository.findById(userID).orElse(null);
        if(user == null){
            return ResponseEntity.notFound().build();
        }

        var userDto = userMapper.toDto(user);

        return ResponseEntity.ok(userDto);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> handleBadCredentialsException(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
