package com.techelper.tropsmart_backend.customer.domain.services;

import com.techelper.tropsmart_backend.configuration.domain.services.ICrudService;
import com.techelper.tropsmart_backend.customer.domain.models.Customer;
import com.techelper.tropsmart_backend.customer.domain.services.comunications.CustomerResponse;
import org.springframework.stereotype.Service;

@Service
public interface ICustomerService extends ICrudService<Customer> {
    CustomerResponse findCustomerById(int customerId);
    CustomerResponse findAllCustomers();
    CustomerResponse rechargeCreditsByCustomerId(int customerId, double creditUnits);

}
