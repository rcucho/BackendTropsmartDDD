package com.techelper.tropsmart_backend.cargo.application.controllers;

import com.techelper.tropsmart_backend.cargo.domain.services.comunications.RouteResponse;
import com.techelper.tropsmart_backend.cargo.infrastructure.servicesImp.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/routes")
public class RoutesController {

    @Autowired
    private RouteService routeService;

    @GetMapping
    public ResponseEntity<RouteResponse> findAllRoutes()
    {
        RouteResponse result = routeService.findAllRoutes();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{routeId}")
    public ResponseEntity<RouteResponse> findRouteById(@PathVariable(value="routeId")int routeId)
    {
        RouteResponse result = routeService.findRouteById(routeId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

