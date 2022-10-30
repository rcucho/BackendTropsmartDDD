package com.techelper.tropsmart_backend.userconfiguration.domain.services.outputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentMethodOutput {
    private String bankName;
    private int swiftCode;
    private long accountNumber;

    public PaymentMethodOutput(String bankName, int swiftCode, long accountNumber) {
        this.bankName = bankName;
        this.swiftCode = swiftCode;
        this.accountNumber = accountNumber;
    }
}