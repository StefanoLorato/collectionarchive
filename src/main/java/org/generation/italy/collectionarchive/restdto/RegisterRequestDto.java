package org.generation.italy.collectionarchive.restdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class RegisterRequestDto {
    @NotEmpty(message = "name is mandatory")
    @Size(min = 3 , max = 30)
    private String name;
    @NotEmpty(message = "lastname is mandatory")
    @Size(min = 3, max = 30)
    private String lastName;
    @NotEmpty(message = "email is mandatory")
    @Email(message = "email invalid format")
    private String email;
    @NotEmpty(message = "password is mandatory")
    @Size(min = 6 , max = 30)
    private String password;

    public RegisterRequestDto(String name, String lastName, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }
    public void setName(@NotEmpty(message = "name is mandatory") @Size(min = 3, max = 30) String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(@NotEmpty(message = "lastname is mandatory") @Size(min = 3, max = 30) String lastName) {
        this.lastName = lastName;
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
}
