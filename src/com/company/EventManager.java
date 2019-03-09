package com.company;

import java.time.LocalDate;
import java.util.Scanner;

public class EventManager {



    public static void createEvent(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Event title:");
        String title = sc.nextLine();
        System.out.println("Event location:");
        String location = sc.nextLine();
        System.out.println("Enter first date(dd-mm-year):");
        String[] dateStart = sc.nextLine().split("-");
        int dayStart = Integer.parseInt(dateStart[0]);
        int monthStart = Integer.parseInt(dateStart[1]);
        int yearStart = Integer.parseInt(dateStart[2]);
        LocalDate startDate = LocalDate.of(yearStart,monthStart,dayStart);
        System.out.println("Is the event longer than one day?:");
        String answer = sc.nextLine().toLowerCase();
        boolean longerThanOneDay = false;
        LocalDate endDate = null;
        if(answer.equals("yes")){
            longerThanOneDay = true;
            System.out.println("Enter second date(dd-mm-year):");
            String[] dateEnd = sc.nextLine().split("-");
            int dayEnd = Integer.parseInt(dateEnd[0]);
            int monthEnd = Integer.parseInt(dateEnd[1]);
            int yearEnd = Integer.parseInt(dateEnd[2]);
            endDate = LocalDate.of(yearEnd,monthEnd,dayEnd);
        }
        System.out.println("Do you want to add a note?:");
        String answer2 = sc.nextLine().toLowerCase();
        String note = "";
        if(answer2.equals("yes")){
            note = noteEditor();
        }

        Event event = new Event(title, location, startDate, endDate, longerThanOneDay, note);
        StorageManager.addEvent(event);
    }




    private static String noteEditor(){
        Scanner sc = new Scanner(System.in);
        String note = "";
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
