package com.dmtryii.vehiclerental.service;

import com.dmtryii.vehiclerental.entity.Rental;

public interface RentalCalculator {
    double calculateTotalCost(Rental rental);
}
