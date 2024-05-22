package com.dmtryii.vehiclerental.service.impl;

import com.dmtryii.vehiclerental.entity.Vehicle;
import com.dmtryii.vehiclerental.repository.VehicleRepository;
import com.dmtryii.vehiclerental.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Override
    public Vehicle getById(long id) {
        return vehicleRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Vehicle not fount")
        );
    }

    @Override
    public Vehicle create(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }
}
