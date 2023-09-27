package com.codeonmars.usersms.service;

import com.codeonmars.usersms.exception.CustomMessageException;
import com.codeonmars.usersms.model.user.UserEntity;
import com.codeonmars.usersms.model.user.dto.ResponseDto;
import com.codeonmars.usersms.model.user.dto.UserRegisterDto;
import com.codeonmars.usersms.repository.UserRepository;
import com.codeonmars.usersms.security.auth.AuthResponse;
import com.codeonmars.usersms.security.dto.UsernameAndPasswordAuthenticationRequest;
import com.codeonmars.usersms.security.jwt.JwtConfig;
import com.codeonmars.usersms.security.jwt.JwtTokenProvider;
import com.github.dozermapper.core.Mapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;

@Service
@Transactional
public class UserAuthenticationService {

    private static final String USER_NOT_AUTHENTICATED = "Unable to Authenticate! Check credential and password!";
    public static final String USERNAME_OR_EMAIL_ALREADY_EXISTS = "Username or email already exists";
    public static final String USER_SUCCESSFULLY_SAVED = "User successfully saved";
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final Mapper dozer;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtConfig jwtConfig;

    public UserAuthenticationService(UserRepository userRepository, PasswordEncoder encoder, Mapper dozer, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, JwtConfig jwtConfig) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.dozer = dozer;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtConfig = jwtConfig;
    }

    public AuthResponse login(UsernameAndPasswordAuthenticationRequest credentials) {
        String token;
        try {
            var authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentials.getCredential(), credentials.getPassword()));
            token = jwtTokenProvider.createToken(authenticate);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException(USER_NOT_AUTHENTICATED);
        }
        return new AuthResponse(token, credentials.getCredential(), Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())));
    }

    public ResponseDto registerNewUser(UserRegisterDto request) {
        if (emailExists(request.getEmail()) || usernameExists(request.getUsername())) {
            throw new CustomMessageException(USERNAME_OR_EMAIL_ALREADY_EXISTS);
        }
        var newUser = dozer.map(request, UserEntity.class);
        newUser.setPassword(encoder.encode(request.getPassword()));
        newUser.initializeData();
        userRepository.saveAndFlush(newUser);
        return new ResponseDto("CREATED", USER_SUCCESSFULLY_SAVED);
    }

    /* SUPPORTING METHODS */

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

}
