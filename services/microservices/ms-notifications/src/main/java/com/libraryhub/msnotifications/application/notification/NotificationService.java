package com.libraryhub.msnotifications.application.notification;

import com.libraryhub.shareddata.sharedRecords.msNotifications.notification.record.request.BorrowCreatedEvent;

public interface NotificationService {
    void listen(BorrowCreatedEvent event);
}
