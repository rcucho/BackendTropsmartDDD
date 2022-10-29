package com.techelper.tropsmart_backend.cargo.domain.repositories;

import com.techelper.tropsmart_backend.cargo.domain.models.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IServiceRequestRepository extends JpaRepository<ServiceRequest, Integer> {
    @Query("select s from ServiceRequest s where s.driver.id = (:driverId)")
    ServiceRequest findServiceByDriverId(@Param("driverId")int driverId);


}