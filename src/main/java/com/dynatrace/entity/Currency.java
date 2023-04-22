package com.dynatrace.entity;

import java.util.Arrays;

public class Currency {
    private String table;
    private String currency;
    private String code;
    private Rate[] rates;

    public String getTable() {
        return table;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCode() {
        return code;
    }

    public Rate[] getRates() {
        return rates;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setRates(Rate[] rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "table='" + table + '\'' +
                ", name='" + currency + '\'' +
                ", code='" + code + '\'' +
                ", rates=" + Arrays.toString(rates) +
                '}';
    }
}
