package com.dmtryii.vehiclerental.repository;

import com.dmtryii.vehiclerental.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
