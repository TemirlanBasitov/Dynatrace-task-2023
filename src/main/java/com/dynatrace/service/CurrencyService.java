package com.dynatrace.service;

import java.time.LocalDate;

public interface CurrencyService {
    Float getAvgRateByCodeAndDate(String code, LocalDate date);
    String getMaxAndMinAvgQuotation(String code, Integer number);
    String getMajorDifference(String code, Integer number);
}
