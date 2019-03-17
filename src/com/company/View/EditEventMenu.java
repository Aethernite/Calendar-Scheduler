package com.company.View;

import com.company.*;
import com.company.View.Command;
import com.company.View.DefaultPage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

import static com.company.AnsiColorCodes.*;
import static com.company.AnsiColorCodes.ANSI_RESET;

public class EditEventMenu implements Command {
    private Command parent;
    private static Scanner sc = new Scanner(System.in);

    public EditEventMenu(Command parent) {
        this.parent = parent;
    }

    @Override
    public Command execute() {
        char choice;
        if (EventService.showAllEvents()) {
            System.out.println("Choose an event to edit(0 to go back):");
            choice = sc.nextLine().charAt(0);
            if(choice == '0'){
                return new DefaultPage(this);
            }
            int index = choice - 49;
            Event event = StorageManager.getListEvents().get(index);
            System.out.println(ANSI_YELLOW + "=========================" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "Edit menu:" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "1)" + ANSI_RESET + "Change title");
            System.out.println(ANSI_CYAN + "2)" + ANSI_RESET + "Change location");
            System.out.println(ANSI_CYAN + "3)" + ANSI_RESET + "Change date");
            System.out.println(ANSI_CYAN + "4)" + ANSI_RESET + "Change time");
            System.out.println(ANSI_CYAN + "5)" + ANSI_RESET + "Change note");
            System.out.println(ANSI_CYAN + "6)" + ANSI_RESET + "Back");
            System.out.println("Choice:");
            choice = sc.nextLine().charAt(0);
            switch (choice) {
                case '1': {
                    System.out.println("Enter new title:");
                    String title = sc.nextLine();
                    event.setTitle(title);
                    StorageManager.editEvent(index, event);
                    return this;
                }
                case '2': {
                    System.out.println("Enter new location:");
                    String location = sc.nextLine();
                    event.setLocation(location);
                    StorageManager.editEvent(index, event);
                    return this;
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
                    return this;
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
                    return this;
                case '5':
                    System.out.println("Enter new note:");
                    String note = sc.nextLine();
                    event.setNote(note);
                    StorageManager.editEvent(index, event);
                    return this;
                case '6':
                    return new DefaultPage(this);


            }
        }
        return new DefaultPage(this);
    }
}

