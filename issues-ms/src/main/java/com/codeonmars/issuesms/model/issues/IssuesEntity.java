package com.codeonmars.issuesms.model.issues;

import com.codeonmars.issuesms.model.comments.IssueComment;
import com.codeonmars.issuesms.model.issues.enums.IssueStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "issues")
public class IssuesEntity {

    @Id
    @Field(name = "_id")
    private String id;
    @Field(name = "property")
    private PropertyData property;
    @Field(name = "creator")
    private CreatorData creator;
    @Field(name = "description")
    private String description;
    @Field(name = "long_description")
    private String longDescription;
    @Field(name = "status")
    private IssueStatus status;
    @Field(name = "creation_date")
    private Instant creationDate;
    @Field(name = "comments")
    private Set<IssueComment> comments = new HashSet<>();
}
