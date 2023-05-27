package com.codeonmars.issuesms.config;

import com.codeonmars.issuesms.repository.IssuesRepository;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import org.springframework.beans.factory.annotation.Autowired;

@ChangeLog
public class DatabaseChangeLog {

    @ChangeSet(order = "0001", id = "seed", author = "Artur Molla")
    public void databaseSeed(@Autowired IssuesRepository repository){
    }

}
