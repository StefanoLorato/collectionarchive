package org.generation.italy.collectionarchive.restdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.generation.italy.collectionarchive.models.entities.User;

public class RegisterRequestDto {
    @NotEmpty(message = "name is mandatory")
    @Size(min = 3 , max = 30)
    private String name;
    @NotEmpty(message = "lastname is mandatory")
    @Size(min = 3, max = 30)
    private String lastname;
    @NotEmpty(message = "email is mandatory")
    @Email(message = "email invalid format")
    private String email;
    @NotEmpty(message = "password is mandatory")
    @Size(min = 6 , max = 30)
    private String password;
    @NotEmpty(message = "country is mandatory")
    private String country;

    public RegisterRequestDto(String name, String lastname, String email, String password, String country) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.country = country;
    }

    public User toUser(){
        User u = new User();
        u.setName(name);
        u.setLastname(lastname);
        u.setPassword(password);
        u.setEmail(email);
        u.setCountry(country);
        u.setActive(true);
        return u;
    }

    public String getName() {
        return name;
    }
    public void setName(@NotEmpty(message = "name is mandatory") @Size(min = 3, max = 30) String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(@NotEmpty(message = "lastname is mandatory") @Size(min = 3, max = 30) String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(@NotEmpty(message = "email is mandatory") @Email(message = "email invalid format") String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(@NotEmpty(message = "password is mandatory") @Size(min = 6, max = 30) String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
}
