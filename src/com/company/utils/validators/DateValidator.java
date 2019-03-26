package com.company.utils.validators;

import java.time.DateTimeException;
import java.time.LocalDate;

public class DateValidator {

    public static boolean isDateValid(String date) {
        boolean dateIsValid = true;
        String[] dateSplit = date.split("-");
        int day = Integer.parseInt(dateSplit[0]);
        int month = Integer.parseInt(dateSplit[1]);
        int year = Integer.parseInt(dateSplit[2]);
        if(!isYearValid(year)){
            System.out.println("Enter valid date!");
            return false;
        }
        try {
            LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            System.out.println("Enter valid date!");
            dateIsValid = false;
        }
        return dateIsValid;
    }


    private static boolean isYearValid(int year){
        boolean yearIsValid = false;

        if(year>=1970){
            yearIsValid=true;
        }

        return yearIsValid;
    }
}