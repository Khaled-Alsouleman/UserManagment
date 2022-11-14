package control;

import entity.User_InmemoryStorage;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Singleton
public class UserStorage {
    private Map<Long, User_InmemoryStorage> storage = new HashMap<>();
    private AtomicLong newId = new AtomicLong(1L);


    @PostConstruct
    public void init() {
        User_InmemoryStorage demoUser1 = new User_InmemoryStorage("Tech", "11", "web@mail.de", LocalDate.of(2018, Month.JANUARY, 1),"Tech11Pass!");
        User_InmemoryStorage demoUser2 = new User_InmemoryStorage("Mark", "Demo", "Demo@mail.de", LocalDate.of(1995, Month.FEBRUARY, 2),"DemoPass!");
        this.create(demoUser1);
        this.create(demoUser2);
    }

    public void create(final User_InmemoryStorage userSingleton) {
        final Long newKey = newId.getAndIncrement();
        userSingleton.setId(newKey);
        storage.put(newKey, userSingleton);
    }

    public List<User_InmemoryStorage> findAll() {
        return this.storage.values().stream().collect(Collectors.toList());
    }


    public User_InmemoryStorage readById(final long id) {
         return this.storage.get(id);
    }

    public void update(final long id, final User_InmemoryStorage userSingleton) {
        this.storage.put(id, userSingleton);
    }

    public void delete(final long id) {this.storage.remove(id);}

    public boolean containsId( final long id )
    {
        return this.storage.containsKey( id );
    }
}
