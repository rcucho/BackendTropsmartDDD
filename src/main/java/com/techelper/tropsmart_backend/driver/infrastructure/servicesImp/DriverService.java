package com.techelper.tropsmart_backend.driver.infrastructure.servicesImp;

import com.techelper.tropsmart_backend.cargo.domain.models.Location;
import com.techelper.tropsmart_backend.driver.domain.models.Driver;
import com.techelper.tropsmart_backend.driver.domain.repositories.IDriverRepository;
import com.techelper.tropsmart_backend.driver.domain.services.IDriverService;
import com.techelper.tropsmart_backend.driver.domain.services.comunications.DriverResponse;
import com.techelper.tropsmart_backend.driver.domain.services.outputs.DriverOutput;
import com.techelper.tropsmart_backend.user.domain.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService implements IDriverService {

    @Autowired
    IDriverRepository driverRepository;

    @Autowired
    IUserRepository userRepository;


    @Override
    public Driver save(Driver driver) throws Exception {
        return driverRepository.save(driver);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        driverRepository.deleteById(id);
    }

    @Override
    public Optional<Driver> findById(Integer id) throws Exception {
        return driverRepository.findById(id);
    }

    @Override
    public List<Driver> findAll() throws Exception {
        return driverRepository.findAll();
    }


    @Override
    public DriverResponse findNearDrivers(Location location) {
        return null;
    }

    @Override
    public DriverResponse findDriverById(int driverId) {
        try
        {
            Driver getDriver = driverRepository.findById(driverId).get();
            return new DriverResponse(new DriverOutput(getDriver.getPerson().getUser().getId(),getDriver.getPerson().getFirstName(),getDriver.getPerson().getLastName(),getDriver.getLicense(),getDriver.getPerson().getUser().getEmail(),getDriver.getPerson().getPersonType(),getDriver.getId()));
        }
        catch (Exception e)
        {
            return new DriverResponse("An error ocurred while getting driver: "+e.getMessage());
        }
    }


    @Override
    public DriverResponse findAllDrivers() {
        try
        {
            List<Driver> drivers = driverRepository.findAll();
            List<DriverOutput> driverOutputList = new ArrayList<>();
            for (Driver getDriver:drivers) {
                driverOutputList.add(new DriverOutput(getDriver.getPerson().getUser().getId(),getDriver.getPerson().getFirstName(),getDriver.getPerson().getLastName(),getDriver.getLicense(),getDriver.getPerson().getUser().getEmail(),getDriver.getPerson().getPersonType(),getDriver.getId()));
            }
            return new DriverResponse(driverOutputList);
        }
        catch (Exception e)
        {
            return new DriverResponse("An error ocurred while getting driver list: "+e.getMessage());
        }
    }
}
