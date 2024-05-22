package com.dmtryii.vehiclerental;

import com.dmtryii.vehiclerental.entity.Rental;
import com.dmtryii.vehiclerental.repository.DiscountRepository;
import com.dmtryii.vehiclerental.service.impl.RentalCalculatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RentalCalculatorImplTest {
    private RentalCalculatorImpl rentalCalculator;

    @Mock
    private DiscountRepository discountRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        rentalCalculator = new RentalCalculatorImpl(discountRepository);
    }

    @Test
    public void calculateTotalCost_NullRental_ThrowsIllegalArgumentException() {
        // Arrange
        Rental rental = null;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            rentalCalculator.calculateTotalCost(rental);
        });
        assertEquals("Rental cannot be null", exception.getMessage());
    }

    @Test
    public void priceCalculationInTimeInterval_NullPricePerDay_ThrowsIllegalArgumentException() {
        // Arrange
        double pricePerDay = 0.0;
        LocalDate start = LocalDate.now();
        LocalDate end = start.plusDays(1);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            rentalCalculator.priceCalculationInTimeInterval(pricePerDay, start, end);
        });
        assertEquals("Price per day cannot be zero or negative", exception.getMessage());
    }

    @Test
    public void priceCalculationInTimeInterval_NullStart_ThrowsIllegalArgumentException() {
        // Arrange
        double pricePerDay = 100.0;
        LocalDate start = null;
        LocalDate end = LocalDate.now().plusDays(1);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            rentalCalculator.priceCalculationInTimeInterval(pricePerDay, start, end);
        });
        assertEquals("Start date cannot be null", exception.getMessage());
    }

    @Test
    public void priceCalculationInTimeInterval_EndBeforeStart_ThrowsIllegalArgumentException() {
        // Arrange
        double pricePerDay = 100.0;
        LocalDate start = LocalDate.now();
        LocalDate end = start.minusDays(1);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            rentalCalculator.priceCalculationInTimeInterval(pricePerDay, start, end);
        });
        assertEquals("End date must be after start date", exception.getMessage());
    }
}
