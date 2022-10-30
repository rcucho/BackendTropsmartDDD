package com.techelper.tropsmart_backend.user.application.controllers;

import com.techelper.tropsmart_backend.user.domain.services.comunications.SubscriptionResponse;
import com.techelper.tropsmart_backend.user.infrastructure.servicesImp.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionsController {
    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping
    public ResponseEntity<SubscriptionResponse> findAllSubscriptions()
    {
        SubscriptionResponse result = subscriptionService.findAllSubscriptions();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<SubscriptionResponse> findSubscriptionsByUserId(@PathVariable(value = "userId")int userId)
    {
        SubscriptionResponse result = subscriptionService.findSubscriptionsByUserId(userId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }


    @PostMapping("/users/{userId}/plans/{planId}")
    public ResponseEntity<SubscriptionResponse> subscribeDriver(@PathVariable(value = "userId")int userId, @PathVariable(value = "planId")int planId)
    {
        SubscriptionResponse result = subscriptionService.subscribe(userId, planId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PutMapping("{subscriptionId}/disable")
    public ResponseEntity<SubscriptionResponse> cancelSubscription(@PathVariable(value = "subscriptionId")int subscriptionId)
    {
        SubscriptionResponse result = subscriptionService.cancelSubscription(subscriptionId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("{subscriptionId}/enable")
    public ResponseEntity<SubscriptionResponse> enableSubscription(@PathVariable(value = "subscriptionId")int subscriptionId)
    {
        SubscriptionResponse result = subscriptionService.enableSubscriptionById(subscriptionId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("{subscriptionId}")
    public ResponseEntity<SubscriptionResponse> deleteSubscriptionBySubscriptionId(@PathVariable(value = "subscriptionId")int subscriptionId)
    {
        SubscriptionResponse result = subscriptionService.deleteSubscriptionBySubscriptionId(subscriptionId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}