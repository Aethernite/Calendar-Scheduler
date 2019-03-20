package com.company.commands.pages;

import com.company.commands.Command;
import com.company.commands.views.ShowEventsMenuView;

import static com.company.utils.frontend.AnsiColorCodes.*;
import static com.company.utils.frontend.AnsiColorCodes.ANSI_RESET;

public class ShowEventsMenuPage implements Command {
    private Command parent;

    public ShowEventsMenuPage(Command parent){
        this.parent = parent;
    }

    @Override
    public Command execute(){
        System.out.println(ANSI_YELLOW +"=========================" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "1)" + ANSI_RESET + "Show all events");
        System.out.println(ANSI_CYAN + "2)" + ANSI_RESET + "Show events for the current month");
        System.out.println(ANSI_CYAN + "3)" + ANSI_RESET + "Show events between two dates");
        System.out.println(ANSI_CYAN + "4)" + ANSI_RESET + " Back");
        System.out.println("Choice:");
        return new ShowEventsMenuView(this);
    }
}
