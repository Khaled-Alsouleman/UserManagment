package com.example.usermanagment.database;

import com.example.usermanagment.models.User;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Singleton
public class UserMemory {
    private Map<Long, User> users = new HashMap<>();
    private AtomicLong newId = new AtomicLong(1L);


    @PostConstruct
    public void init() {
        User demoUser = new User("Tech", "11", "web@mail.de", LocalDate.of(2018, Month.JANUARY, 1), "password");
        Long newKey = newId.getAndIncrement();
        demoUser.setId(newKey);
        users.put(newKey, demoUser);

    }

    public void addUser(final User user) {
        final Long newKey = newId.getAndIncrement();
        user.setId(newKey);
        users.put(newKey, user);
    }

    public List<User> getUsers() {
        return this.users.values().stream().collect(Collectors.toList());
    }

    public Optional<User> getSingleUser(final Long id) {
        return Optional.of(users.get(id));
    }

    public void updateUser(final Long id, final User user) {
        this.users.put(id, user);
    }


}
