package com.libraryhub.msusers.domain.user.model;


import com.libraryhub.msusers.domain.user.enumeration.UserTypeEnum;
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
public class User {
    @Id
    protected String idUser;

    protected String username;

    protected String email;

    protected String firstName;

    protected String lastName;

    protected UserTypeEnum userType;

    protected Boolean isDeleted;
}
