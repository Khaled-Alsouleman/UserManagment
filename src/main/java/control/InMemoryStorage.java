package control;

import entity.User_InMemoryStorage;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Singleton
public class InMemoryStorage {
    private Map<Long, User_InMemoryStorage> storage = new HashMap<>();
    private AtomicLong newId = new AtomicLong(1L);


    @PostConstruct
    public void init() {
        User_InMemoryStorage demoUser1 = new User_InMemoryStorage("Tech", "11", "web@mail.de", LocalDate.of(2018, Month.JANUARY, 1), "Tech11Pass!");
        User_InMemoryStorage demoUser2 = new User_InMemoryStorage("Mark", "Demo", "Demo@mail.de", LocalDate.of(1995, Month.FEBRUARY, 2), "DemoPass!");
        this.create(demoUser1);
        this.create(demoUser2);
    }

    public boolean create(final User_InMemoryStorage user) {
        if (isValidToCreate(user)){
            final Long newKey = newId.getAndIncrement();
            user.setId(newKey);
            storage.put(newKey, user);
            return true;
        }
        else return false;
    }

    public List<User_InMemoryStorage> findAll(final long limit , final long offset) {
        return this.storage.values().stream().limit(limit).skip(offset).collect(Collectors.toList());
    }
    public User_InMemoryStorage readById(final long id) {return this.storage.get(id);}

    public boolean delete(final long id) {
        if (this.readById(id)!=null)
        {
            this.storage.remove(id);
            return true;
        }
        return false;
    }

    public boolean update(final long id, final User_InMemoryStorage user) {
        final User_InMemoryStorage user_inMemoryStorage =this.readById(id);
        if (user_inMemoryStorage!=null || id == user.getId())
        {
           this.storage.put(id,isValidToUpdate(user ,user_inMemoryStorage));
           return true;
        }
        return false;
    }

    private static boolean isValidToCreate(User_InMemoryStorage userSingleton) {
        if (userSingleton.getFirstname() == null ||
                userSingleton.getLastname()  == null ||
                userSingleton.getBirthday()  == null ||
                userSingleton.getEmail()     == null ||
                userSingleton.getPassword()  == null
        )return false;
        return true;
    }

    private static User_InMemoryStorage isValidToUpdate(User_InMemoryStorage modelToUpdate, User_InMemoryStorage user) {
        final User_InMemoryStorage tempUser = user;
        if (modelToUpdate.getFirstname() != null) user.setFirstname(modelToUpdate.getFirstname());
        if (modelToUpdate.getLastname()  != null) user.setLastname(modelToUpdate.getLastname());
        if (modelToUpdate.getBirthday()  != null) user.setBirthday(modelToUpdate.getBirthday());
        if (modelToUpdate.getEmail()     != null) user.setEmail(modelToUpdate.getEmail());
        if (modelToUpdate.getPassword()  != null) user.setPassword(modelToUpdate.getPassword());
        return tempUser;
    }
}