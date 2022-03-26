package com.bank.backend.customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerRepositoryTest {

    @Autowired private CustomerRepository customerRepository;

    @Test
    public void saveCustomer(){
        Customer customer1 = new Customer("Jason", "Cormier");
        Customer customer2 = new Customer("Omar", "Hussein");
        Customer customer3 = new Customer("Soham", "Thaker");

        customerRepository.saveAll(List.of(customer1, customer2, customer3));
    }

    @Test
    public void printAllCustomers(){
        List<Customer> customerList = customerRepository.findAll();
        System.out.println(customerList);
    }
}