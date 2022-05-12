package com.kuhnenagel.service;

import com.kuhnenagel.model.Customer;
import com.kuhnenagel.repo.CustomersRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CustomerServiceTest {
    private CustomersRepo customersRepo;
    private CustomerService customerService;

    @BeforeEach
    public void setup() {
        customersRepo = mock(CustomersRepo.class);
        customerService = new CustomerService(customersRepo);
    }

    @Test
    void contextLoads() {
        customerService.createCustomer(1, "Nikita", "some@gmail.eu", 565554553);
        verify(customersRepo, times(1))
                .save(new Customer(1, "Nikita", "some@gmail.eu", 565554553));
    }


}