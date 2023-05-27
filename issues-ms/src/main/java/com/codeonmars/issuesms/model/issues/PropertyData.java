package com.codeonmars.issuesms.model.issues;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "property")
public class PropertyData {
    @Field(name = "origin_id")
    private Long originId;
    @Field(name = "description")
    private String description;
}
