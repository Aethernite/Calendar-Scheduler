package com.company.commands.views;

import com.company.commands.Command;
import com.company.commands.misc.NextMonth;
import com.company.commands.misc.PreviousMonth;
import com.company.commands.pages.DefaultPage;
import com.company.commands.pages.ShowEventsMenuPage;

import java.util.Scanner;

public class MainMenuView implements Command {
    private Command parent;
    private static Scanner sc = new Scanner(System.in);

    public MainMenuView(Command parent){
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
                return new CreateEventView(this);
            case '2':
                return new ShowEventsMenuPage(this);
            case '3':
                return new EditEventView(this);
            case '4':
                return new DeleteEventView(this);
            case '5':
                return new Exit(this);
            default:
                System.out.println("Invalid input!");
                return this;
        }
    }
}
