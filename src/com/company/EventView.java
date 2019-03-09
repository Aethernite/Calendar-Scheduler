package com.company;

public class EventView {
    public void printEventDetails(Event ev){
        System.out.println("Event name: " + ev.getEventName());
        System.out.println("Location: " + ev.getLocation());
        if(ev.isLongerThanOneDay() == true){
            System.out.println("From date: " + ev.getStartDate() + " to " + ev.getEndDate());
        }
        else{
            System.out.println("Date: " + ev.getStartDate());
        }
        System.out.println("Note: " + ev.getNote());
    }
}
