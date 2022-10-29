package com.techelper.tropsmart_backend.driver.domain.services;

import com.techelper.tropsmart_backend.cargo.domain.models.Location;
import com.techelper.tropsmart_backend.configuration.domain.services.ICrudService;
import com.techelper.tropsmart_backend.driver.domain.models.Driver;
import com.techelper.tropsmart_backend.driver.domain.services.comunications.DriverResponse;

public interface IDriverService extends ICrudService<Driver> {
    DriverResponse findNearDrivers(Location location);
    DriverResponse findDriverById(int driverId);
    DriverResponse findAllDrivers();
}