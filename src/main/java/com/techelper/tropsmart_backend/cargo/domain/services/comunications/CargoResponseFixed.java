package com.techelper.tropsmart_backend.cargo.domain.services.comunications;

import com.techelper.tropsmart_backend.cargo.domain.services.outputs.CargoOutputFixed;
import com.techelper.tropsmart_backend.configuration.domain.services.comunications.BaseResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CargoResponseFixed extends BaseResponse<CargoOutputFixed> {
    public CargoResponseFixed(List<CargoOutputFixed> cargoOutputList) {super(cargoOutputList);}
    public CargoResponseFixed(CargoOutputFixed cargoOutput) {super(cargoOutput);}
    public CargoResponseFixed(String message) { super(message);}
}