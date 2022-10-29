package com.techelper.tropsmart_backend.user.domain.services.outputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonOutput {
    private String firstName;
    private String lastName;
    private String email;
    private String userType;
}
