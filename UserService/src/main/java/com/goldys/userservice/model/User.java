package com.goldys.userservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/* This class should be annotated with @Entity annotation */
@Entity
public class User implements Serializable {
    /*
     * This class should have three fields of type String
     * (email,password,role)
     * This class should also contain the getters and setters for the
     * fields along with the no-arg , parameterized	constructor and toString method.
     *
     */
    @Id
    private String email;
    private String password;
    private String role;

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
