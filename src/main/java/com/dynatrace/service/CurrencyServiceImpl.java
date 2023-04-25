package com.dynatrace.service;

import com.dynatrace.entity.Currency;
import com.dynatrace.entity.Rate;
import com.dynatrace.exception.*;
import com.dynatrace.validator.Validator;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;


@Service
public class CurrencyServiceImpl implements CurrencyService{

    private String baseUrl = "http://api.nbp.pl/api/exchangerates/rates";

    @Override
    public Float getAvgRateByCodeAndDate(String code, LocalDate date) {
        if(Validator.isFuture(date)){
            throw new FutureDayException("Given date range is future date");
        }
        if (Validator.isHoliday(date)){
            throw new WeekendException("You have chosen weekend day");
        }
        else {
            String dateStr = date + "";
            String endpoint = String.format("/a/%s/%s", code, dateStr);
            String apiUrl = String.format("%s%s",baseUrl,endpoint);
            RestTemplate rest = new RestTemplate();
            Currency response = rest.getForObject(apiUrl, Currency.class);
            return response.getRates()[0].getMid();
        }

    }

    @Override
    public String getMaxAndMinAvgQuotation(String code, Integer number) {

        if(Validator.isValidQuotation(number)){
            String endpoint = String.format("/a/%s/last/%s/?format=json", code, number);
            String apiUrl = String.format("%s%s", baseUrl, endpoint);
            RestTemplate rest = new RestTemplate();
            Currency response = rest.getForObject(apiUrl, Currency.class);
            Rate minRate = new Rate();
            Rate maxRate = new Rate();
            Float max = response.getRates()[0].getMid();
            Float min = response.getRates()[0].getMid();
            for(Rate rate : response.getRates()) {
                if (rate.getMid() <= min) {
                    minRate = rate;
                }
                if(rate.getMid()>= max){
                    maxRate = rate;
                }
            }
            return "Maximum rate average date: " +  maxRate.getEffectiveDate() + ", value is: " + maxRate.getMid() + "\n"
                    + "Minimum rate average date: " + minRate.getEffectiveDate() + ", value is: " + minRate.getMid() + "\n" +
                    "for last " + number + " quotations on " + response.getCode();
        }
        else{
            throw new OutOfRangeQuotation("Invalid number of quotations, choose 1 and 255 inclusively");
        }

    }

    @Override
    public String getMajorDifference(String code, Integer number) {
            if(Validator.isValidQuotation(number)){
                String endpoint = String.format("/c/%s/last/%s/?format=json", code, number);
                String apiUrl = String.format("%s%s", baseUrl, endpoint);
                RestTemplate rest = new RestTemplate();
                Currency response = rest.getForObject(apiUrl, Currency.class);
                Rate maxDifferenceRate = new Rate();
                Float difference = response.getRates()[0].getAsk() - response.getRates()[0].getBid();
                for(Rate rate : response.getRates()){
                    Float currentDifference = rate.getAsk() - rate.getBid();
                    if(Float.compare(currentDifference, difference) == 0 || Float.compare(currentDifference, difference)==1 ){
                        maxDifferenceRate = rate;
                        difference = currentDifference;
                    }
                }
                return "Major difference between the buy and sell rate for last  " + number +  " quotations  was on: " + maxDifferenceRate.getEffectiveDate()
                        + ", value is: " + difference + " code is: " + response.getCode();
            }
            else {
                throw new OutOfRangeQuotation("Invalid number of quotations, choose 1 and 255 inclusively");
            }
    }
}
