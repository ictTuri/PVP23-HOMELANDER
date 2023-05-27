package com.codeonmars.notificationsms.model.notifications.dto;

import java.time.Instant;

public record NotificationRecord(String header, String message, Long number, Instant creationDate) {
}
