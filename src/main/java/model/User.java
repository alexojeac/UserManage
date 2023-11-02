package model;

import org.mindrot.jbcrypt.BCrypt;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 8998;
    private String name;
    private String passwordHash;
    private int age;
    private String email;

    public User(String name, String passwordHash, int age, String email) {
        this.name = name;
        setPass(passwordHash);
        this.age = age;
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Boolean chekPass(String pass) {
        return BCrypt.checkpw(pass, passwordHash);
    }

    public void setPass(String pass) {
        this.passwordHash = BCrypt.hashpw(pass, BCrypt.gensalt());
    }
}
