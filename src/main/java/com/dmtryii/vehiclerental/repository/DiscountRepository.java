package com.dmtryii.vehiclerental.repository;

import com.dmtryii.vehiclerental.entity.Customer;
import com.dmtryii.vehiclerental.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
    List<Discount> findAllByCustomer(Customer customer);
}
