package com.company;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import static com.company.AnsiColorCodes.*;
public class EventService {


    public EventService() {
    }

    private void printEventDetails(Event ev){
        System.out.println("Event name: " + ev.getTitle());
        System.out.println("Location: " + ev.getLocation());
        System.out.println("Date and time: " + ev.getDate().getDayOfMonth() + " " + ev.getDate().getMonth() + " " + ev.getDate().getYear() + " " + ev.getDate().getHour() + ":" + ev.getDate().getMinute());
        System.out.println("Note: " + ev.getNote());
    }



    public void showAllEvents(){
        ArrayList<Event> listEvents = StorageManager.getListEvents();
        boolean isThereEvent = false;
        for(Event event: listEvents){
            int i=1;
            System.out.println(ANSI_YELLOW + i++ + ")" + "---------------------" + ANSI_RESET);
            printEventDetails(event);
            System.out.println(ANSI_YELLOW  + "-----------------------" + ANSI_RESET);
            isThereEvent = true;
        }
        if(!isThereEvent){
            System.out.println("No events found for current account.");
        }
    }

    public void showAllEventsForCurrentMonth(){
        ArrayList<Event> listEvents = StorageManager.getListEvents();
        LocalDateTime ldt = LocalDateTime.now();
        boolean isThereEvent = false;
        for(Event event: listEvents){
            int i=1;
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

    public void showAllEventsBetweenTwoDates(ChronoLocalDateTime date1, ChronoLocalDateTime date2){
        ArrayList<Event> listEvents = StorageManager.getListEvents();
        boolean isThereEvent = false;
        for(Event event: listEvents){
            int i=1;
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
