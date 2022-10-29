package com.techelper.tropsmart_backend.customer.domain.repositories;

import com.techelper.tropsmart_backend.customer.domain.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

}