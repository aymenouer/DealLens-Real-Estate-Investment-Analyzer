package com.deallens.model;

public record MonthlyRent(Integer id , double rentPerSquareMetters, double rentParkingSpace  ) {
    double monthlyColdRent(double squareMetters)
    {
        return (rentPerSquareMetters * squareMetters)+rentParkingSpace;
    }

    double monthlywarmRent(double squareMetters , double allocableCosts)
    {
        return this.monthlyColdRent(squareMetters) + allocableCosts;
    }
}