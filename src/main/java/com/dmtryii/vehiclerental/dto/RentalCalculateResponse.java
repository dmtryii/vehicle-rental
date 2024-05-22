package com.dmtryii.vehiclerental.dto;

import com.dmtryii.vehiclerental.entity.Rental;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RentalCalculateResponse {
    private Rental rental;
    private double price;
}
