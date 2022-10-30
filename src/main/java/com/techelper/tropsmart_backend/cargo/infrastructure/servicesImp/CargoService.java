package com.techelper.tropsmart_backend.cargo.infrastructure.servicesImp;

import com.techelper.tropsmart_backend.cargo.domain.models.Cargo;
import com.techelper.tropsmart_backend.cargo.domain.models.Location;
import com.techelper.tropsmart_backend.cargo.domain.models.ServiceRequest;
import com.techelper.tropsmart_backend.cargo.domain.repositories.ICargoRepository;
import com.techelper.tropsmart_backend.cargo.domain.repositories.ILocationRepository;
import com.techelper.tropsmart_backend.cargo.domain.repositories.IServiceRepository;
import com.techelper.tropsmart_backend.cargo.domain.repositories.IServiceRequestRepository;
import com.techelper.tropsmart_backend.cargo.domain.services.ICargoService;
import com.techelper.tropsmart_backend.cargo.domain.services.comunications.CargoResponse;
import com.techelper.tropsmart_backend.cargo.domain.services.inputs.CargoInput;
import com.techelper.tropsmart_backend.cargo.domain.services.outputs.CargoOutput;
import com.techelper.tropsmart_backend.configuration.application.exception.ResourceNotFoundException;
import com.techelper.tropsmart_backend.customer.domain.models.Customer;
import com.techelper.tropsmart_backend.customer.domain.repositories.ICustomerRepository;
import com.techelper.tropsmart_backend.user.domain.models.Balance;
import com.techelper.tropsmart_backend.user.domain.models.Price;
import com.techelper.tropsmart_backend.user.domain.models.User;
import com.techelper.tropsmart_backend.user.domain.repositories.IBalanceRepository;
import com.techelper.tropsmart_backend.user.domain.repositories.IPriceRepository;
import com.techelper.tropsmart_backend.user.domain.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CargoService implements ICargoService {

    @Autowired
    private ICargoRepository cargoRepository;

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private IPriceRepository priceRepository;
    @Autowired
    private IBalanceRepository balanceRepository;
    @Autowired
    private IServiceRepository serviceRepository;
    @Autowired
    private IServiceRequestRepository serviceRequestRepository;
    @Autowired
    private ILocationRepository locationRepository;




    @Override
    public Cargo save(Cargo cargo) throws Exception {
        return cargoRepository.save(cargo);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        cargoRepository.deleteById(id);
    }

    @Override
    public Optional<Cargo> findById(Integer id) throws Exception {
        return cargoRepository.findById(id);
    }

    @Override
    public List<Cargo> findAll() throws Exception {
        return cargoRepository.findAll();
    }


    @Override
    public CargoResponse findCargoesByCustomerId(int customerId) {
        try
        {
            List<Cargo> cargoes = cargoRepository.findCargoesByCustomerId(customerId);
            List<CargoOutput> cargoOutputList = new ArrayList<>();
            for (Cargo c:cargoes) {
                cargoOutputList.add(toCargoOutput(c));
            }
            return new CargoResponse(cargoOutputList);
        }
        catch (Exception e)
        {
            return new CargoResponse("An error ocurred while getting cargo list: "+e.getMessage());
        }
    }

    @Override
    public CargoResponse addCargoByCustomerId(int customerId, CargoInput cargoInput) {

        try
        {
            com.techelper.tropsmart_backend.cargo.domain.models.Service getService = serviceRepository.findById(cargoInput.getServiceId()).get();
            Customer getCustomer = customerRepository.findById(customerId)
                    .orElseThrow(()->new ResourceNotFoundException("customer","id",customerId));
            User getUser = userRepository.findUserByPersonId(getCustomer.getPerson().getId())
                    .orElseThrow(()->new ResourceNotFoundException("customer","id",customerId));

            Balance getBalance = getUser.getBalance();
            if(getCustomer.getCredits() - cargoInput.getServicePrice()<0)
                return new CargoResponse("You dont have enough credits");
            Price newPrice = new Price();
            newPrice.setTotalPrice((double) cargoInput.getServicePrice());
            newPrice.setTax(((double) cargoInput.getServicePrice()) * 0.19);
            newPrice.setPriceType(2);
            newPrice = priceRepository.save(newPrice);

            getBalance.setSpentMoney(getBalance.getSpentMoney() + newPrice.getTotalPrice());
            getCustomer.setCredits(getCustomer.getCredits() - newPrice.getTotalPrice());
            getBalance = balanceRepository.save(getBalance);
            getCustomer = customerRepository.save(getCustomer);

            Cargo newCargo = new Cargo();
            newCargo.setCustomer(getCustomer);
            newCargo.setService(getService);
            newCargo.setDescription(cargoInput.getDescription());
            newCargo.setWeight(cargoInput.getWeight());
            newCargo.setPrice(newPrice);
            newCargo.setCargoType("Chemicals");
            newCargo.setCargoStatus("Awaiting");
            newCargo = cargoRepository.save(newCargo);

            Location newLocation = new Location();
            newLocation.setArrivalAltitude(1.0);
            newLocation.setArrivalLatitude(2.0);
            newLocation.setArrivalLongitude(3.0);
            newLocation.setDepartureAltitude(4.0);
            newLocation.setDepartureLatitude(5.0);
            newLocation.setDepartureLongitude(6.0);
            newLocation.setRoute(getService.getRoute());
            newLocation.setCargo(newCargo);
            newLocation = locationRepository.save(newLocation);

            return new CargoResponse(toCargoOutput(newCargo));
        }
        catch (Exception e)
        {
            return new CargoResponse("An error ocurred while registering a cargo: "+e.getMessage());
        }

    }

    @Override
    public CargoResponse findCargoById(int cargoId) {
        try
        {
            Cargo getCargo = cargoRepository.findById(cargoId).get();

            return new CargoResponse(toCargoOutput(getCargo));
        }
        catch (Exception e)
        {
            return new CargoResponse("An error ocurred while getting a cargo: "+e.getMessage());
        }


    }

    @Override
    public CargoResponse findAllCargoes() {
        try
        {
            List<Cargo> cargoes = cargoRepository.findAll();
            List<CargoOutput> cargoOutputList = new ArrayList<>();
            for (Cargo c:cargoes) {
                cargoOutputList.add(toCargoOutput(c));
            }
            return new CargoResponse(cargoOutputList);
        }
        catch (Exception e)
        {
            return new CargoResponse("An error ocurred while getting the cargo list: "+e.getMessage());
        }
    }

    @Override
    public CargoResponse findAllCargoesFixed() {
        try
        {
            List<Cargo> cargoes = cargoRepository.findAll();
            List<CargoOutput> cargoOutputList = new ArrayList<>();
            for (Cargo c:cargoes) {
                cargoOutputList.add(toCargoOutput(c));
            }
            return new CargoResponse(cargoOutputList);
        }
        catch (Exception e)
        {
            return new CargoResponse("An error ocurred while getting the cargo list: "+e.getMessage());
        }
    }

    @Override
    public CargoResponse setCargoDelivered(int cargoId) {
        try
        {
            Cargo getCargo = cargoRepository.findById(cargoId).get();
            User getUser = userRepository.findUserByPersonId(getCargo.getCustomer().getPerson().getId())
                    .orElseThrow(()->new ResourceNotFoundException("user","id",cargoId));
            Customer getCustomer = getCargo.getCustomer();
            Balance getBalance = getUser.getBalance();

            getCargo.setCargoStatus("Done");
            getCargo = cargoRepository.save(getCargo);

            return new CargoResponse(toCargoOutput(getCargo));
        }
        catch (Exception e)
        {
            return new CargoResponse("An error ocurred while getting the cargo list: "+e.getMessage());
        }
    }


    @Override
    public CargoResponse findCargoesByDriverId(int driverId) {
        try
        {
            ServiceRequest getServiceRequest = serviceRequestRepository.findServiceByDriverId(driverId);
            List<CargoOutput> cargoOutputList = new ArrayList<>();

            for (com.techelper.tropsmart_backend.cargo.domain.models.Service s:getServiceRequest.getServicesList()) {
                for(Cargo c:s.getCargoList())
                {
                    cargoOutputList.add(toCargoOutput(c));
                }
            }
            return new CargoResponse(cargoOutputList);
        }
        catch (Exception e)
        {
            return new CargoResponse("An error ocurred while getting the cargo list : "+e.getMessage());
        }
    }

    @Override
    public CargoResponse findRequestedCargoesByDriverId(int driverId) {
        try
        {
            ServiceRequest getServiceRequest = serviceRequestRepository.findServiceByDriverId(driverId);
            List<CargoOutput> cargoOutputList = new ArrayList<>();

            for (com.techelper.tropsmart_backend.cargo.domain.models.Service s:getServiceRequest.getServicesList()) {
                for(Cargo c:s.getCargoList())
                {
                    if(c.getCargoStatus().equals("Awaiting"))
                    {
                        cargoOutputList.add(toCargoOutput(c));
                    }

                }
            }
            return new CargoResponse(cargoOutputList);
        }
        catch (Exception e)
        {
            return new CargoResponse("An error ocurred while getting the cargo list : "+e.getMessage());
        }
    }

    @Override
    public CargoResponse findConfirmedCargoesByDriverId(int driverId) {
        try
        {
            ServiceRequest getServiceRequest = serviceRequestRepository.findServiceByDriverId(driverId);
            List<CargoOutput> cargoOutputList = new ArrayList<>();

            for (com.techelper.tropsmart_backend.cargo.domain.models.Service s:getServiceRequest.getServicesList()) {
                for(Cargo c:s.getCargoList())
                {
                    if(c.getCargoStatus().equals("In process"))
                    {
                        cargoOutputList.add(toCargoOutput(c));
                    }
                }
            }
            return new CargoResponse(cargoOutputList);
        }
        catch (Exception e)
        {
            return new CargoResponse("An error ocurred while getting the cargo list : "+e.getMessage());
        }
    }

    @Override
    public CargoResponse findFinishedCargoesByDriverId(int driverId) {
        try
        {
            ServiceRequest getServiceRequest = serviceRequestRepository.findServiceByDriverId(driverId);
            List<CargoOutput> cargoOutputList = new ArrayList<>();

            for (com.techelper.tropsmart_backend.cargo.domain.models.Service s:getServiceRequest.getServicesList()) {
                for(Cargo c:s.getCargoList())
                {
                    if(c.getCargoStatus().equals("Done"))
                    {
                        cargoOutputList.add(toCargoOutput(c));
                    }
                }
            }
            return new CargoResponse(cargoOutputList);
        }
        catch (Exception e)
        {
            return new CargoResponse("An error ocurred while getting the cargo list : "+e.getMessage());
        }
    }



    @Override
    public CargoResponse confirmCargoRequest(int cargoId) {
        try
        {
            Cargo getCargo = cargoRepository.findById(cargoId).get();
            User getUser = userRepository.findUserByPersonId(getCargo.getCustomer().getPerson().getId())
                    .orElseThrow(()->new ResourceNotFoundException("user","id",cargoId));
            Customer getCustomer = getCargo.getCustomer();
            Balance getBalance = getUser.getBalance();
            getCargo.setCargoStatus("In process");
            getCargo = cargoRepository.save(getCargo);

            return new CargoResponse(toCargoOutput(getCargo));
        }
        catch (Exception e)
        {
            return new CargoResponse("An error ocurred while getting the cargo list: "+e.getMessage());
        }
    }

    @Override
    public CargoResponse rejectCargoById(int cargoId) {
        try
        {
            Cargo getCargo = cargoRepository.findById(cargoId).get();
            User getUser = userRepository.findUserByPersonId(getCargo.getCustomer().getPerson().getId())
                    .orElseThrow(()->new ResourceNotFoundException("user","id",cargoId));
            Customer getCustomer = getCargo.getCustomer();
            Balance getBalance = getUser.getBalance();
            getCargo.setCargoStatus("Rejected");
            getCargo = cargoRepository.save(getCargo);

            return new CargoResponse(toCargoOutput(getCargo));
        }
        catch (Exception e)
        {
            return new CargoResponse("An error ocurred while getting the cargo list: "+e.getMessage());
        }
    }


    public CargoOutput toCargoOutput(Cargo cargo){
        CargoOutput newCargoOutput = new CargoOutput();
        newCargoOutput.setId(cargo.getId());
        newCargoOutput.setWeight(cargo.getWeight());
        newCargoOutput.setCustomer(cargo.getCustomer().getPerson().getFirstName()+" "+cargo.getCustomer().getPerson().getFirstName());
        newCargoOutput.setDriver(cargo.getService().getServicesRequest().getDriver().getPerson().getFirstName()+" "+cargo.getService().getServicesRequest().getDriver().getPerson().getLastName());
        newCargoOutput.setStartTime(cargo.getService().getStartTime());
        newCargoOutput.setFinishTime(cargo.getService().getFinishTime());
        newCargoOutput.setServicePrice(cargo.getPrice().getTotalPrice());
        newCargoOutput.setDescription(cargo.getDescription());
        newCargoOutput.setCargoType(cargo.getCargoType().toString());
        newCargoOutput.setCargoStatus(cargo.getCargoStatus());

        return newCargoOutput;
    }




}