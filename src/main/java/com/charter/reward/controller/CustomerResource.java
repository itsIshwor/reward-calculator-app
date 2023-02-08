package com.charter.reward.controller;

import com.charter.reward.model.Customer;
import com.charter.reward.model.Purchase;
import com.charter.reward.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/customers")
public class CustomerResource {
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping
    public List<Customer> createCustomers(@RequestBody List<Customer> customers) {
        return this.customerRepository.saveAll(customers);
    }

    @PostMapping("/{customerId}/purchases")
    public ResponseEntity<Customer> createPurchases(@PathVariable Long customerId,
                                                    @RequestBody List<Purchase> purchases) {
        Customer customer = this.customerRepository.findById(customerId).get();
        purchases.forEach(customer::addToPurchases);

        return new ResponseEntity(this.customerRepository.save(customer), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }
}
