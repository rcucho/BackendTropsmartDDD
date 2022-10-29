package com.techelper.tropsmart_backend.cargo.domain.services;

import com.techelper.tropsmart_backend.cargo.domain.models.Cargo;
import com.techelper.tropsmart_backend.cargo.domain.services.comunications.CargoResponse;
import com.techelper.tropsmart_backend.cargo.domain.services.inputs.CargoInput;
import com.techelper.tropsmart_backend.configuration.domain.services.ICrudService;
import org.springframework.stereotype.Service;

@Service
public interface ICargoService extends ICrudService<Cargo> {
    CargoResponse findCargoesByCustomerId(int customerId);
    CargoResponse addCargoByCustomerId(int customerId, CargoInput cargoInput);
    CargoResponse findCargoById(int cargoId);
    CargoResponse findAllCargoes();
    CargoResponse findAllCargoesFixed();
    CargoResponse confirmCargoRequest(int cargoId);
    CargoResponse setCargoDelivered(int cargoId);
    CargoResponse rejectCargoById(int cargoId);
    CargoResponse findCargoesByDriverId(int driverId);
    CargoResponse findRequestedCargoesByDriverId(int driverId);
    CargoResponse findConfirmedCargoesByDriverId(int driverId);
    CargoResponse findFinishedCargoesByDriverId(int driverId);
}