package com.techelper.tropsmart_backend.cargo.domain.services;

import com.techelper.tropsmart_backend.cargo.domain.models.Route;
import com.techelper.tropsmart_backend.cargo.domain.services.comunications.RouteResponse;
import com.techelper.tropsmart_backend.configuration.domain.services.ICrudService;

public interface IRouteService extends ICrudService<Route> {
    RouteResponse getRouteInfo(int cargoId);
    RouteResponse findAllRoutes();
    RouteResponse findRouteById(int routeId);
}
