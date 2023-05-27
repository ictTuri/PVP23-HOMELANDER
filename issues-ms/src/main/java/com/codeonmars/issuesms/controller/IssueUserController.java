package com.codeonmars.issuesms.controller;

import com.codeonmars.issuesms.model.issues.dto.IssueRequest;
import com.codeonmars.issuesms.service.IssueUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/issues/user")
public class IssueUserController {

    @Autowired
    private IssueUserService service;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createIssue(@RequestBody IssueRequest request){
        service.createNewIssue(request);
    }
}
