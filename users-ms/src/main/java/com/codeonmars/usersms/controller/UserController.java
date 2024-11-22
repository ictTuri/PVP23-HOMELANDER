package com.codeonmars.usersms.controller;

import com.codeonmars.usersms.model.user.dto.FullUserContextDto;
import com.codeonmars.usersms.model.user.dto.UpdateUserRequest;
import com.codeonmars.usersms.model.user.projection.SimpleUserProjection;
import com.codeonmars.usersms.service.UserGeneralService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/user")
public record UserController(UserGeneralService userGeneralService) {

    @GetMapping("/get-simple-user-data")
    ResponseEntity<SimpleUserProjection> getSimpleUserData(@RequestParam(name = "email") String email){
        return new ResponseEntity<>(userGeneralService.getSimpleUserData(email), HttpStatus.OK);
    }

    @GetMapping("/get-full-user-context/{email}")
    ResponseEntity<FullUserContextDto> getFullUserContext(@PathVariable("email") String email){
        return new ResponseEntity<>(userGeneralService.getFullUserContext(email), HttpStatus.OK);
    }

    @PutMapping("/update-user/{email}")
    void updateUserData(@PathVariable("email") String email,
                        @RequestBody UpdateUserRequest request) {
        userGeneralService.updateUserData(email, request);
    }
}
