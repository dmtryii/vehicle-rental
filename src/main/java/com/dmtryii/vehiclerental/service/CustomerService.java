package com.dmtryii.vehiclerental.service;

import com.dmtryii.vehiclerental.entity.Customer;

public interface CustomerService {
    Customer getById(long id);
    Customer create(Customer customer);
}
