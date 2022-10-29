package com.techelper.tropsmart_backend.cargo.domain.services;

import com.techelper.tropsmart_backend.cargo.domain.models.Service;
import com.techelper.tropsmart_backend.cargo.domain.services.comunications.ServiceResponse;
import com.techelper.tropsmart_backend.configuration.domain.services.ICrudService;

public interface IServiceService extends ICrudService<Service> {
    ServiceResponse findSomeServiceByDriverId(int driverId);
    ServiceResponse findServicesByDriverId(int driverId);
    ServiceResponse findAllServices();
    ServiceResponse createService(int driverId);
}
