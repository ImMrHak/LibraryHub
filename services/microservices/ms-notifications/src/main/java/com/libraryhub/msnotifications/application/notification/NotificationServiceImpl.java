package com.libraryhub.msnotifications.application.notification;

import com.libraryhub.msnotifications.application.notification.mapper.NotificationMapper;
import com.libraryhub.msnotifications.domain.notification.model.Notification;
import com.libraryhub.msnotifications.domain.notification.service.NotificationDomainService;
import com.libraryhub.msnotifications.infrastructure.utility.Utility;
import com.libraryhub.shareddata.sharedRecords.msNotifications.notification.record.request.BorrowCreatedEvent;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationDomainService notificationDomainService;
    private final NotificationMapper notificationMapper;
    private final Utility utility;

    @Override
    @KafkaListener(topics = "borrow-created", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(BorrowCreatedEvent event) {
        if (event == null) {
            throw new IllegalArgumentException("Deserialized event is null");
        }
        System.out.println("Received Kafka Event: " + event);

        Notification notification = notificationMapper.mapBorrowCreatedEventToNotification(event);

        notificationDomainService.saveNotification(notification);

        try{
            utility.sendEmail(event.userEmail(), "Borrow Created", event.message(), true);
        }
        catch (MessagingException e){
            e.printStackTrace();
        }
    }
}
