package com.dmtryii.vehiclerental.controller;

import com.dmtryii.vehiclerental.entity.Customer;
import com.dmtryii.vehiclerental.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        Customer newCustomer = customerService.create(customer);
        return ResponseEntity.ok(
                newCustomer
        );
    }
}
