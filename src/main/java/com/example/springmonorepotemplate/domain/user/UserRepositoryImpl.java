package com.example.springmonorepotemplate.domain.user;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final AtomicLong userIdGenerator = new AtomicLong(1);
    private final List<User> users = new ArrayList<>();

    @PostConstruct
    public void setup() {
        for (int i = 0; i < 5; i++) {
            long id = userIdGenerator.getAndIncrement();
            users.add(new User(id, "name" + id));
        }
    }

    public User findById(long id) {
        return users.stream()
                .filter(u -> u.id() == id)
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
