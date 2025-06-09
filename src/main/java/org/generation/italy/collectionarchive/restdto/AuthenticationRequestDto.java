package org.generation.italy.collectionarchive.restdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class AuthenticationRequestDto {
    @NotEmpty(message = "email is mandatory")
    @Email(message = "email invalid format")
    private String email;
    @NotEmpty(message = "password is mandatory")
    @Size(min = 6 , max = 30)
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
