package com.company.View;

import com.company.StorageManager;

import static com.company.AnsiColorCodes.*;
import static com.company.AnsiColorCodes.ANSI_RESET;

public class MainMenuPage implements Command{
    private Command parent;

    public MainMenuPage(Command parent){
        this.parent = parent;
    }

    @Override
    public Command execute(){
        System.out.println("USER:" + ANSI_CYAN + StorageManager.getUser() + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "__________________________" + ANSI_RESET);
        System.out.println("a <- previous month ----- next month -> d");
        System.out.println(ANSI_CYAN + "1)" + ANSI_RESET + "Create new event");
        System.out.println(ANSI_CYAN + "2)"+ ANSI_RESET + "Show events menu");
        System.out.println(ANSI_CYAN + "3)"+ ANSI_RESET + "Edit event");
        System.out.println(ANSI_CYAN + "4)" + ANSI_RESET + "Delete event");
        System.out.println(ANSI_CYAN + "5)" + ANSI_RESET +  "Exit");
        System.out.println("Choice:");
        return new MainMenu(this);
    }
}
