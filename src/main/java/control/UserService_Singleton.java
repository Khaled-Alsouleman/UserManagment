package control;

import entity.User;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Singleton
public class UserService_Singleton {
    private Map<Long, User> users = new HashMap<>();
    private AtomicLong newId = new AtomicLong(1L);


    @PostConstruct
    public void init() {
        User demoUser = new User("Tech", "11", "web@mail.de", LocalDate.of(2018, Month.JANUARY, 1));
        this.addUser(demoUser);
    }

    public void addUser(final User user) {
        final Long newKey = newId.getAndIncrement();
        user.setId(newKey);
        users.put(newKey, user);
    }

    public List<User> getUsers() {
        return this.users.values().stream().collect(Collectors.toList());
    }

    public User getSingleUser(final long id) {
       User user = new User();
       user.setId(-1l);
       if (this.users.containsKey(id)) return this.users.get(id);
       return user;
    }

    public void updateUser(final long id, final User user) {
        this.users.put(id, user);
    }

    public void removeUser(final long id) {this.users.remove(id);}

}
