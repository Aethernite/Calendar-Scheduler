package com.company.utils.calendar;

import java.time.LocalDate;
import java.util.Calendar;

public class CalendarService{
    private static CalendarView view;
    private static int month;
    private static int year;
    private static String[] MOTDS = new String[30];


    public CalendarService(){

        this.view = new CalendarView();
    }

    public static void setMonth(int newMonth){
        month = newMonth;
    }

    public static void setYear(int newYear){
        year = newYear;
    }

    public static void setMonthYear(int month, int year) {
        setMonth(month);
        setYear(year);
        updateView();
    }

    public static void updateView(){
        view.printCalendarMonthYear(month, year);
    }

    public static void defaultPrint() {
        Calendar cal = Calendar.getInstance();
        month = cal.get(Calendar.MONTH) + 1;
        year = cal.get(Calendar.YEAR);
        setMonthYear(month, year);
    }

    public static void printPreviousMonth(){
        if(month-1<1){
            month = 12;
            year--;
        }
        else{
            month--;
        }
        updateView();
    }

    public static void printNextMonth(){
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
