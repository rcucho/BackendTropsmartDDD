package com.techelper.tropsmart_backend.driver.domain.services;

import com.techelper.tropsmart_backend.configuration.domain.services.ICrudService;
import com.techelper.tropsmart_backend.driver.domain.models.Vehicle;
import com.techelper.tropsmart_backend.driver.domain.services.comunications.VehicleResponse;
import com.techelper.tropsmart_backend.driver.domain.services.inputs.VehicleInput;

public interface IVehicleService extends ICrudService<Vehicle> {
    VehicleResponse findVehiclesByDriverId(int driverId);
    VehicleResponse addVehicleByUserId(int driverId, VehicleInput vehicleInput);
    VehicleResponse findAllVehicles();
    VehicleResponse findVehicleById(int vehicleId);
}