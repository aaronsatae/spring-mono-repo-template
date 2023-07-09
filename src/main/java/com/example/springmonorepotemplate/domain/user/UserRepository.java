package com.example.springmonorepotemplate.domain.user;

public interface UserRepository {
    User findById(long id);
}
