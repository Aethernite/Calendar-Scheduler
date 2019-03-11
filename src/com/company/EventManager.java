package com.company;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Scanner;

public class EventManager {



    public static void createEvent(){
        //Title input
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter event title:");
        String title = sc.nextLine();
        //Location input
        System.out.println("Event location:");
        String location = sc.nextLine();
        //Date input
        System.out.println("Enter date(dd-mm-year):");
        String[] dateInput = sc.nextLine().split("-");
        int dayStart = Integer.parseInt(dateInput[0]);
        int monthStart = Integer.parseInt(dateInput[1]);
        int yearStart = Integer.parseInt(dateInput[2]);
        //Time input
        System.out.println("Enter time(hh:mm):");
        String[] time = sc.nextLine().split(":");
        int hours = Integer.parseInt(time[0]);
        int minutes = Integer.parseInt(time[1]);
        LocalDateTime date = LocalDateTime.of(yearStart,monthStart,dayStart, hours, minutes);
        System.out.println("Do you want to add a note?(yes/no):");
        String answer2 = sc.nextLine().toLowerCase();
        String note = "";
        if(answer2.equals("yes")){
            note = noteEditor();
        }
        sc.close();
        Event event = new Event(title, location, date, note);
        StorageManager.addEvent(event);
    }

    public static ChronoLocalDateTime dateInput(){
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split("-");
        int day = Integer.parseInt(input[0]);
        int month = Integer.parseInt(input[1]);
        int year = Integer.parseInt(input[2]);
        ChronoLocalDateTime cld = LocalDateTime.of(year,month,day,0,0);
        return cld;
    }


    private static String noteEditor(){
        Scanner sc = new Scanner(System.in);
        String note = "";
        System.out.println("Enter \"DONE\" on singe row to continue.");
        System.out.println("Note:");
        System.out.println();
        while(true){
            String input = sc.nextLine();
            if(input.equals("DONE")){
                break;
            }
            note = note.concat(input);
        }
        return note;
    }
}
