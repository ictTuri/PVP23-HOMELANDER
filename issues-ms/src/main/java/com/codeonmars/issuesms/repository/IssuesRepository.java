package com.codeonmars.issuesms.repository;

import com.codeonmars.issuesms.model.issues.IssuesEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuesRepository extends MongoRepository<IssuesEntity, String> {
}
