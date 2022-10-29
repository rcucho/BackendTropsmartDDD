package com.techelper.tropsmart_backend.driver.domain.services.outputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewOutput {
    private String Customer;
    private String Driver;
    private String cargo;
    private String commentary;
    private double calification;
}