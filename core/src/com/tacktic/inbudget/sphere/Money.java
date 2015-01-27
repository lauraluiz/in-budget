package com.tacktic.inbudget.sphere;

import java.math.BigDecimal;

public class Money {
    private String currencyCode;
    private int centAmount;

    public Money() {

    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public double getCentAmount() {
        return centAmount;
    }

    public BigDecimal getAmount() {
        return new BigDecimal(centAmount).movePointLeft(2);
    }

    public String getAmountAsString() {
        return "$" + String.valueOf(centAmount / 100);
    }

    @Override
    public String toString() {
        return "Money{" +
                "currencyCode='" + currencyCode + '\'' +
                ", centAmount=" + centAmount +
                '}';
    }
}
