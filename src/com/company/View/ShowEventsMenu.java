package com.company.View;

import com.company.EventManager;
import com.company.EventService;

import java.time.chrono.ChronoLocalDateTime;
import java.util.Scanner;

public class ShowEventsMenu implements Command{
    private Command parent;

    public ShowEventsMenu(Command parent){
        this.parent = parent;
    }

    @Override
    public Command execute(){
        Scanner sc = new Scanner(System.in);
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
                    date1 = EventManager.dateInput();
                    System.out.println("Enter the second date:");
                    date2 = EventManager.dateInput();
                    EventService.showAllEventsBetweenTwoDates(date1,date2);
                return new ShowEventsMenuPage(this);
            case '4':
                return new DefaultPage(this);
            default:
                return this;
        }
    }
}
