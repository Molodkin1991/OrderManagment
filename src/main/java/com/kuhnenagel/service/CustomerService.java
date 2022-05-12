package com.kuhnenagel.service;

import com.kuhnenagel.model.Customer;
import com.kuhnenagel.repo.CustomersRepo;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomersRepo customersRepo;

    public CustomerService(CustomersRepo customersRepo) {
        this.customersRepo = customersRepo;
    }

    public Customer createCustomer(Integer code, String name, String email, Integer phone) {
        Customer customer = new Customer();
        customer.setFullName(name);
        customer.setEmail(email);
        customer.setRegistrationCode(code);
        customer.setTelephone(phone);

        return customersRepo.save(customer);
    }
}
