package com.techelper.tropsmart_backend.cargo.domain.repositories;

import com.techelper.tropsmart_backend.cargo.domain.models.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRouteRepository extends JpaRepository<Route, Integer> {
}