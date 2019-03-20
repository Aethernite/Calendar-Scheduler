package com.company.utils;

import com.company.objects.Event;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import static com.company.utils.AnsiColorCodes.*;

public class EventService {


    private static void printEventDetails(Event ev){
        System.out.println("Event name: " + ev.getTitle());
        System.out.println("Location: " + ev.getLocation());
        System.out.println("Date and time: " + ev.getDate().getDayOfMonth() + " " + ev.getDate().getMonth() + " " + ev.getDate().getYear() + " " + ev.getDate().getHour() + ":" + String.format("%02d",ev.getDate().getMinute()));
        System.out.println("Note: " + ev.getNote());
    }



    public static boolean showAllEvents(){
        ArrayList<Event> listEvents = StorageManager.getListEvents();
        boolean isThereEvent = false;
        int i=1;
        for(Event event: listEvents){
            System.out.println(ANSI_YELLOW + i++ + ")" + "---------------------" + ANSI_RESET);
            printEventDetails(event);
            System.out.println(ANSI_YELLOW  + "-----------------------" + ANSI_RESET);
            isThereEvent = true;
        }
        if(!isThereEvent){
            System.out.println("No events found for current account.");
            return false;
        }
        return true;
    }

    public static void showAllEventsForCurrentMonth(){
        ArrayList<Event> listEvents = StorageManager.getListEvents();
        LocalDateTime ldt = LocalDateTime.now();
        boolean isThereEvent = false;
        int i=1;
        for(Event event: listEvents){
            if(event.getDate().getMonthValue() == ldt.getMonthValue()){
                System.out.println(ANSI_YELLOW + i++ + ")" + "---------------------" + ANSI_RESET);
                printEventDetails(event);
                System.out.println(ANSI_YELLOW  + "-----------------------" + ANSI_RESET);
                isThereEvent = true;
            }
        }
        if(!isThereEvent){
            System.out.println("No events found for the month.");
        }
    }

    public static void showAllEventsBetweenTwoDates(ChronoLocalDateTime date1, ChronoLocalDateTime date2){
        ArrayList<Event> listEvents = StorageManager.getListEvents();
        boolean isThereEvent = false;
        int i=1;
        if(date1.isEqual(date2)){
            System.out.println(ANSI_RED + "The dates are equal!" + ANSI_RESET);
            return;
        }
        ChronoLocalDateTime first;
        ChronoLocalDateTime second;
        if(date1.isAfter(date2)){
            first = date2;
            second = date1;
        }
        else{
            first = date1;
            second = date2;
        }

        for(Event event: listEvents){
            if(event.getDate().isAfter(date1) && event.getDate().isBefore(date2)){
                System.out.println(ANSI_YELLOW + i++ + ")" + "---------------------" + ANSI_RESET);
                printEventDetails(event);
                System.out.println(ANSI_YELLOW  + "-----------------------" + ANSI_RESET);
                isThereEvent = true;
            }
        }
        if(!isThereEvent){
            System.out.println("No events found between the two dates.");
        }
    }

}
