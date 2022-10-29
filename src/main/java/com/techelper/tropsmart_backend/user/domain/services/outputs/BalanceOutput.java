package com.techelper.tropsmart_backend.user.domain.services.outputs;

public class BalanceOutput {
    private String user;
    private String email;
    private double credits;
    private double addedMoney;
    private double spentMoney;

    public BalanceOutput(String user, String email, double credits, double addedMoney, double spentMoney) {
        this.user = user;
        this.email = email;
        this.credits = credits;
        this.addedMoney = addedMoney;
        this.spentMoney = spentMoney;
    }
}