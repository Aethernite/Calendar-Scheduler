package com.company;

import java.util.ArrayList;

public class EventService {



    private static void printEventDetails(Event ev){
        System.out.println("Event name: " + ev.getEventName());
        System.out.println("Location: " + ev.getLocation());
        if(ev.isLongerThanOneDay() == true){
            System.out.println("Date: " + ev.getStartDate());
        }
        else{
            System.out.println("From date: " + ev.getStartDate() + " to " + ev.getEndDate());
        }
        System.out.println("Note: " + ev.getNote());
    }


    public static void showAllEvents(){
        ArrayList<Event> listEvents = StorageManager.getListEvents();

        for(Event ev: listEvents){
            int i=1;
            System.out.println(i++ + ")" + "---------------------");
            printEventDetails(ev);

        }
    }


}
