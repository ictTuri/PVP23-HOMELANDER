package com.codeonmars.usersms.controller;

import com.codeonmars.usersms.model.user.dto.ResponseDto;
import com.codeonmars.usersms.model.user.dto.UserRegisterDto;
import com.codeonmars.usersms.service.UserAuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/auth/register")
public class RegisterController {

    private final UserAuthenticationService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto registerUser(@Valid @RequestBody UserRegisterDto request){
        return userService.registerNewUser(request);
    }
}
