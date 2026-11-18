package com.deallens.property;

public record Property(Integer id , String city , double price , double monthlyRent , double monthlyOwnerCosts , double monthlyLoanPayment) {
    double grossYield()
    {
        return ((this.monthlyRent * 12 ) / this.price) * 100;
    }

    double netYield()
    {
        return (((this.monthlyRent - this.monthlyOwnerCosts) * 12 ) / this.price ) * 100;
    }
}
