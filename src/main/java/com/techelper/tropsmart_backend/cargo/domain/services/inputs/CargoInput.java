package com.techelper.tropsmart_backend.cargo.domain.services.inputs;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CargoInput implements Serializable {
    private double weight;
    private String description;
    private int servicePrice;
    private int serviceId;

    public CargoInput(double weight, String description, int servicePrice, int serviceId) {
        this.weight = weight;
        this.description = description;
        this.servicePrice = servicePrice;
        this.serviceId = serviceId;
    }
}