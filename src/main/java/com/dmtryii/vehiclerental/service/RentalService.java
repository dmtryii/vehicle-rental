package com.dmtryii.vehiclerental.service;

import com.dmtryii.vehiclerental.dto.RentalCreate;
import com.dmtryii.vehiclerental.dto.RentalUpdate;
import com.dmtryii.vehiclerental.entity.Rental;

public interface RentalService {
    Rental getById(long id);
    Rental create(RentalCreate rental);
    Rental update(Long id, RentalUpdate updatedRental);
    void cancel(Long id);
}
