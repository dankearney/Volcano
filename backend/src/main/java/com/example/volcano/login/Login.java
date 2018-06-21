package com.example.volcano.login;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "logins")
public class Login {
    
    private String username;
    private String password;
    private Long userid;

    public Login() {
    }

    public Login(String username, String password, long userid) {
        this.userid = userid;
        this.username = username;
        this.password = password;
    }

    public Long getuserid() {
        return userid;
    }

    public void setId(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Login{" + "id=" + userid + ", username=" + username
                + ", password=" + password + '}';
    }
}