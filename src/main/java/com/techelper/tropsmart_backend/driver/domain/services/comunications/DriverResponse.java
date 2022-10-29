package com.techelper.tropsmart_backend.driver.domain.services.comunications;

import com.techelper.tropsmart_backend.configuration.domain.services.comunications.BaseResponse;
import com.techelper.tropsmart_backend.driver.domain.services.outputs.DriverOutput;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DriverResponse extends BaseResponse<DriverOutput> {
    public DriverResponse(List<DriverOutput> driverOutputList) {super(driverOutputList);}
    public DriverResponse(DriverOutput driverOutput) {super(driverOutput);}
    public DriverResponse(String message) {super(message);}
}