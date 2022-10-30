package com.techelper.tropsmart_backend.user.application.controllers;

import com.techelper.tropsmart_backend.user.domain.services.comunications.PriceResponse;
import com.techelper.tropsmart_backend.user.infrastructure.servicesImp.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/prices")
public class PricesController {
    @Autowired
    PriceService priceService;

    @GetMapping
    public ResponseEntity<PriceResponse> findAllPrices()
    {
        PriceResponse result = priceService.findAllPrices();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/{priceId}")
    public ResponseEntity<PriceResponse> findPriceById(@PathVariable(value = "priceId")int priceId)
    {
        PriceResponse result = priceService.findPriceById(priceId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("price-type/{priceType}")
    public ResponseEntity<PriceResponse> findPricesByPriceType(@PathVariable(value = "priceType")int priceType)
    {
        PriceResponse result =  priceService.findPricesByPriceType(priceType);

        if(!result.success)
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}

