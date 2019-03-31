package com.company.utils.calendar;

import java.util.Calendar;

public class CalendarService{
    private static CalendarView view = new CalendarView();
    private static int month;
    private static int year;


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


}
