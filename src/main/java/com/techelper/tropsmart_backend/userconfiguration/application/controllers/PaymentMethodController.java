package com.techelper.tropsmart_backend.userconfiguration.application.controllers;

import com.techelper.tropsmart_backend.userconfiguration.domain.services.comunications.PaymentMethodResponse;
import com.techelper.tropsmart_backend.userconfiguration.domain.services.inputs.PaymentMethodInput;
import com.techelper.tropsmart_backend.userconfiguration.infrastructure.servicesImp.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/payment-methods")
public class PaymentMethodController {
    @Autowired
    private PaymentMethodService paymentMethodService;

    @GetMapping
    public ResponseEntity<PaymentMethodResponse> findAllPaymentMethods()
    {
        PaymentMethodResponse result = paymentMethodService.findAllPaymentMethod();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("users/{userId}")
    public ResponseEntity<PaymentMethodResponse> findPaymentMethodsByUser(@PathVariable(value = "userId")int userId)
    {
        PaymentMethodResponse result = paymentMethodService.findPaymentMethodByUserId(userId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("users/{userId}")
    public ResponseEntity<PaymentMethodResponse> addPaymentMethod(@PathVariable(value = "userId")int userId, @Valid @RequestBody PaymentMethodInput paymentMethodInput)
    {
        PaymentMethodResponse result = paymentMethodService.addPaymentMethodByUserId(userId, paymentMethodInput);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
