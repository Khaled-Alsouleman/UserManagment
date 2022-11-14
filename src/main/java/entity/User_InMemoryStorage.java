package entity;

import utils.EncryptPassword;

import javax.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDate;

public class User_Singleton  {

    private Long id ;
    private String firstname;
    private String lastname;
    private String email;


   private LocalDate birthday;
   @Transient
   private String password;

    public User_Singleton() {
        super();

    }

    public User_Singleton(String firstname, String lastname, String email, LocalDate birthday, String password) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.birthday = birthday;
        this.password= EncryptPassword.doEncryptPassword(password);
    }
    public  UserDTO toTDO(){

     UserDTO tempUser = new UserDTO();
     tempUser.setId(this.getId());
     tempUser.setFirstname(this.getFirstname());
     tempUser.setLastname(this.getLastname());
     tempUser.setBirthday(this.getBirthday());
     tempUser.setEmail(this.getEmail());
     return tempUser;
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
        this.password = EncryptPassword.doEncryptPassword(password);;
    }
}
