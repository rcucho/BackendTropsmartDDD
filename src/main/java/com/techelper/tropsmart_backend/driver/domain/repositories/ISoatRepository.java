package com.techelper.tropsmart_backend.driver.domain.repositories;

import com.techelper.tropsmart_backend.driver.domain.models.Soat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISoatRepository extends JpaRepository<Soat, Integer> {
}
