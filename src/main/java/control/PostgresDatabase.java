package control;

import entity.User_PostgresDB;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


public class Db {
    @PersistenceContext
    EntityManager em;

    @PostConstruct
    public void initDummyData() {

    }

    public void create(final User_PostgresDB user) {
       em.persist(user);
    }

    public void update(final long id, final User_PostgresDB user) {
        em.find(User_PostgresDB.class ,id);
    }

    public User_PostgresDB readById(final long id) {
        User_PostgresDB re = em.find(User_PostgresDB.class, id);
        if (re == null)
            return null;
        return re;
    }

    public List<User_PostgresDB> findAll() {
      return em.createQuery("SELECT u FROM User_PostgresDB u", User_PostgresDB.class).getResultList();
    }

}
