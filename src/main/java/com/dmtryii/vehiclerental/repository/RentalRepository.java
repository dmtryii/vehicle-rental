package com.dmtryii.vehiclerental.repository;

import com.dmtryii.vehiclerental.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface RentalRepository extends JpaRepository<Rental, Long> {
}
