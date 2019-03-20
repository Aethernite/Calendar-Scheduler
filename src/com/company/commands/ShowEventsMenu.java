package com.company.commands;

import com.company.utils.EventService;
import com.company.pages.DefaultPage;
import com.company.pages.ShowEventsMenuPage;
import com.company.utils.validators.DateValidator;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Scanner;

public class ShowEventsMenu implements Command {
    private Command parent;
    private static Scanner sc = new Scanner(System.in);

    public ShowEventsMenu(Command parent){
        this.parent = parent;
    }

    @Override
    public Command execute(){
        char choice = sc.nextLine().charAt(0);

        switch(choice){
            case '1':
                EventService.showAllEvents();
                return new ShowEventsMenuPage(this);
            case '2':
                EventService.showAllEventsForCurrentMonth();
                return new ShowEventsMenuPage(this);
            case '3':
                ChronoLocalDateTime date1;
                ChronoLocalDateTime date2;
                    System.out.println("Enter the first date:");
                    date1 = dateInput();
                    System.out.println("Enter the second date:");
                    date2 = dateInput();
                    EventService.showAllEventsBetweenTwoDates(date1,date2);
                return new ShowEventsMenuPage(this);
            case '4':
                return new DefaultPage(this);
            default:
                return this;
        }
    }

    private static ChronoLocalDateTime dateInput(){
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
}
