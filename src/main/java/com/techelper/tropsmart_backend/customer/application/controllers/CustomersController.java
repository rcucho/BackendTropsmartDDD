package com.techelper.tropsmart_backend.customer.application.controllers;

import com.techelper.tropsmart_backend.cargo.domain.services.comunications.CargoResponse;
import com.techelper.tropsmart_backend.cargo.infrastructure.servicesImp.CargoService;
import com.techelper.tropsmart_backend.customer.domain.services.comunications.CustomerResponse;
import com.techelper.tropsmart_backend.customer.infrastructure.servicesImp.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/customers")
public class CustomersController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CargoService cargoService;

    @GetMapping
    public ResponseEntity<CustomerResponse> findAllCustomers()
    {
        CustomerResponse result = customerService.findAllCustomers();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{cargoId}")
    public ResponseEntity<CustomerResponse> findCustomersById(@PathVariable(value = "cargoId") int cargoId)
    {
        CustomerResponse result = customerService.findCustomerById(cargoId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/{customerId}/cargoes")
    public ResponseEntity<CargoResponse> getCargoesByCustomerId(@PathVariable(value = "customerId")int customerId)
    {
        CargoResponse result = cargoService.findCargoesByCustomerId(customerId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{customerId}/credits/{credits}")
    public ResponseEntity<CustomerResponse> rechargeCreditsByCustomer(@PathVariable(value = "customerId")int customerId, @PathVariable(value = "credits")double credits)
    {
        CustomerResponse result = customerService.rechargeCreditsByCustomerId(customerId, credits);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}