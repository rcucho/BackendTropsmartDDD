package com.techelper.tropsmart_backend.customer.domain.services.outputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerOutput {
    public int id;
    public String firstName;
    public String lastName;
    public double credits;
    public String email;
    public int role;
    public int roleId;

    public CustomerOutput(int id, String firstName, String lastName, double credits,String email, int role, int roleId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.credits = credits;
        this.email = email;
        this.role = role;
        this.roleId = roleId;
    }
}