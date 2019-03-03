package com.company;

import java.util.GregorianCalendar;

public class CalendarController {
    private CalendarView view;
    private java.util.Calendar cal;
    private int month;
    private int year;


    public CalendarController(){
        this.view = view;
        this.cal = new GregorianCalendar();
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year){
        this.year = year;
    }
    public void setMonthYear(int month, int year){
        setMonth(month);
        setYear(year);
    }

    public void updateView(){
        view.printCalendarMonthYear(month,year,cal);
    }
}
