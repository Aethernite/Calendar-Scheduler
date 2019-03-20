package com.company.commands;

import com.company.utils.validators.DateValidator;
import com.company.objects.Event;
import com.company.utils.StorageManager;
import com.company.utils.validators.TimeValidator;
import com.company.pages.DefaultPage;

import java.time.LocalDateTime;
import java.util.Scanner;

public class CreateEvent implements Command {
    private Command parent;
    private static Scanner sc = new Scanner(System.in);
    public CreateEvent(Command parent){
        this.parent = parent;
    }

    @Override
    public Command execute(){

        System.out.println("Enter event title:");
        String title = sc.nextLine();

        //Location input
        System.out.println("Event location:");
        String location = sc.nextLine();

        //Date input
        String date;
        do{
        System.out.println("Enter date(dd-mm-year):");
            date = sc.nextLine();
        } while(!DateValidator.isDateValid(date));

        String[] dateInput = date.split("-");
        int day = Integer.parseInt(dateInput[0]);
        int month = Integer.parseInt(dateInput[1]);
        int year = Integer.parseInt(dateInput[2]);

        //Time input
        String[] time;
        do {
            System.out.println("Enter time(hh:mm):");
            time = sc.nextLine().split(":");
        }while(!TimeValidator.isTimeValid(time));
        int hour = Integer.parseInt(time[0]);
        int minute = Integer.parseInt(time[1]);

        LocalDateTime dateTime = LocalDateTime.of(year,month,day, hour, minute);

        //Note input
        System.out.println("Do you want to add a note?(yes/no):");
        String answer = sc.nextLine().toLowerCase();
        String note = "Note is empty.";
        if(answer.equals("yes")){
            System.out.println("Enter note:");
            note = sc.nextLine();
        }
        Event event = new Event(title, location, dateTime, note);
        StorageManager.addEvent(event);

        return new DefaultPage(this);
    }
}
