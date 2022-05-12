package com.kuhnenagel.repo;

import com.kuhnenagel.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepo extends JpaRepository<Customer,Long> {

}
