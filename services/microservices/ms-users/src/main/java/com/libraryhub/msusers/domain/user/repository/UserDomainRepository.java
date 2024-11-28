package com.libraryhub.msusers.domain.user.repository;

import com.libraryhub.msusers.domain.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDomainRepository extends MongoRepository<User,String> {
}
