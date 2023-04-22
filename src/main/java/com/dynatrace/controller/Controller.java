package com.dynatrace.controller;

import com.dynatrace.entity.Currency;
import com.dynatrace.entity.Rate;
import com.dynatrace.service.CurrencyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;


@RestController
@RequestMapping("/api/exchange")
public class Controller {
    private CurrencyService currencyService;
    public Controller(CurrencyService currencyService){
        this.currencyService = currencyService;
    }
    @GetMapping("/a/{code}/{date}")
    public ResponseEntity<Float> getAvgRateByCodeAndDate(@PathVariable String code, @PathVariable LocalDate date){
        Float response = currencyService.getAvgRateByCodeAndDate(code, date);
        return new ResponseEntity<Float>(response, HttpStatus.OK);
    }
    @GetMapping("/a/{code}/last/{number}")
    public ResponseEntity<String> getMaxAndMinAvgQuotation(@PathVariable String code, @PathVariable Integer number){
        String response = currencyService.getMaxAndMinAvgQuotation(code, number);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    @GetMapping("/c/{code}/last/{number}")
    public ResponseEntity<String> getMajorDifference(@PathVariable String code, @PathVariable Integer number){
        String response = currencyService.getMajorDifference(code, number);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }
}
