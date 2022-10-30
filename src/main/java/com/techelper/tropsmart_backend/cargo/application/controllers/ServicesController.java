package com.techelper.tropsmart_backend.cargo.application.controllers;

import com.techelper.tropsmart_backend.cargo.domain.services.comunications.ServiceResponse;
import com.techelper.tropsmart_backend.cargo.infrastructure.servicesImp.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/services")
public class ServicesController {
    @Autowired
    private ServiceService serviceService;

    @GetMapping
    public ResponseEntity<ServiceResponse> findAllServices()
    {
        ServiceResponse result = serviceService.findAllServices();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping("/drivers/{driverId}")
    public ResponseEntity<ServiceResponse> addServiceByDriverId(@PathVariable(value = "driverId")int driverId)
    {
        ServiceResponse result = serviceService.createService(driverId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/drivers/{driverId}/some")
    public ResponseEntity<ServiceResponse> findServiceByDriverId(@PathVariable(value = "driverId")int driverId)
    {
        ServiceResponse result = serviceService.findSomeServiceByDriverId(driverId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/drivers/{driverId}")
    public ResponseEntity<ServiceResponse> findServicesByDriverId(@PathVariable(value = "driverId")int driverId)
    {
        ServiceResponse result = serviceService.findServicesByDriverId(driverId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
