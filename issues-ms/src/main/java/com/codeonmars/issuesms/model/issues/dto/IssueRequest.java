package com.codeonmars.issuesms.model.issues.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueRequest {
    private Long propertyID;
    private String description;
    private String longDescription;
}
