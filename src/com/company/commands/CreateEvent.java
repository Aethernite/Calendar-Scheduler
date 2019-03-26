package com.company.commands;


import com.company.utils.validators.DateValidator;
import com.company.objects.Event;
import com.company.utils.storage.StorageManager;
import com.company.utils.validators.TimeValidator;
import com.company.commands.pages.DefaultCalendarPrint;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class CreateEvent implements Command {
    private Command parent;
    private static Scanner sc = new Scanner(System.in);

    public CreateEvent(Command parent) {
        this.parent = parent;
    }

    @Override
    public Command execute() {

        String title = getTitle();
        String location = getLocation();
        LocalDate date = getDate();
        LocalTime time = getTime();
        LocalDateTime dateTime = LocalDateTime.of(date,time);
        String note = getNote();
        Event event = new Event(title, location, dateTime, note);
        StorageManager.addEvent(event);

        return new DefaultCalendarPrint(this);
    }

    private String getNote() {
        System.out.println("Do you want to add a note?(yes/no):");
        String answer = sc.nextLine().toLowerCase();
        String note = "Note is empty.";
        if (answer.equals("yes")) {
            System.out.println("Enter note:");
            note = sc.nextLine();
        }
        return note;
    }

    private LocalTime getTime(){
        String[] time;
        do {
            System.out.println("Enter time(hh:mm):");
            time = sc.nextLine().split(":");
        } while (!TimeValidator.isTimeValid(time));
        int hour = Integer.parseInt(time[0]);
        int minute = Integer.parseInt(time[1]);
        return LocalTime.of(hour,minute,0);
    }

    private LocalDate getDate() {
        String date;
        do {
            System.out.println("Enter date(dd-mm-year):");
            date = sc.nextLine();
        } while (!DateValidator.isDateValid(date));
        String[] dateInput = date.split("-");
        int day = Integer.parseInt(dateInput[0]);
        int month = Integer.parseInt(dateInput[1]);
        int year = Integer.parseInt(dateInput[2]);
        return LocalDate.of(year,month,day);
    }

    private String getLocation() {
        System.out.println("Event location:");
        return sc.nextLine();
    }

    private String getTitle() {
        System.out.println("Enter event title:");
        return sc.nextLine();
    }
}
