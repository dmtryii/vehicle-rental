package com.dmtryii.vehiclerental.controller;

import com.dmtryii.vehiclerental.dto.DiscountRequest;
import com.dmtryii.vehiclerental.entity.Discount;
import com.dmtryii.vehiclerental.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/discount")
@RestController
public class DiscountController {

    private final DiscountService discountService;

    @PostMapping
    public ResponseEntity<Discount> create(@RequestBody DiscountRequest request) {
        return ResponseEntity.ok(
                discountService.create(request.getCustomerId(), request.getAmount())
        );
    }
}
