package com.dynatrace.service;

import com.dynatrace.entity.Currency;
import com.dynatrace.entity.Rate;
import com.dynatrace.exception.*;
import com.dynatrace.validator.Validator;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.util.Date;

@Service
public class CurrencyServiceImpl implements CurrencyService{

    private String baseUrl = "http://api.nbp.pl/api/exchangerates/rates";

    @Override
    public Float getAvgRateByCodeAndDate(String code, LocalDate date) {
        if(Validator.isFuture(date)){
            throw new FutureDayException("Given date range is incorrect");
        }
        if (Validator.isHoliday(date)){
            throw new WeekendException("You have chosen holiday");
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

        if(number > 0 && number <=255){
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
            return "Maximum rate average was on: " +  maxRate.getEffectiveDate() + ", value is: " + maxRate.getMid() + "\n"
                    + "Minimum was on: " + minRate.getEffectiveDate() + ", value is: " + minRate.getMid();
        }
        else{
            throw new OutOfRangeQuotation("Given number of quotation should be between 1 and 255 inclusively");
        }

    }

    @Override
    public String getMajorDifference(String code, Integer number) {
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
        return "Major difference between the buy and sell rate was on: " + maxDifferenceRate.getEffectiveDate()
                + ", value is: " + difference ;
    }
}
