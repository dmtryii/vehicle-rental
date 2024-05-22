package com.dmtryii.vehiclerental.service.impl;

import com.dmtryii.vehiclerental.entity.Customer;
import com.dmtryii.vehiclerental.helper.exception.CustomerNotFoundException;
import com.dmtryii.vehiclerental.repository.CustomerRepository;
import com.dmtryii.vehiclerental.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer getById(long id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException("Customer not found with id: " + id)
        );
    }

    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }
}
