package com.techelper.tropsmart_backend.user.domain.repositories;

import com.techelper.tropsmart_backend.user.domain.models.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPriceRepository extends JpaRepository<Price, Integer> {

    @Query("select p from Price p where p.priceType = (:ptype)")
    List<Price> findPricesByType(@Param("ptype")int priceType);
}