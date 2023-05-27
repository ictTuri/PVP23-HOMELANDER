package com.codeonmars.issuesms.model.issues;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "creator")
public class CreatorData {
    @Field(name = "username")
    private String username;
    @Field(name = "email")
    private String email;
}
