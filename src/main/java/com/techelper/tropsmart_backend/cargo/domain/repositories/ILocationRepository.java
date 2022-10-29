package com.techelper.tropsmart_backend.cargo.domain.repositories;

import com.techelper.tropsmart_backend.cargo.domain.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ILocationRepository extends JpaRepository<Location, Integer> {

    @Query("select l from Location l where l.cargo.id = (:uid)")
    Location findLocationByCargoId(@Param("uid") int cargoId);

    @Query("select l from Location l where l.route.id = (:uid)")
    Location findLocationByRouteId(@Param("uid") int routeId);
}