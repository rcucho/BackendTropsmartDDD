package com.techelper.tropsmart_backend.user.domain.services.inputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignIn {
    private String email;
    private String password;

    public SignIn(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
