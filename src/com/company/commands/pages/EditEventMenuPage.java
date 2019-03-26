package com.company.commands.pages;

import com.company.commands.Command;
import com.company.objects.Event;
import com.company.utils.event.EventService;
import com.company.utils.storage.StorageManager;
import com.company.utils.validators.DateValidator;
import com.company.utils.validators.TimeValidator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

import static com.company.utils.frontend.AnsiColorCodes.*;

public class EditEventMenuPage implements Command {
    private Command parent;
    private Event event;
    private int index;
    private Scanner sc = new Scanner(System.in);

    public EditEventMenuPage(Command parent){
        this.parent = parent;
    }

    @Override
    public Command execute(){
        if (interactWithUser()){
            return new DefaultCalendarPrint(this);
        }
        return editMenu();
    }


    private Command editMenu(){
        while(true) {
            menu();
            char choice = sc.nextLine().charAt(0);
            switch (choice) {
                case '1':
                    changeTitle();
                    return this;
                case '2':
                    changeLocation();
                    return this;
                case '3':
                    changeDate();
                    return this;
                case '4':
                    changeTime();
                    return this;
                case '5':
                    changeNote();
                    return this;
                case '6':
                    return new DefaultCalendarPrint(this);
                default:
                    System.out.println(ANSI_RED + "Invalid option!" + ANSI_RESET);
            }
        }
    }

    private boolean interactWithUser() {
        if (EventService.showAllEvents()){
            if (!chooseEvent()) {
                return true;
            }
        }
        return false;
    }

    private boolean chooseEvent() {
        int index;
        int size = StorageManager.getSizeEventsList();
        char choice;
        do {
            System.out.println("Choose an event to delete(0 to go back):");
            choice = sc.nextLine().charAt(0);
            if (choice == '0') {
                return false;
            }
            index = choice - 49;
            if (index < 0 || index > size - 1) {
                System.out.println(ANSI_RED + "Invalid index!" + ANSI_RESET);
            }
        } while (index < 0 || index > size - 1);
        this.event = StorageManager.getListEvents().get(index);
        this.index = index;
        return true;
    }

    private void changeNote() {
        System.out.println("Enter new note:");
        String note = sc.nextLine();
        event.setNote(note);
        StorageManager.editEvent(index, event);
    }

    private void changeTime() {
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
    }

    private void changeDate() {
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
    }

    private void changeLocation() {
        System.out.println("Enter new location:");
        String location = sc.nextLine();
        event.setLocation(location);
        StorageManager.editEvent(index, event);
    }

    private void changeTitle() {
        System.out.println("Enter new title:");
        String title = sc.nextLine();
        event.setTitle(title);
        StorageManager.editEvent(index, event);
    }

    private void menu() {
        System.out.println(ANSI_YELLOW + "=========================" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "Edit menu:" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "1)" + ANSI_RESET + "Change title");
        System.out.println(ANSI_CYAN + "2)" + ANSI_RESET + "Change location");
        System.out.println(ANSI_CYAN + "3)" + ANSI_RESET + "Change date");
        System.out.println(ANSI_CYAN + "4)" + ANSI_RESET + "Change time");
        System.out.println(ANSI_CYAN + "5)" + ANSI_RESET + "Change note");
        System.out.println(ANSI_CYAN + "6)" + ANSI_RESET + "Back");
        System.out.println("Choice:");
    }
}
