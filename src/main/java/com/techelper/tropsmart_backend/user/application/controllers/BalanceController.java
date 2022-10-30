package com.techelper.tropsmart_backend.user.application.controllers;

import com.techelper.tropsmart_backend.user.domain.services.IBalanceService;
import com.techelper.tropsmart_backend.user.domain.services.comunications.BalanceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/balances")
public class BalanceController {

    @Autowired
    private IBalanceService balanceService;

    @GetMapping()
    public ResponseEntity<BalanceResponse> findAllBalances()
    {
        BalanceResponse result = balanceService.findAllBalances();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/{balanceId}")
    public ResponseEntity<BalanceResponse> findBalanceById(@PathVariable(value = "balanceId")int balanceId)
    {
        BalanceResponse result =  balanceService.findBalanceById(balanceId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}