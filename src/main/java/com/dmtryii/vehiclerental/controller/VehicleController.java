package com.dmtryii.vehiclerental.controller;

import com.dmtryii.vehiclerental.entity.Vehicle;
import com.dmtryii.vehiclerental.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/vehicle")
@RestController
public class VehicleController {

    private final VehicleRepository vehicleRepository;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public ResponseEntity<Vehicle> create(@RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(
                vehicleRepository.save(vehicle)
        );
    }
}
