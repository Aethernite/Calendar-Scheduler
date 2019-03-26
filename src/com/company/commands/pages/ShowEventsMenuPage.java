package com.company.commands.pages;

import com.company.commands.Command;
import com.company.utils.event.EventService;
import com.company.utils.validators.DateValidator;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Scanner;

import static com.company.utils.frontend.AnsiColorCodes.*;
import static com.company.utils.frontend.AnsiColorCodes.ANSI_RESET;

public class ShowEventsMenuPage implements Command {
    private Command parent;
    private Scanner sc = new Scanner(System.in);
    public ShowEventsMenuPage(Command parent){
        this.parent = parent;
    }

    @Override
    public Command execute(){
        printMenu();
        return interactWithUser();
    }

    private Command interactWithUser() {
        char choice = sc.nextLine().charAt(0);
        switch (choice) {
            case '1':
                EventService.showAllEvents();
                return new ShowEventsMenuPage(this);
            case '2':
                EventService.showAllEventsForCurrentMonth();
                return new ShowEventsMenuPage(this);
            case '3':
                showAllEventsBetweenTwoDates();
                return new ShowEventsMenuPage(this);
            case '4':
                return new DefaultCalendarPrint(this);
            default:
                return this;
        }
    }

    private void showAllEventsBetweenTwoDates() {
        ChronoLocalDateTime date1;
        ChronoLocalDateTime date2;
        System.out.println("Enter the first date:");
        date1 = dateInput();
        System.out.println("Enter the second date:");
        date2 = dateInput();
        EventService.showAllEventsBetweenTwoDates(date1, date2);
    }

    private void printMenu() {
        System.out.println(ANSI_YELLOW +"=========================" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "1)" + ANSI_RESET + "Show all events");
        System.out.println(ANSI_CYAN + "2)" + ANSI_RESET + "Show events for the current month");
        System.out.println(ANSI_CYAN + "3)" + ANSI_RESET + "Show events between two dates");
        System.out.println(ANSI_CYAN + "4)" + ANSI_RESET + "Back");
        System.out.println("Choice:");
    }

    private ChronoLocalDateTime dateInput() {
        String date;
        while(true){
            date = sc.nextLine();
            if (DateValidator.isDateValid(date)) {
                String[] input = date.split("-");
                int day = Integer.parseInt(input[0]);
                int month = Integer.parseInt(input[1]);
                int year = Integer.parseInt(input[2]);
                return LocalDateTime.of(year, month, day, 0, 0);
            }
        }
    }
}
