package com.techelper.tropsmart_backend.user.domain.services.outputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubscriptionOutput {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String plan;
    private double price;
    private String state;

    public SubscriptionOutput(int id, String firstName, String lastName, String email, String plan, double price, String state) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.plan = plan;
        this.price = price;
        this.state = state;
    }
}