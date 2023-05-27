package com.codeonmars.issuesms.model.comments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "comments")
public class IssueComment {

    @Id
    @Field(name = "_id")
    private String id;
    @Field(name = "comment")
    private String comment;
    @Field(name = "created_from")
    private String createdFrom;
    @Field(name = "creation_date")
    private Instant creationDate;
}
