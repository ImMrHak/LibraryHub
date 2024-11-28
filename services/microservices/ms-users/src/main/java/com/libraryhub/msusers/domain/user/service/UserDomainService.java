package com.libraryhub.msusers.domain.user.service;


import com.libraryhub.msusers.domain.user.model.User;
import com.libraryhub.msusers.domain.user.repository.UserDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDomainService {

    private final UserDomainRepository userDomainRepository;

    public List<User> findAll(){
        return userDomainRepository.findAll();
    }

    public User findUserById(String id) {
        return userDomainRepository.findById(id).orElse(null);
    }

    public User saveUser(User user) {
        return userDomainRepository.save(user);
    }

    public Boolean deleteUserById(String id) {
        User dbUser = findUserById(id);

        if(dbUser == null) return false;

        dbUser.setIsDeleted(true);

        userDomainRepository.save(dbUser);
        return true;
    }

    public Boolean deleteUser(User user) {
        User dbUser = findUserById(user.getIdUser());
        if(dbUser == null) return false;

        dbUser.setIsDeleted(true);

        userDomainRepository.save(dbUser);
        return true;
    }
}
