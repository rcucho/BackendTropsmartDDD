package com.techelper.tropsmart_backend.driver.domain.repositories;

import com.techelper.tropsmart_backend.driver.domain.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IReviewRepository extends JpaRepository<Review, Integer> {
    @Query("select r from Review r where r.cargo.customer.id = (:customerId)")
    List<Review> findReviewsByCustomerId(@Param("customerId")int customerId);

    @Query("select r from Review r where r.cargo.service.servicesRequest.driver.id = (:driverId)")
    List<Review> findReviewsByDriverId(@Param("driverId")int driverId);
}