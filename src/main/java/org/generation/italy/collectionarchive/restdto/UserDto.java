package org.generation.italy.collectionarchive.restdto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.restdto.logindto.UserInputDto;

public class UserDto {
    private int userId;
    private String name;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private String country;
    private String role;
    private boolean active;

    public UserDto() {
    }

    public UserDto(int userId, String name, String lastname, String username,
                String password, String email, String country, String role, boolean active) {
        this.userId = userId;
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.country = country;
        this.role = role;
        this.active = active;
    }

    public User toUser(){
        User u = new User( userId, name,  lastname,  username,
                 password,  email,  country,  role, active);
        return u;
    }

    public static UserDto toDto(User u){
        return new UserDto(u.getUserId(), u.getName(),u.getLastname(),u.getUsername(),u.getPassword(),
                u.getEmail(), u.getCountry(), u.getRole(),u.isActive());
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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


}
