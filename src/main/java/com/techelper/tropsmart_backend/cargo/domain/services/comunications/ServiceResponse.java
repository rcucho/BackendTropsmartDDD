package com.techelper.tropsmart_backend.cargo.domain.services.comunications;

import com.techelper.tropsmart_backend.cargo.domain.services.outputs.ServiceOutput;
import com.techelper.tropsmart_backend.configuration.domain.services.comunications.BaseResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ServiceResponse extends BaseResponse<ServiceOutput> {
    public ServiceResponse(List<ServiceOutput> serviceOutputList) {super(serviceOutputList);}
    public ServiceResponse(ServiceOutput serviceOutput) {super(serviceOutput);}
    public ServiceResponse(String message) {super(message);}
}