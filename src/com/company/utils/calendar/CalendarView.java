package com.company.utils.calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import static com.company.utils.AnsiColorCodes.*;

public class CalendarView{
    private boolean highlight = false;
    private int dayToHighlight;

    public void printCalendarMonthYear(int month, int year) {
        Calendar now = Calendar.getInstance();
        int monthNow = now.get(Calendar.MONTH) + 1;
        int yearNow = now.get(Calendar.YEAR);
        boolean highlightCurrentDate = false;
        if(yearNow == year && monthNow == month){
            highlight = true;
            dayToHighlight = now.get(Calendar.DAY_OF_MONTH);
        }
        else{
            highlight = false;
        }
        Calendar cal = new GregorianCalendar();
        cal.clear();
        cal.set(year, month - 1, 1); // setting the calendar to the month and year provided as parameters
        System.out.println(ANSI_YELLOW + "__________________________" + ANSI_RESET);
        System.out.println("Calendar for "+ cal.getDisplayName(java.util.Calendar.MONTH, java.util.Calendar.LONG,
                Locale.US) + " " + cal.get(Calendar.YEAR));//to print Calendar for month and year

        int firstWeekdayOfMonth = cal.get(Calendar.DAY_OF_WEEK);//which weekday was the first in month
        int numberOfMonthDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH); //lengh of days in a month
        printCalendar(numberOfMonthDays, firstWeekdayOfMonth);
    }


    private void printCalendar(int numberOfMonthDays, int firstWeekdayOfMonth) {

        int weekdayIndex = 0;
        System.out.println(ANSI_YELLOW + "Su  MO  Tu  We  Th  Fr  Sa" + ANSI_RESET); // The order of days depends on your calendar
        for (int day = 1; day < firstWeekdayOfMonth; day++) {
            System.out.print("    "); //this loop to print the first day in his correct place
            weekdayIndex++;
        }
        for (int day = 1; day <= numberOfMonthDays; day++) {

            if (day<10) { // this is just for better visialising because unit number take less space of course than 2
                if (highlight && day == dayToHighlight) {
                    System.out.print(ANSI_RED + day + " " + ANSI_RESET);
                }
                else {
                    System.out.print(day + " ");
                }
            }
            else {
                if(highlight && day == dayToHighlight) {
                    System.out.print(ANSI_RED + day + ANSI_RESET);
                }
                else{
                    System.out.print(day);
                }
            }
            weekdayIndex++;
            if (weekdayIndex == 7) {
                weekdayIndex = 0;
                System.out.println();
            } else {
                System.out.print("  ");
            }
        }
        System.out.println();
        System.out.println(ANSI_YELLOW + "__________________________" + ANSI_RESET);
        System.out.println("MOTD:" + CalendarService.getMOTD());
        System.out.println(ANSI_YELLOW + "__________________________" + ANSI_RESET);
    }


}
