package com.libraryhub.msusers.domain.user.repository;

import com.libraryhub.msusers.domain.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDomainRepository extends MongoRepository<User,String> {
    Boolean existsByUsername(String username);
    Boolean existsByIdUser(String idUser);
}
