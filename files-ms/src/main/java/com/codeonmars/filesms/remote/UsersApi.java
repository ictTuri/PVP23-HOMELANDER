package com.codeonmars.filesms.remote;

import com.codeonmars.commonsms.security.UserContext;
import com.codeonmars.commonsms.security.UserContextRetriever;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Optional;

@FeignClient(value = "users-ms")
public interface UsersApi extends UserContextRetriever {

    @GetMapping("/users/context/{credential}")
    Optional<UserContext> retrieveUserContext(@PathVariable("credential") String credential);

    @PutMapping("/users/internal/{uuid}")
    void setProfileUUID(@PathVariable("uuid") String uuid);
}
