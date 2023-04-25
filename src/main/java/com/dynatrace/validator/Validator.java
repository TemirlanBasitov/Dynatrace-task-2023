package com.dynatrace.validator;

import java.time.LocalDate;
import java.time.Year;


public class Validator {
    public static boolean isFuture(LocalDate date){
        LocalDate now = LocalDate.now();
        if(date.isAfter(now)){
            return true;
        }
        else {
            return false;
        }
    }
    public static boolean isHoliday(LocalDate date){
        String dayOfWeek = date.getDayOfWeek().toString();
        if(dayOfWeek.equalsIgnoreCase("SATURDAY") || dayOfWeek.equalsIgnoreCase("SUNDAY")){
            return true;
        }
        else {
            return false;
        }
    }
    public static  boolean isValidQuotation(int number){
        if(number > 0 && number < 256){
            return true;
        }
        else{
            return false;
        }
    }
}
