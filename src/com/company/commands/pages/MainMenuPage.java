package com.company.commands.pages;

import com.company.commands.NextMonth;
import com.company.commands.PreviousMonth;
import com.company.commands.CreateEvent;
import com.company.commands.DeleteEvent;
import com.company.commands.Exit;
import com.company.utils.storage.StorageManager;
import com.company.commands.Command;

import java.util.Scanner;

import static com.company.utils.frontend.AnsiColorCodes.*;
import static com.company.utils.frontend.AnsiColorCodes.ANSI_RESET;

public class MainMenuPage implements Command {
    private Command parent;
    private Scanner sc = new Scanner(System.in);
    public MainMenuPage(Command parent){
        this.parent = parent;
    }

    @Override
    public Command execute(){
        printMenu();
        return interactWithUser();
    }

    private Command interactWithUser() {
        char choice = sc.nextLine().charAt(0);
        switch(choice){
            case 'a':
                return new PreviousMonth(this);
            case 'd':
                return new NextMonth(this);
            case 'r':
                return new DefaultCalendarPrint(this);
            case '1':
                return new CreateEvent(this);
            case '2':
                return new ShowEventsMenuPage(this);
            case '3':
                return new EditEventMenuPage(this);
            case '4':
                return new DeleteEvent(this);
            case '5':
                return new Exit(this);
            default:
                System.out.println("Invalid input!");
                return this;
        }
    }

    private void printMenu() {
        System.out.println("USER:" + ANSI_CYAN + StorageManager.getUser() + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "__________________________" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "a)" + ANSI_RESET + "Print previous month");
        System.out.println(ANSI_CYAN + "d)" + ANSI_RESET + "Print next month");
        System.out.println(ANSI_CYAN + "r)" + ANSI_RESET + "Reset calendar (Current month)");
        System.out.println(ANSI_CYAN + "1)" + ANSI_RESET + "Create new event");
        System.out.println(ANSI_CYAN + "2)"+ ANSI_RESET + "Show events menu");
        System.out.println(ANSI_CYAN + "3)"+ ANSI_RESET + "Edit event");
        System.out.println(ANSI_CYAN + "4)" + ANSI_RESET + "Delete event");
        System.out.println(ANSI_CYAN + "5)" + ANSI_RESET +  "Exit");
        System.out.println("Choice:");
    }
}
