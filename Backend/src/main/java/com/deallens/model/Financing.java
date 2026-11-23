package com.deallens.model;

public record Financing(Integer id , double percentageLoanAmount , double Capital , double intrest , double repayment  ) {
    double loanAmount(double propertyPrice)
    {
        return propertyPrice * percentageLoanAmount;
    }

    double debtServicePerMonth(double loanAmount)
    {
        return ((this.intrest - this.repayment) * loanAmount ) / 12;
    }
}