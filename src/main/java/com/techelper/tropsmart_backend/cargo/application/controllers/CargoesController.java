package com.techelper.tropsmart_backend.cargo.application.controllers;

import com.techelper.tropsmart_backend.cargo.domain.services.ICargoService;
import com.techelper.tropsmart_backend.cargo.domain.services.comunications.CargoResponse;
import com.techelper.tropsmart_backend.cargo.domain.services.inputs.CargoInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/cargoes")
public class CargoesController {

    @Autowired
    private ICargoService cargoService;

    @GetMapping
    public ResponseEntity<CargoResponse> findAllCargoes()
    {
        CargoResponse result = cargoService.findAllCargoes();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/fixed")
    public ResponseEntity<CargoResponse> findAllCargoesFixed()
    {
        CargoResponse result = cargoService.findAllCargoesFixed();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        //
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{cargoId}")
    public ResponseEntity<CargoResponse> findCargoById(@PathVariable(value = "cargoId")int cargoId)
    {
        CargoResponse result = cargoService.findCargoById(cargoId);

        //if(!result.success)
        //    return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("customers/{customerId}")
    public ResponseEntity<CargoResponse> findCargoesByCustomerId(@PathVariable(value="customerId")int customerId)
    {
        CargoResponse result = cargoService.findCargoesByCustomerId(customerId);

        //if(!result.success)
        //    return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("drivers/{driverId}")
    public ResponseEntity<CargoResponse> findCargoesByDriverId(@PathVariable(value="driverId")int driverId)
    {
        CargoResponse result = cargoService.findCargoesByDriverId(driverId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("{cargoId}/confirms")
    public ResponseEntity<CargoResponse> setCargoConfirmed(@PathVariable(value = "cargoId")int cargoId)
    {
        CargoResponse result = cargoService.confirmCargoRequest(cargoId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("{cargoId}/deliver")
    public ResponseEntity<CargoResponse> setCargoDelivered(@PathVariable(value="cargoId")int cargoId)
    {
        CargoResponse result = cargoService.setCargoDelivered(cargoId);

        //if(!result.success)
        //    return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("{cargoId}/reject")
    public ResponseEntity<CargoResponse> setCargoRejected(@PathVariable(value = "cargoId")int cargoId)
    {
        CargoResponse result = cargoService.rejectCargoById(cargoId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/customers/{customerId}")
    public ResponseEntity<CargoResponse> postCargo(@PathVariable(value = "customerId")int customerId, @RequestBody CargoInput cargoInput)
    {
        CargoResponse result = cargoService.addCargoByCustomerId(customerId,cargoInput);
        //if(!result.success)
        //    return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }




}