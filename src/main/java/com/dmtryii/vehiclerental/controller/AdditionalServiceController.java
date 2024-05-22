package com.dmtryii.vehiclerental.controller;

import com.dmtryii.vehiclerental.entity.AdditionalService;
import com.dmtryii.vehiclerental.service.AdditionalServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/additional-service")
@RestController
public class AdditionalServiceController {

    private final AdditionalServiceService additionalServiceService;

    @PostMapping
    public ResponseEntity<AdditionalService> create(@RequestBody AdditionalService additionalService) {
        return ResponseEntity.ok(
                additionalServiceService.create(additionalService)
        );
    }
}
