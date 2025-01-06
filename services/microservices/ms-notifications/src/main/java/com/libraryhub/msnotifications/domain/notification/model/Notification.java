package com.libraryhub.msnotifications.domain.notification.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Validated
public class Notification {

    @Id
    private String idNotification;
    private String userEmail;
    private String message;
}