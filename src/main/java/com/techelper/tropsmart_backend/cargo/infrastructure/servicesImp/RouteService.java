package com.techelper.tropsmart_backend.cargo.infrastructure.servicesImp;

import com.techelper.tropsmart_backend.cargo.domain.models.Location;
import com.techelper.tropsmart_backend.cargo.domain.models.Route;
import com.techelper.tropsmart_backend.cargo.domain.repositories.ICargoRepository;
import com.techelper.tropsmart_backend.cargo.domain.repositories.ILocationRepository;
import com.techelper.tropsmart_backend.cargo.domain.repositories.IRouteRepository;
import com.techelper.tropsmart_backend.cargo.domain.services.IRouteService;
import com.techelper.tropsmart_backend.cargo.domain.services.comunications.RouteResponse;
import com.techelper.tropsmart_backend.cargo.domain.services.outputs.RouteOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RouteService implements IRouteService {

    @Autowired
    IRouteRepository routeRepository;

    @Autowired
    ICargoRepository cargoRepository;

    @Autowired
    ILocationRepository locationRepository;

    @Override
    public RouteResponse getRouteInfo(int cargoId) {
        try
        {
            Location getLocation = locationRepository.findLocationByCargoId(cargoId);

            double distance = Math.sqrt(Math.pow(getLocation.getDepartureLongitude()-
                    getLocation.getDepartureLatitude(),2)+Math.pow(getLocation.getArrivalLongitude()-
                    getLocation.getArrivalLatitude(),2));

            int drivingTime = (int)(distance/80);

            RouteOutput newRouteOutput = new RouteOutput();
            newRouteOutput.setDepartureLocation(getLocation.getDepartureLatitude()+" "+getLocation.getDepartureLongitude()
                    +" "+getLocation.getDepartureAltitude());
            newRouteOutput.setArrivalLocation(getLocation.getArrivalLatitude()+" "+getLocation.getArrivalLongitude()
                    +" "+getLocation.getArrivalAltitude());
            newRouteOutput.setDistance(distance);
            newRouteOutput.setEstimedTime(drivingTime);

            return new RouteResponse(newRouteOutput);
        }
        catch (Exception e)
        {
            return new RouteResponse("An error ocurred while getting the route info : "+e.getMessage());
        }
    }

    @Override
    public RouteResponse findAllRoutes() {
        try
        {
            List<Route> routeList = routeRepository.findAll();
            List<RouteOutput> routeOutputList = new ArrayList<>();
            for (Route r:routeList) {

                Location getLocation = locationRepository.findLocationByRouteId(r.getId());

                double distance = Math.sqrt(Math.pow(getLocation.getDepartureLongitude()-
                        getLocation.getDepartureLatitude(),2)+Math.pow(getLocation.getArrivalLongitude()-
                        getLocation.getArrivalLatitude(),2));

                int drivingTime = (int)(distance/80);

                RouteOutput newRouteOutput = new RouteOutput();
                newRouteOutput.setDepartureLocation(getLocation.getDepartureLatitude()+" "+getLocation.getDepartureLongitude()
                        +" "+getLocation.getDepartureAltitude());
                newRouteOutput.setArrivalLocation(getLocation.getArrivalLatitude()+" "+getLocation.getArrivalLongitude()
                        +" "+getLocation.getArrivalAltitude());
                newRouteOutput.setDistance(distance);
                newRouteOutput.setEstimedTime(drivingTime);

                routeOutputList.add(newRouteOutput);
            }
            return new RouteResponse(routeOutputList);
        }
        catch (Exception e)
        {
            return new RouteResponse("An error ocurred while getting the route list : "+e.getMessage());
        }
    }

    @Override
    public RouteResponse findRouteById(int routeId) {
        try {
            Route getRoute = routeRepository.findById(routeId).get();
            Location getLocation = locationRepository.findLocationByRouteId(getRoute.getId());
            double distance = Math.sqrt(Math.pow(getLocation.getDepartureLongitude()-
                    getLocation.getDepartureLatitude(),2)+Math.pow(getLocation.getArrivalLongitude()-
                    getLocation.getArrivalLatitude(),2));

            int drivingTime = (int)(distance/80);

            RouteOutput newRouteOutput = new RouteOutput();
            newRouteOutput.setDepartureLocation(getLocation.getDepartureLatitude()+" "+getLocation.getDepartureLongitude()
                    +" "+getLocation.getDepartureAltitude());
            newRouteOutput.setArrivalLocation(getLocation.getArrivalLatitude()+" "+getLocation.getArrivalLongitude()
                    +" "+getLocation.getArrivalAltitude());
            newRouteOutput.setDistance(distance);
            newRouteOutput.setEstimedTime(drivingTime);

            return new RouteResponse(newRouteOutput);
        }
        catch (Exception e)
        {
            return new RouteResponse("An error ocurred while getting the route list : "+e.getMessage());
        }
    }

    @Override
    public Route save(Route route) throws Exception {
        return routeRepository.save(route);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        routeRepository.deleteById(id);
    }

    @Override
    public Optional<Route> findById(Integer id){
        return routeRepository.findById(id);
    }

    @Override
    public List<Route> findAll() throws Exception {
        return routeRepository.findAll();
    }
}
