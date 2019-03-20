package com.company.utils.validators;

public class TimeValidator {

    public static boolean isTimeValid(String[] time){
        int hours = Integer.parseInt(time[0]);
        int minutes = Integer.parseInt(time[1]);
        if(hours<=23 && hours>=0){
            if(minutes>=0 && minutes <=59){
                return true;
            }
        }
        System.out.println("Enter valid time!");
        return false;
    }
}
