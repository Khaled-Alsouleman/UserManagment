package control;


import entity.User_PostgresDB;


import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;


@Transactional
@Stateless
public class PostgresDatabase {
    @PersistenceContext
    EntityManager em;


    @PostConstruct
    public void initDummyData() {

        for (int i = 0; i < 20; i++) {
            em.persist(
                    new User_PostgresDB(
                            "Mark" + i + 1,
                            "Demo",
                            "Demo" + i + "@mail.de",
                            LocalDate.of(1995 + i, Month.FEBRUARY, 2 + i),
                            "DemoPass!" + i));
        }

    }

    public boolean create(final User_PostgresDB user) {
        if (isValidToCreateDB(user)) {
                user.setId(0l);
                em.persist(user);
                return true;
        }
        return false;
    }

    public boolean update(final long id, final User_PostgresDB user) {
        if (id == user.getId()) {
            final User_PostgresDB tempUser = em.find(User_PostgresDB.class, id);
            if (user.getFirstname() != null) tempUser.setFirstname(user.getFirstname());
            if (user.getLastname() != null) tempUser.setLastname(user.getLastname());
            if (user.getBirthday() != null) tempUser.setBirthday(user.getBirthday());
            if (user.getEmail() != null) tempUser.setEmail(user.getEmail());
            if (user.getPassword() != null) tempUser.setPassword(user.getPassword());
            return true;
        }
        return false;
    }

    public User_PostgresDB readById(final long id) {
        final User_PostgresDB re = em.find(User_PostgresDB.class, id);
        if (re == null)
            return null;
        return re;
    }

    public List<User_PostgresDB> findAll(final long limit, final long offset) {
        return em.createQuery("SELECT u FROM User_PostgresDB u", User_PostgresDB.class)
                .getResultList()
                .stream().limit(limit)
                .skip(offset)
                .collect(Collectors.toList());
    }

    public boolean delete(final long id) {
        if (containsId(id)) {
            em.remove(this.readById(id));
            return true;
        } else return false;
    }

    private boolean containsId(final long id) {
        final User_PostgresDB user = this.readById(id);
        if (user == null) return false;
        return true;
    }

    private boolean isValidToCreateDB(User_PostgresDB user) {

        if (    user.getFirstname() ==   null ||
                user.getLastname()  ==   null ||
                user.getBirthday()  ==   null ||
                user.getEmail()     ==   null ||
                user.getPassword()  ==   null
        )
            return false;
        return true;
    }
}
