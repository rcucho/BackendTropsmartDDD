package com.techelper.tropsmart_backend.userconfiguration.domain.services.inputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentMethodInput {
    private String bankName;
    private int swiftCode;
    private long accountNumber;
    private String billingAdress;

    public PaymentMethodInput(String bankName, int swiftCode, long accountNumber, String billingAdress) {
        this.bankName = bankName;
        this.swiftCode = swiftCode;
        this.accountNumber = accountNumber;
        this.billingAdress = billingAdress;
    }
}
