package com.codeonmars.usersms.security;

import com.codeonmars.usersms.security.auth.AuthResponse;
import com.codeonmars.usersms.security.dto.UsernameAndPasswordAuthenticationRequest;
import com.codeonmars.usersms.service.UserAuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/auth")
public class AuthenticationController {

    private final UserAuthenticationService service;

    public AuthenticationController(UserAuthenticationService service) {
        this.service = service;
    }


    @PostMapping("/_login")
    public ResponseEntity<AuthResponse> login(HttpServletResponse response,
                                             @Valid @RequestBody UsernameAndPasswordAuthenticationRequest credentials) throws InvalidCredentialsException {
        logout();
        return new ResponseEntity<>(service.login(credentials), HttpStatus.OK);
    }

    @PostMapping("/_logout")
    public void logout() {
    }
}
