package com.dmtryii.vehiclerental.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class RentalCreate {
    private long vehicleId;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate beginning;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate end;
    private long customerId;
    private List<Long> additionalServicesId;
}
