package com.codeonmars.issuesms.remote;

import com.codeonmars.commonsms.security.UserContext;
import com.codeonmars.commonsms.security.UserContextRetriever;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(value = "users-ms")
public interface UsersApi extends UserContextRetriever {
    @GetMapping("/users/context/{credential}")
    Optional<UserContext> retrieveUserContext(@PathVariable("credential") String credential);

    @GetMapping("/users/user/get-simple-user-data")
    ResponseEntity<SimpleUserData> getSimpleUserData(@RequestParam(name = "email") String email);

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class SimpleUserData{
        private String username;
        private String email;
    }
}
