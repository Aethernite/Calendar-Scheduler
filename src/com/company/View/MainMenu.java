package com.company.View;

import java.util.Scanner;

public class MainMenu implements Command{
    private Command parent;
    private static Scanner sc = new Scanner(System.in);

    public MainMenu(Command parent){
        this.parent = parent;
    }

    @Override
    public Command execute(){
        char choice = sc.nextLine().charAt(0);
        switch(choice){
            case '1':
                return new CreateEvent(this);
            case '2':
                return new ShowEventsMenuPage(this);
            case '3':
                return new EditEventMenu(this);
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
