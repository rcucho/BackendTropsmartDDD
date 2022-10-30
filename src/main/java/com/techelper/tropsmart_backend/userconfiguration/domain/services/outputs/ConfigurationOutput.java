package com.techelper.tropsmart_backend.userconfiguration.domain.services.outputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ConfigurationOutput {
    private String firstName;
    private String lastName;
    private String Phone;
    private String language;
    private String paymentCurrency;
}
