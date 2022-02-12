package com.getir.bookstore.security.controller;

import com.getir.bookstore.security.jwt.JwtUtil;
import com.getir.bookstore.security.model.AuthenticationRequest;
import com.getir.bookstore.security.model.AuthenticationResponse;
import com.getir.bookstore.security.service.AuthenticationProviderService;
import com.getir.bookstore.security.service.JpaUserDetailsService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationProviderService authenticationManager;

    private final JpaUserDetailsService userDetailsService;

    private final JwtUtil jwtUtil;

    public AuthenticationController(
            AuthenticationProviderService authenticationManager,
            JpaUserDetailsService userDetailsService,
            JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("auth/signin")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwt).body(new AuthenticationResponse(jwt));
    }
}
