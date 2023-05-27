package com.codeonmars.usersms.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Set;

@FeignClient(value = "properties-ms")
public interface PropertiesApi {


    @GetMapping("/properties/internal/user-properties")
    Map<String, Set<Long>> getUsersProperties(@RequestParam(name = "username") String username,
                                              @RequestParam(name = "email") String email);
}
