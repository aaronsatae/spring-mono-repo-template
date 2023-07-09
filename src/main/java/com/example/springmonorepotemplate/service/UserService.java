package com.example.springmonorepotemplate.service;

import com.example.springmonorepotemplate.domain.user.User;
import com.example.springmonorepotemplate.domain.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(long id) {
        return this.userRepository.findById(id);
    }
}
