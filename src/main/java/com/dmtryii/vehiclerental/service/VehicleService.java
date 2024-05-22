package com.dmtryii.vehiclerental.service;

import com.dmtryii.vehiclerental.entity.Vehicle;

public interface VehicleService {
    Vehicle getById(long id);
    Vehicle create(Vehicle vehicle);
}
