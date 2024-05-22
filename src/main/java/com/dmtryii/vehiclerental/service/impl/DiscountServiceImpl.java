package com.dmtryii.vehiclerental.service.impl;

import com.dmtryii.vehiclerental.entity.Customer;
import com.dmtryii.vehiclerental.entity.Discount;
import com.dmtryii.vehiclerental.repository.DiscountRepository;
import com.dmtryii.vehiclerental.service.CustomerService;
import com.dmtryii.vehiclerental.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DiscountServiceImpl implements DiscountService {

    private final CustomerService customerService;
    private final DiscountRepository discountRepository;

    public Discount create(long customerId, int amount) {
        Customer customer = customerService.getById(customerId);
        Discount discount = Discount.builder()
                .customer(customer)
                .amount(amount)
                .build();
        return discountRepository.save(discount);
    }
}
