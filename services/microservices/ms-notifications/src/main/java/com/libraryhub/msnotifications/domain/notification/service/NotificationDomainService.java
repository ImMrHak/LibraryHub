package com.libraryhub.msnotifications.domain.notification.service;

import com.libraryhub.msnotifications.domain.notification.model.Notification;
import com.libraryhub.msnotifications.domain.notification.repository.NotificationDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationDomainService {

    private final NotificationDomainRepository notificationDomainRepository;

    public List<Notification> findAll(){
        return notificationDomainRepository.findAll();
    }

    public Notification findNotificationById(String id) {
        return notificationDomainRepository.findById(id).orElse(null);
    }

    public Notification saveNotification(Notification notification) {
        return notificationDomainRepository.save(notification);
    }

    public Boolean deleteUserById(String id) {
        Notification dbUser = findNotificationById(id);

        notificationDomainRepository.save(dbUser);
        return true;
    }

    public Boolean deleteNotification(Notification notification) {
        notificationDomainRepository.save(notification);
        return true;
    }

    public Boolean existsNotificationById(String id) {
        return notificationDomainRepository.existsById(id);
    }
}
