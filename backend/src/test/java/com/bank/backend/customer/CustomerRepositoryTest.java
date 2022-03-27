package com.bank.backend.customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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

    //find a customer, create a new account, pass that acc to that cx, save that cx to the db

    @Test
    public void printAllCustomers(){
        List<Customer> customerList = customerRepository.findAll();
        System.out.println(customerList);
    }
}