package com.dmtryii.vehiclerental.service.impl;

import com.dmtryii.vehiclerental.entity.AdditionalService;
import com.dmtryii.vehiclerental.entity.Discount;
import com.dmtryii.vehiclerental.entity.Rental;
import com.dmtryii.vehiclerental.repository.DiscountRepository;
import com.dmtryii.vehiclerental.service.RentalCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@RequiredArgsConstructor
@Service
public class RentalCalculatorImpl implements RentalCalculator {

    private final DiscountRepository discountRepository;

    /**
     * Обчислює загальну вартість прокату, враховуючи вартість транспортного засобу за день,
     * тривалість прокату, вартість додаткових послуг та будь-яких застосовних знижок.
     * @param rental Прокат, для якого потрібно обчислити загальну вартість.
     * @return Загальна вартість прокату.
     */
    @Override
    public double calculateTotalCost(Rental rental) {

        // Обчислити вартість за весь період часу
        double priceOfVehiclePerDay = rental.getVehicle().getPrice();

        // Обчислити загальну вартість всіх додаткових послуг
        double priceOfEntirePeriodOfTime = priceCalculationInTimeInterval(
                priceOfVehiclePerDay,
                rental.getStartDate(),
                rental.getEndDate()
        );

        double priceOfAllAdditionalServices = rental.getAdditionalServices()
                .stream()
                .mapToDouble(AdditionalService::getPrice)
                .sum();

        // Обчислити загальну вартість всіх застосовних знижок для клієнта
        double priceOfAllDiscounts = discountRepository.findAllByCustomer(rental.getCustomer())
                .stream()
                .mapToDouble(Discount::getAmount)
                .sum();

        // Повернути загальну вартість прокату, враховуючи додаткові послуги та знижки
        return priceOfEntirePeriodOfTime + priceOfAllAdditionalServices - priceOfAllDiscounts;
    }

    /**
     * Обчислити вартість прокату транспортного засобу за вказаний проміжок часу.
     * @param priceOfVehiclePerDay Вартість транспортного засобу за день.
     * @param start Початкова дата та час прокату.
     * @param end Кінцева дата та час прокату.
     * @return Вартість прокату за вказаний проміжок часу.
     */
    public double priceCalculationInTimeInterval(double priceOfVehiclePerDay,
                                                 LocalDate start,
                                                 LocalDate end) {
        Period period = Period.between(start, end);
        int days = period.getDays();

        return days * priceOfVehiclePerDay;
    }
}
