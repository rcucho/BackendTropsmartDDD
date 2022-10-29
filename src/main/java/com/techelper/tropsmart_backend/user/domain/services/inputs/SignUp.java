package com.techelper.tropsmart_backend.user.domain.services.inputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignUp {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int discriminator;

    public SignUp(String firstName, String lastName, String email, String password, String phone, int discriminator) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.discriminator = discriminator;
    }
}