package com.example.library.user.dao;

import com.example.library.user.entity.UserEntity;
import com.example.library.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDAOImpl implements UserDAO {
    private final UserRepository userRepository;

    @Autowired
    public UserDAOImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity getAllUser(Long userNo) {
        UserEntity userEntity = userRepository.findById(userNo).get();
        return userEntity;
    }
}
