package com.techelper.tropsmart_backend.user.domain.services.outputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserOutput {
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String role;
    private int roleId;

    public UserOutput(String email, String password, String firstName, String lastName, String role, int roleId) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.roleId = roleId;
    }
    public UserOutput(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}