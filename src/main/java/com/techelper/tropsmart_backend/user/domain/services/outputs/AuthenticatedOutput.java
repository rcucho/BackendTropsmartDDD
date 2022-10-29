package com.techelper.tropsmart_backend.user.domain.services.outputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthenticatedOutput {
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private int role;
    private int roleId;
    private String token;
    //private String refreshToken;

    public AuthenticatedOutput(int id, String email, String password, String firstName, String lastName, int role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }
}