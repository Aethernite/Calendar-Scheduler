package com.company;

import java.util.Calendar;
import java.util.Scanner;

public class CalendarService{
    private CalendarView view;
    private int month;
    private int year;


    public CalendarService() {
        this.view = new CalendarView();
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonthYear(int month, int year) {
        setMonth(month);
        setYear(year);
        updateView();
    }

    public void updateView() {
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
}
