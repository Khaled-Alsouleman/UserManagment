package entity;

import java.time.LocalDate;

import static entity.User_PostgresDB.getUserDTO;

public class User_InMemoryStorage {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;


    private LocalDate birthday;

    private String password;

    public User_InMemoryStorage() {
    }

    public User_InMemoryStorage(String firstname, String lastname, String email, LocalDate birthday, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.birthday = birthday;
        this.password = password;
    }

    public UserDTO toDTO() {
        return getUserDTO(this.id, this.firstname, this.lastname, this.email, this.birthday);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
