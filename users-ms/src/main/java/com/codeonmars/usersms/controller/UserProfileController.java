package com.codeonmars.usersms.controller;

import com.codeonmars.usersms.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/profile")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PutMapping("/update-profile-pic")
    public void updateProfilePic(@RequestParam(name = "currentUUID", required = false) String currentUUID,
                                 @RequestParam(name = "newUUID") String newUUID){
        userProfileService.updateProfilePic(currentUUID, newUUID);
    }
}
