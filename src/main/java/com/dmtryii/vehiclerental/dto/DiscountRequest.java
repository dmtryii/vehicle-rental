package com.dmtryii.vehiclerental.dto;

import lombok.Data;

@Data
public class DiscountRequest {
    private long customerId;
    private int amount;
}
