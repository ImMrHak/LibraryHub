package com.libraryhub.msnotifications.domain.notification.repository;

import com.libraryhub.msnotifications.domain.notification.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationDomainRepository extends MongoRepository<Notification, String> {
}
