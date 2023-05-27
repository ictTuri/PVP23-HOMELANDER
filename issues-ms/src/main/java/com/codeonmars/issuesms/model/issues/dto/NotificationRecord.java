package com.codeonmars.issuesms.model.issues.dto;

import java.time.Instant;

public record NotificationRecord(String header, String message, Long number, Instant creationDate) {
}
