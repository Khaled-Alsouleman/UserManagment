package entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User_Database {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;
    @NotEmpty
    @Size(max = 50)
    private String firstname;

    @NotEmpty
    @Size(max = 50)
    private String lastname;
    @NotEmpty
    @Size(max = 150)
    private String email;
    private LocalDate birthday;
    @Transient
    @NotEmpty
    @Size(max = 255)
    private String password;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}