package com.libraryhub.shareddata.sharedRecords.msNotifications.notification.record.request;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record BorrowCreatedEvent(
        @NotNull
        String userEmail,
        @NotNull
        String message,
        @NotNull
        Date date
) {
}
