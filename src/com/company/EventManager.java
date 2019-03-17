package com.company;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import static com.company.AnsiColorCodes.*;

public class EventManager {

private static ArrayList<Event> list;
private static Scanner sc = new Scanner(System.in);

    public static void createEvent(){
        String note = "Note is empty;";
        String title;
        String location;
        String[] dateInput;
        int day;
        int month;
        int year;
        int hour;
        int minute;
        //Title input

        System.out.println("Enter event title:");
        title = sc.nextLine();

        //Location input
        System.out.println("Event location:");
        location = sc.nextLine();

        //Date input
        System.out.println("Enter date(dd-mm-year):");
        dateInput = sc.nextLine().split("-");
        day = Integer.parseInt(dateInput[0]);
        month = Integer.parseInt(dateInput[1]);
        year = Integer.parseInt(dateInput[2]);

        //Time input
        System.out.println("Enter time(hh:mm):");
        String[] time = sc.nextLine().split(":");
        hour = Integer.parseInt(time[0]);
        minute = Integer.parseInt(time[1]);
        LocalDateTime date = LocalDateTime.of(year,month,day, hour, minute);
        //Note input
        System.out.println("Do you want to add a note?(yes/no):");
        String answer2 = sc.nextLine().toLowerCase();
        if(answer2.equals("yes")){
            System.out.println("Enter note:");
            note = sc.nextLine();
        }
        Event event = new Event(title, location, date, note);
        StorageManager.addEvent(event);
    }

    public static ChronoLocalDateTime dateInput(){
        String date;
        do {
            date = sc.nextLine();
            if (DateValidator.isDateValid(date)) {
                String[] input = date.split("-");
                int day = Integer.parseInt(input[0]);
                int month = Integer.parseInt(input[1]);
                int year = Integer.parseInt(input[2]);
                ChronoLocalDateTime cld = LocalDateTime.of(year, month, day, 0, 0);
                return cld;
            }
            else{
                System.out.println("Invalid date!\nEnter again:");
            }
        }while(!DateValidator.isDateValid(date));
        return null;
    }

    public static void deleteEvent() {
        list = StorageManager.getListEvents();
        System.out.println("Events:");
        if (list.size() == 0) {
            System.out.println(ANSI_RED + "No events in the list." + ANSI_RESET);
        } else {
            int i = 1;
            for (Event ev : list) {
                System.out.println(i++ + ")" + ev.getTitle() + " - " + ev.getDate().getDayOfMonth() + " " + ev.getDate().getMonth() + " " + ev.getDate().getYear() + " " + ev.getDate().getHour() + ":" + ev.getDate().getMinute());
            }
            System.out.println("Choose an event to delete:");
            char choice = sc.nextLine().charAt(0);
            int index = choice - 49;
            Event event = list.get(index);
            System.out.println("Chosen:" + event.getTitle() + " - " + event.getDate().getDayOfMonth() + " " + event.getDate().getMonth() + " " + event.getDate().getYear() + " " + event.getDate().getHour() + ":" + event.getDate().getMinute());
            StorageManager.deleteEvent(index);
        }
    }
    public static void editEvent(){
       list = StorageManager.getListEvents();
       System.out.println("Events:");
       if(list.size() == 0){
           System.out.println(ANSI_RED + "No events in the list." + ANSI_RESET);
       }
       else{
           int i=1;
           for(Event ev: list){
               System.out.println(i++ + ")" + ev.getTitle() + " - " + ev.getDate().getDayOfMonth() + " " + ev.getDate().getMonth() + " " + ev.getDate().getYear() + " " + ev.getDate().getHour() + ":" + ev.getDate().getMinute());
           }
           System.out.println("Choose an event to edit:");
           char choice = sc.nextLine().charAt(0);
           int index = choice-49;
           Event event = list.get(index);
           System.out.println("Chosen:" + event.getTitle() + " - " + event.getDate().getDayOfMonth() + " " + event.getDate().getMonth() + " " + event.getDate().getYear() + " " + event.getDate().getHour() + ":" + event.getDate().getMinute());
           do{
               editMenu();
               choice = sc.nextLine().charAt(0);
               switch(choice){
                   case '1': {
                       System.out.println("Enter new title:");
                       String title = sc.nextLine();
                       event.setTitle(title);
                       StorageManager.editEvent(index, event);
                       break;
                   }
                   case '2': {
                       System.out.println("Enter new location:");
                       String location = sc.nextLine();
                       event.setLocation(location);
                       StorageManager.editEvent(index, event);
                       break;
                   }
                   case '3': {
                       System.out.println("Enter new date (dd-mm-year):");
                       String[] date = sc.nextLine().split("-");
                       LocalTime time = event.getDate().toLocalTime();
                       LocalDate newDate = LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
                       LocalDateTime edited = LocalDateTime.of(newDate, time);
                       event.setDate(edited);
                       StorageManager.editEvent(index, event);
                       break;
                   }
                   case '4': {
                       System.out.println("Enter new time(hh:mm):");
                       String[] time = sc.nextLine().split(":");
                       LocalDate date = event.getDate().toLocalDate();
                       LocalTime newTime = LocalTime.of(Integer.parseInt(time[0]), Integer.parseInt(time[1]));
                       LocalDateTime edited = LocalDateTime.of(date, newTime);
                       event.setDate(edited);
                       StorageManager.editEvent(index, event);
                       break;
                   }
                   case '5':{
                    System.out.println("Enter new note:");
                    String note = sc.nextLine();
                    event.setNote(note);
                    StorageManager.editEvent(index,event);
                       break;
                   }
                   case '6':
                       break;
                   default:
                       System.out.println(ANSI_RED + "INVALID INPUT" + ANSI_RESET);
                       break;
               }
           }while(choice!='6');
       }
    }
    private static void editMenu(){
        System.out.println(ANSI_YELLOW + "=========================" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "Edit menu:" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "1)"+ ANSI_RESET +"Change title");
        System.out.println(ANSI_CYAN + "2)"+ ANSI_RESET +"Change location");
        System.out.println(ANSI_CYAN + "3)"+ ANSI_RESET +"Change date");
        System.out.println(ANSI_CYAN + "4)"+ ANSI_RESET +"Change time");
        System.out.println(ANSI_CYAN + "5)"+ ANSI_RESET +"Change note");
        System.out.println(ANSI_CYAN + "6)"+ ANSI_RESET +"Back");
    }


}
