package com.dmtryii.vehiclerental.service.impl;

import com.dmtryii.vehiclerental.dto.RentalCreate;
import com.dmtryii.vehiclerental.dto.RentalUpdate;
import com.dmtryii.vehiclerental.entity.AdditionalService;
import com.dmtryii.vehiclerental.entity.Customer;
import com.dmtryii.vehiclerental.entity.Rental;
import com.dmtryii.vehiclerental.entity.Vehicle;
import com.dmtryii.vehiclerental.helper.exception.RentalNotFoundException;
import com.dmtryii.vehiclerental.repository.RentalRepository;
import com.dmtryii.vehiclerental.service.AdditionalServiceService;
import com.dmtryii.vehiclerental.service.CustomerService;
import com.dmtryii.vehiclerental.service.RentalService;
import com.dmtryii.vehiclerental.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RentalServiceImpl implements RentalService {

    private final CustomerService customerService;
    private final VehicleService vehicleService;
    private final AdditionalServiceService additionalServiceService;
    private final RentalRepository rentalRepository;

    @Override
    public Rental getById(long id) {
        return rentalRepository.findById(id).orElseThrow(
                ()-> new RentalNotFoundException("Rental not found with id: " + id)
        );
    }

    /**
     * Створює новий прокат.
     * @param rentalCreate Прокат, який потрібно створити.
     * @return Створений прокат.
     */
    @Override
    public Rental create(RentalCreate rentalCreate) {

        Vehicle vehicle = vehicleService.getById(rentalCreate.getVehicleId());
        Customer customer = customerService.getById(rentalCreate.getCustomerId());

        List<AdditionalService> additionalServices =
                additionalServiceService.getByIds(rentalCreate.getAdditionalServicesId());

        Rental rental = new Rental(
                vehicle,
                rentalCreate.getBeginning(),
                rentalCreate.getEnd(),
                customer,
                additionalServices
        );

        return rentalRepository.save(rental);
    }

    /**
     * Оновлює інформацію про прокат.
     * @param id Ідентифікатор прокату, який потрібно оновити.
     * @param updatedRental Оновлена інформація про прокат.
     * @return Оновлений прокат.
     * @throws RentalNotFoundException Якщо прокат з вказаним ідентифікатором не знайдено.
     */
    @Override
    public Rental update(Long id, RentalUpdate updatedRental) {
        Rental rental = getById(id);

        rental.setVehicle(vehicleService.getById(updatedRental.getVehicleId()));
        rental.setAdditionalServices(
                additionalServiceService.getByIds(
                        updatedRental.getAdditionalServicesId()
                ));
        rental.setStartDate(updatedRental.getBeginning());
        rental.setEndDate(updatedRental.getEnd());

        return rentalRepository.save(rental);
    }

    /**
     * Скасувати прокат.
     * @param id Ідентифікатор прокату, який потрібно скасувати.
     * @throws RentalNotFoundException Якщо прокат з вказаним ідентифікатором не знайдено.
     */
    @Override
    public void cancel(Long id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RentalNotFoundException("Rental not found with id: " + id));

        rentalRepository.delete(rental);
    }
}
