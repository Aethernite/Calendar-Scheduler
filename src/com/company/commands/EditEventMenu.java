package com.company.commands;

import com.company.objects.Event;
import com.company.pages.DefaultPage;
import com.company.utils.validators.DateValidator;
import com.company.utils.StorageManager;
import com.company.utils.validators.TimeValidator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

import static com.company.utils.AnsiColorCodes.ANSI_RED;
import static com.company.utils.AnsiColorCodes.ANSI_RESET;

public class EditEventMenu implements Command {
    private Command parent;
    private Event event;
    private int index;
    private Scanner sc = new Scanner(System.in);

    public EditEventMenu(Command parent, Event event, int index){
        this.parent = parent;
        this.event = event;
        this.index = index;
    }

    @Override
    public Command execute(){
        while(true) {
            char choice = sc.nextLine().charAt(0);
            switch (choice) {
                case '1': {
                    System.out.println("Enter new title:");
                    String title = sc.nextLine();
                    event.setTitle(title);
                    StorageManager.editEvent(index, event);
                    return new EditEvent(this);
                }
                case '2': {
                    System.out.println("Enter new location:");
                    String location = sc.nextLine();
                    event.setLocation(location);
                    StorageManager.editEvent(index, event);
                    return new EditEvent(this);
                }
                case '3': {
                    String date;
                    do {
                        System.out.println("Enter date(dd-mm-year):");
                        date = sc.nextLine();
                    } while (!DateValidator.isDateValid(date));

                    String[] dateInput = date.split("-");
                    int day = Integer.parseInt(dateInput[0]);
                    int month = Integer.parseInt(dateInput[1]);
                    int year = Integer.parseInt(dateInput[2]);
                    LocalTime time = event.getDate().toLocalTime();
                    LocalDate newDate = LocalDate.of(Integer.parseInt(dateInput[2]), Integer.parseInt(dateInput[1]), Integer.parseInt(dateInput[0]));
                    LocalDateTime edited = LocalDateTime.of(newDate, time);
                    event.setDate(edited);
                    StorageManager.editEvent(index, event);
                    return new EditEvent(this);
                }
                case '4':
                    String[] time;
                    do {
                        System.out.println("Enter time(hh:mm):");
                        time = sc.nextLine().split("-");
                    } while (TimeValidator.isTimeValid(time));
                    int hour = Integer.parseInt(time[0]);
                    int minute = Integer.parseInt(time[1]);
                    LocalDate date = event.getDate().toLocalDate();
                    LocalTime newTime = LocalTime.of(Integer.parseInt(time[0]), Integer.parseInt(time[1]));
                    LocalDateTime edited = LocalDateTime.of(date, newTime);
                    event.setDate(edited);
                    StorageManager.editEvent(index, event);
                    return new EditEvent(this);
                case '5':
                    System.out.println("Enter new note:");
                    String note = sc.nextLine();
                    event.setNote(note);
                    StorageManager.editEvent(index, event);
                    return new EditEvent(this);
                case '6':
                    return new DefaultPage(this);
                default:
                    System.out.println(ANSI_RED + "Invalid option!" + ANSI_RESET);
            }
        }
    }
}
