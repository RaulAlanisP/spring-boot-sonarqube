package com.project.presentation.controller;

import com.project.persistence.entity.Customer;
import com.project.persistence.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerRepository customerRepository;

    /**
     * This controller handles CRUD operations for Customer entities.
     * It uses the CustomerRepository to interact with the database.
     */
    @GetMapping("/findAll")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(this.customerRepository.findAll());
    }

    /**
     * Retrieves a customer by their ID.
     * @param id the ID of the customer to retrieve
     * @return ResponseEntity containing the customer if found, or an error if not found
     */
    @GetMapping("/find/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = this.customerRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(customer);
    }

    /**
     * Updates an existing customer.
     * @param id the ID of the customer to update
     * @param customer the updated customer data
     * @return ResponseEntity containing the updated customer
     */
    @PostMapping("/save")
    public ResponseEntity<Customer> saveCustomer(Customer customer) {
        Customer savedCustomer = this.customerRepository.save(customer);
        return new ResponseEntity(savedCustomer, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatusCode> deleteCustomer(@PathVariable Long id) {
        this.customerRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}   
