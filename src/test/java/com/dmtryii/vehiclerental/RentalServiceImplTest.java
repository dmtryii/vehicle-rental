package com.dmtryii.vehiclerental;

import com.dmtryii.vehiclerental.dto.RentalUpdate;
import com.dmtryii.vehiclerental.helper.exception.RentalNotFoundException;
import com.dmtryii.vehiclerental.repository.RentalRepository;
import com.dmtryii.vehiclerental.service.impl.RentalServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class RentalServiceImplTest {
    @Mock
    private RentalRepository rentalRepository;

    @InjectMocks
    private RentalServiceImpl rentalService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void update_NullId_ThrowsIllegalArgumentException() {
        // Arrange
        Long id = null;
        RentalUpdate updatedRental = new RentalUpdate();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            rentalService.update(id, updatedRental);
        });
        assertEquals("Rental ID cannot be null", exception.getMessage());
        verifyNoInteractions(rentalRepository);
    }

    @Test
    public void update_RentalNotFound_ThrowsRentalNotFoundException() {
        // Arrange
        Long id = 1L;
        RentalUpdate updatedRental = new RentalUpdate();
        when(rentalRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        RentalNotFoundException exception = assertThrows(RentalNotFoundException.class, () -> {
            rentalService.update(id, updatedRental);
        });
        assertEquals("Rental not found with id: 1", exception.getMessage());
        verify(rentalRepository, times(1)).findById(id);
        verifyNoMoreInteractions(rentalRepository);
    }

    @Test
    public void cancel_NullId_ThrowsIllegalArgumentException() {
        // Arrange
        Long id = null;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            rentalService.cancel(id);
        });
        assertEquals("Rental ID cannot be null", exception.getMessage());
        verifyNoInteractions(rentalRepository);
    }

    @Test
    public void cancel_RentalNotFound_ThrowsRentalNotFoundException() {
        // Arrange
        Long id = 1L;
        when(rentalRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        RentalNotFoundException exception = assertThrows(RentalNotFoundException.class, () -> {
            rentalService.cancel(id);
        });
        assertEquals("Rental not found with id: 1", exception.getMessage());
        verify(rentalRepository, times(1)).findById(id);
        verifyNoMoreInteractions(rentalRepository);
    }
}
