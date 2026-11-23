package com.deallens.deal;


import com.deallens.model.Deal;
import com.deallens.model.Financing;
import com.deallens.model.MonthlyRent;
import com.deallens.model.Property;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DealService {

    List<Deal> deals;

    public DealService() {
        deals = List.of(
                new Deal(
                        1,
                        // id, address, city, squareMeters, purchasePrice, allocableCosts, nonAllocableCosts
                        new Property(1, "Musterstraße 1", "Hamburg", 75, 200000, 200, 50),
                        // id, rentPerSquareMeter, rentParkingSpace
                        new MonthlyRent(1, 10.50, 50),
                        // id, percentageLoanAmount, capital, interest, repayment
                        new Financing(1, 0.90, 41140, 0.0484, 0.02)
                ),
                new Deal(
                        2,
                        new Property(2, "Beispielweg 12", "Hamburg", 65, 230000, 100, 60),
                        new MonthlyRent(2, 11.00, 0),
                        new Financing(2, 0.80, 70311, 0.0410, 0.025)
                )
        );
    }


    public List<Deal> getAllDeals() {
        return deals;
    }

    public Optional<Deal> getDealById(Integer id) {
        return deals.stream()
                .filter(deal -> deal.id().equals(id))
                .findFirst();
    }

}
