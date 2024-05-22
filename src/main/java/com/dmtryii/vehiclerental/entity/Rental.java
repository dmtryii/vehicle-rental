package com.dmtryii.vehiclerental.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Vehicle vehicle;
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @ManyToOne
    private Customer customer;
    @OneToMany(fetch = FetchType.EAGER)
    private List<AdditionalService> additionalServices;

    public Rental(Vehicle vehicle,
                  LocalDate beginning,
                  LocalDate end,
                  Customer customer,
                  List<AdditionalService> additionalServices) {
        this.vehicle = vehicle;
        this.startDate = beginning;
        this.endDate = end;
        this.customer = customer;
        this.additionalServices = additionalServices;
    }
}
