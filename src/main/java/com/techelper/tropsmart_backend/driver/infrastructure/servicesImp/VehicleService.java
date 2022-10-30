package com.techelper.tropsmart_backend.driver.infrastructure.servicesImp;
import com.techelper.tropsmart_backend.configuration.application.exception.ResourceNotFoundException;
import com.techelper.tropsmart_backend.driver.domain.models.Driver;
import com.techelper.tropsmart_backend.driver.domain.models.Soat;
import com.techelper.tropsmart_backend.driver.domain.models.Vehicle;
import com.techelper.tropsmart_backend.driver.domain.repositories.IDriverRepository;
import com.techelper.tropsmart_backend.driver.domain.repositories.ISoatRepository;
import com.techelper.tropsmart_backend.driver.domain.repositories.IVehicleRepository;
import com.techelper.tropsmart_backend.driver.domain.services.IVehicleService;
import com.techelper.tropsmart_backend.driver.domain.services.comunications.VehicleResponse;
import com.techelper.tropsmart_backend.driver.domain.services.inputs.VehicleInput;
import com.techelper.tropsmart_backend.driver.domain.services.outputs.VehicleOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService implements IVehicleService {

    @Autowired
    private IVehicleRepository vehicleRepository;

    @Autowired
    private IDriverRepository driverRepository;

    @Autowired
    private ISoatRepository soatRepository;

    @Transactional
    @Override
    public Vehicle save(Vehicle vehicle) throws Exception {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        vehicleRepository.deleteById(id);
    }

    @Override
    public Optional<Vehicle> findById(Integer id) throws Exception {
        return vehicleRepository.findById(id);
    }

    @Override
    public List<Vehicle> findAll() throws Exception {
        return vehicleRepository.findAll();
    }


    @Override
    public VehicleResponse findVehiclesByDriverId(int driverId) {
        try
        {
            List<Vehicle> vehicles = vehicleRepository.getVehiclesByDriverId(driverId);
            List<VehicleOutput> vehicleOutputList = new ArrayList<>();
            for (Vehicle v:vehicles) {
                VehicleOutput newVehicleOutput = new VehicleOutput();
                newVehicleOutput.setDriver(v.getDriver().getPerson().getFirstName()+" "+v.getDriver().getPerson().getLastName());
                newVehicleOutput.setModel(v.getModel());
                newVehicleOutput.setBrand(v.getBrand());
                newVehicleOutput.setLoadingCapacity(v.getLoadingCapacity());
                vehicleOutputList.add(newVehicleOutput);
            }
            return new VehicleResponse(vehicleOutputList);
        }
        catch (Exception e)
        {
            return new VehicleResponse("An error ocurred while getting the vehicle list : "+e.getMessage());
        }
    }

    @Override
    public VehicleResponse addVehicleByUserId(int driverId, VehicleInput vehicleInput) {
        try
        {
            Driver getDriver = driverRepository.findById(driverId)
                    .orElseThrow(()-> new ResourceNotFoundException("driver","id",driverId));

            Soat newSoat = new Soat();
            newSoat.setAssociateName(getDriver.getPerson().getFirstName()+" "+getDriver.getPerson().getLastName());
            newSoat.setEmissionDate(Calendar.getInstance().getTime());
            newSoat.setExpireDate(Calendar.getInstance().getTime());
            newSoat.setServiceType("Servicio de carga");

            newSoat = soatRepository.save(newSoat);

            Vehicle newVehicle = new Vehicle();
            newVehicle.setBrand(vehicleInput.getBrand());
            newVehicle.setLoadingCapacity(vehicleInput.getLoadingCapacity());
            newVehicle.setModel(vehicleInput.getModel());
            newVehicle.setFabricationYear(Calendar.getInstance().getTime());
            newVehicle.setOwnershipCard(vehicleInput.getOwnershipCard());

            newVehicle.setDriver(getDriver);
            newVehicle.setSoat(newSoat);

            newVehicle = vehicleRepository.save(newVehicle);

            VehicleOutput newVehicleOutput = new VehicleOutput();
            newVehicleOutput.setDriver(newVehicle.getDriver().getPerson().getFirstName()+" "+newVehicle.getDriver().getPerson().getLastName());
            newVehicleOutput.setLoadingCapacity(newVehicle.getLoadingCapacity());
            newVehicleOutput.setBrand(newVehicle.getBrand());
            newVehicleOutput.setModel(newVehicle.getModel());

            return new VehicleResponse(newVehicleOutput);
        }
        catch (Exception e)
        {
            return new VehicleResponse("An error ocurred while saving the vehicle : "+e.getMessage());
        }

    }

    @Override
    public VehicleResponse findAllVehicles() {
        try
        {
            List<Vehicle> vehicles = vehicleRepository.findAll();
            List<VehicleOutput> vehicleOutputList = new ArrayList<>();
            for (Vehicle v:vehicles) {
                VehicleOutput newVehicleOutput = new VehicleOutput();
                newVehicleOutput.setDriver(v.getDriver().getPerson().getFirstName()+" "+v.getDriver().getPerson().getLastName());
                newVehicleOutput.setModel(v.getModel());
                newVehicleOutput.setBrand(v.getBrand());
                newVehicleOutput.setLoadingCapacity(v.getLoadingCapacity());
                vehicleOutputList.add(newVehicleOutput);
            }
            return new VehicleResponse(vehicleOutputList);
        }
        catch (Exception e)
        {
            return new VehicleResponse("An error ocurred while getting the vehicles list : "+e.getMessage());
        }
    }

    @Override
    public VehicleResponse findVehicleById(int vehicleId) {
        try
        {
            Vehicle getVehicle = vehicleRepository.findById(vehicleId).get();
            VehicleOutput newVehicleOutput = new VehicleOutput();
            newVehicleOutput.setDriver(getVehicle.getDriver().getPerson().getFirstName()+" "+getVehicle.getDriver().getPerson().getLastName());
            newVehicleOutput.setModel(getVehicle.getModel());
            newVehicleOutput.setBrand(getVehicle.getBrand());
            newVehicleOutput.setLoadingCapacity(getVehicle.getLoadingCapacity());
            return new VehicleResponse(newVehicleOutput);
        }
        catch (Exception e)
        {
            return new VehicleResponse("An error ocurred while getting the vehicle : "+e.getMessage());
        }
    }
}