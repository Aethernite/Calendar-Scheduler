package com.company;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Scanner;

public class CalendarService{
    private CalendarView view;
    private int month;
    private int year;
    private static String[] MOTDS = new String[30];


    public CalendarService(){

        this.view = new CalendarView();
    }

    public void setMonth(int month){
        this.month = month;
    }

    public void setYear(int year){
        this.year = year;
    }

    public void setMonthYear(int month, int year) {
        setMonth(month);
        setYear(year);
        updateView();
    }

    public void updateView(){
        view.printCalendarMonthYear(month, year);
    }

    public void defaultPrint() {
        Calendar cal = Calendar.getInstance();
        month = cal.get(Calendar.MONTH) + 1;
        year = cal.get(Calendar.YEAR);
        setMonthYear(month, year);
    }

    public void printPreviousMonth(){
        if(month-1<1){
            month = 12;
        }
        else{
            month--;
        }
        updateView();
    }

    public void printNextMonth(){
        if(month+1>12){
            month = 1;
            year++;
        }
        else{
            month++;
        }
        updateView();
    }

    //Message of the day methods
    public static String getMOTD(){
        LocalDate ld = LocalDate.now();
        if(MOTDS[ld.getDayOfMonth()-1] == null){
            return "No message of the day is found";
        }
        return MOTDS[ld.getDayOfMonth()];
    }

    public static void setMOTD(int day,String MOTD){
        MOTDS[day-1] = MOTD;
    }
}
