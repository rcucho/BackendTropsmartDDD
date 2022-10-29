package com.techelper.tropsmart_backend.driver.domain.services.inputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewInput {
    private String commentary;
    private double calification;
}