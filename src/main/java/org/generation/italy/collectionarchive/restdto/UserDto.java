package org.generation.italy.collectionarchive.restdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.generation.italy.collectionarchive.models.entities.Authority;
import org.generation.italy.collectionarchive.models.entities.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDto {
    private int userId;
    private String name;
    private String lastname;
    private String password;
    private String email;
    private String country;
    private boolean active;
    @JsonProperty("authorities")
    private Collection<? extends GrantedAuthority> authorities;

    public UserDto() {
    }

    public UserDto(int userId, String name, String lastname,
                String password, String email, String country, boolean active, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.country = country;
        this.active = active;
        this.authorities = authorities;
    }

    public User toUser(){
        User u = new User( userId, name,  lastname,
                 password,  email,  country, active);
        u.setActive(true);
        return u;
    }

    public static UserDto toDto(User u){
        return new UserDto(u.getUserId(), u.getName(), u.getLastname(), u.getPassword(),
                u.getEmail(), u.getCountry(), u.isActive(), u.getAuthorities());
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

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

}
