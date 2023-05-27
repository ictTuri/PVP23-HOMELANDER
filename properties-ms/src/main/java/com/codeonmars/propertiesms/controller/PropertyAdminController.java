package com.codeonmars.propertiesms.controller;

import com.codeonmars.commonsms.security.UserContextHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/properties/admin")
public class PropertyAdminController {

    private static final Logger logger = LogManager.getLogger(PropertyAdminController.class);

    @GetMapping
    public ResponseEntity<List<String>> getProperties(){
        var context = UserContextHolder.getContext();
        logger.info(String.valueOf(context));
        return new ResponseEntity<List<String>>(List.of("hello", "world"), HttpStatus.OK);
    }
}
