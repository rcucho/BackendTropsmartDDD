package com.techelper.tropsmart_backend.customer.domain.services.comunications;

import com.techelper.tropsmart_backend.configuration.domain.services.comunications.BaseResponse;
import com.techelper.tropsmart_backend.customer.domain.services.outputs.CustomerOutput;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CustomerResponse extends BaseResponse<CustomerOutput> {
    public CustomerResponse(List<CustomerOutput> customerResponseList) {super(customerResponseList);}
    public CustomerResponse(CustomerOutput customerOutput) {super(customerOutput);}
    public CustomerResponse(String message) {super(message);}
}
