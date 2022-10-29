package com.techelper.tropsmart_backend.driver.domain.services.comunications;

import com.techelper.tropsmart_backend.configuration.domain.services.comunications.BaseResponse;
import com.techelper.tropsmart_backend.driver.domain.services.outputs.VehicleOutput;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class VehicleResponse extends BaseResponse<VehicleOutput> {
    public VehicleResponse(List<VehicleOutput> vehicleOutputList) {super(vehicleOutputList);}
    public VehicleResponse(VehicleOutput vehicleOutput) {super(vehicleOutput);}
    public VehicleResponse(String message) {super(message);}
}
