package com.company;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class CalendarView {

    public static void printCalendarMonthYear(int month, int year, java.util.Calendar cal) {
        cal.clear();
        cal.set(year, month - 1, 1); // setting the calendar to the month and year provided as parameters
        System.out.println("Calendar for "+ cal.getDisplayName(java.util.Calendar.MONTH, java.util.Calendar.LONG,
                Locale.UK) + " " + cal.get(java.util.Calendar.YEAR));//to print Calendar for month and year

        int firstWeekdayOfMonth = cal.get(java.util.Calendar.DAY_OF_WEEK);//which weekday was the first in month
        int numberOfMonthDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH); //lengh of days in a month
        printCalendar(numberOfMonthDays, firstWeekdayOfMonth);
    }


    private static void printCalendar(int numberOfMonthDays, int firstWeekdayOfMonth) {
        int weekdayIndex = 0;
        System.out.println("Su  MO  Tu  We  Th  Fr  Sa"); // The order of days depends on your calendar

        for (int day = 1; day < firstWeekdayOfMonth; day++) {
            System.out.print("    "); //this loop to print the first day in his correct place
            weekdayIndex++;
        }
        for (int day = 1; day <= numberOfMonthDays; day++) {

            if (day<10) // this is just for better visialising because unit number take less space of course than 2
                System.out.print(day+" ");
            else System.out.print(day);
            weekdayIndex++;
            if (weekdayIndex == 7) {
                weekdayIndex = 0;
                System.out.println();
            } else {
                System.out.print("  ");
            }}}
}
