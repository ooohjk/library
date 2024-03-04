package com.example.library.user.dao;

import com.example.library.user.entity.UserEntity;
import com.example.library.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserDAOImpl  implements UserDAO {
    private final UserRepository userRepository;

    @Autowired
    public UserDAOImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity getAllUser(String userId) {
        UserEntity userEntity = userRepository.findById(userId);
        return userEntity;
    }
}
