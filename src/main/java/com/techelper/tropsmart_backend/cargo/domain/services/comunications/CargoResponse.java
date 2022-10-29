package com.techelper.tropsmart_backend.cargo.domain.services.comunications;

import com.techelper.tropsmart_backend.cargo.domain.services.outputs.CargoOutput;
import com.techelper.tropsmart_backend.configuration.domain.services.comunications.BaseResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CargoResponse extends BaseResponse<CargoOutput> {
    public CargoResponse(List<CargoOutput> cargoOutputList) {super(cargoOutputList);}
    public CargoResponse(CargoOutput cargoOutput) {super(cargoOutput);}
    public CargoResponse(String message) { super(message);}
}