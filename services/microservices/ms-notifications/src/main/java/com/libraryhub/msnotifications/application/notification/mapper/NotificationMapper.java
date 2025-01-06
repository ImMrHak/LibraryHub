package com.libraryhub.msnotifications.application.notification.mapper;

import com.libraryhub.msnotifications.domain.notification.model.Notification;
import com.libraryhub.shareddata.sharedRecords.msNotifications.notification.record.request.BorrowCreatedEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    Notification mapBorrowCreatedEventToNotification(BorrowCreatedEvent borrowCreatedEvent);
}
