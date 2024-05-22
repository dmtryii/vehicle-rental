package com.dmtryii.vehiclerental.service;

import com.dmtryii.vehiclerental.entity.Discount;

public interface DiscountService {
    Discount create(long customerId, int amount);
}
