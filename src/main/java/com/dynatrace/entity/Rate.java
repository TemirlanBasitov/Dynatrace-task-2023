package com.dynatrace.entity;

import java.time.LocalDate;

public class Rate {
    private String no;
    private LocalDate effectiveDate;
    private Float mid;
    private Float bid;
    private Float ask;

    public Float getBid() {
        return bid;
    }

    public void setBid(Float bid) {
        this.bid = bid;
    }

    public Float getAsk() {
        return ask;
    }

    public void setAsk(Float ask) {
        this.ask = ask;
    }

    public String getNo() {
        return no;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public Float getMid() {
        return mid;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "no='" + no + '\'' +
                ", effectiveDate=" + effectiveDate +
                ", mid=" + mid +
                '}';
    }
}
