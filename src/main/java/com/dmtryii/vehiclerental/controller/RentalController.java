package com.dmtryii.vehiclerental.controller;

import com.dmtryii.vehiclerental.dto.RentalCalculateResponse;
import com.dmtryii.vehiclerental.dto.RentalCreate;
import com.dmtryii.vehiclerental.dto.RentalUpdate;
import com.dmtryii.vehiclerental.entity.Rental;
import com.dmtryii.vehiclerental.service.RentalCalculator;
import com.dmtryii.vehiclerental.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/rental")
@RestController
public class RentalController {

    private final RentalService rentalService;
    private final RentalCalculator rentalCalculator;

    @PostMapping
    public ResponseEntity<Rental> create(@RequestBody RentalCreate request) {
        return ResponseEntity.ok(
                rentalService.create(request)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Rental> update(@PathVariable long id,
                                         @RequestBody RentalUpdate request) {
        return ResponseEntity.ok(
                rentalService.update(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> cancel(@PathVariable Long id) {
        rentalService.cancel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/calculate")
    public ResponseEntity<RentalCalculateResponse> calculate(@PathVariable long id) {
        Rental rental = rentalService.getById(id);
        double price = rentalCalculator.calculateTotalCost(rental);

        RentalCalculateResponse response = RentalCalculateResponse.builder()
                .rental(rental)
                .price(price)
                .build();

        return ResponseEntity.ok(
                response
        );
    }
}
