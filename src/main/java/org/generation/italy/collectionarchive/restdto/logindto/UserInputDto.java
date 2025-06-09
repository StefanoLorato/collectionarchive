package org.generation.italy.collectionarchive.restdto.logindto;

import org.generation.italy.collectionarchive.models.entities.User;

public class UserInputDto {
    private String name;
    private String lastname;
    private String username;
    private String password; //campio o registro password
    private String email;
    private String country;
    private String role;
    private boolean active;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public User toEntity() {
        User user = new User();
        user.setName(this.name);
        user.setLastname(this.lastname);
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setUsername(this.username);
        user.setRole(this.role);
        user.setCountry(this.country);
        user.setActive(this.active);
        return user;
    }
}



