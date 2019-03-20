package com.company.commands;

import com.company.pages.DefaultPage;
import com.company.pages.ShowEventsMenuPage;

import java.util.Scanner;

public class MainMenu implements Command {
    private Command parent;
    private static Scanner sc = new Scanner(System.in);

    public MainMenu(Command parent){
        this.parent = parent;
    }

    @Override
    public Command execute(){
        char choice = sc.nextLine().charAt(0);
        switch(choice){
            case 'a':
                return new PreviousMonth(this);
            case 'd':
                return new NextMonth(this);
            case 'r':
                return new DefaultPage(this);
            case '1':
                return new CreateEvent(this);
            case '2':
                return new ShowEventsMenuPage(this);
            case '3':
                return new EditEvent(this);
            case '4':
                return new DeleteEvent(this);
            case '5':
                return new Exit(this);
            default:
                System.out.println("Invalid input!");
                return this;
        }
    }
}
