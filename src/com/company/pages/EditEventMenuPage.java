package com.company.pages;

import com.company.commands.EditEventMenu;
import com.company.commands.Command;
import com.company.objects.Event;

import static com.company.utils.AnsiColorCodes.*;
import static com.company.utils.AnsiColorCodes.ANSI_RESET;

public class EditEventMenuPage implements Command {
    private Command parent;
    private Event event;
    private int index;

    public EditEventMenuPage(Command parent, Event event, int index){
        this.parent = parent;
        this.event = event;
        this.index = index;
    }

    @Override
    public Command execute(){
        System.out.println(ANSI_YELLOW + "=========================" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "Edit menu:" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "1)" + ANSI_RESET + "Change title");
        System.out.println(ANSI_CYAN + "2)" + ANSI_RESET + "Change location");
        System.out.println(ANSI_CYAN + "3)" + ANSI_RESET + "Change date");
        System.out.println(ANSI_CYAN + "4)" + ANSI_RESET + "Change time");
        System.out.println(ANSI_CYAN + "5)" + ANSI_RESET + "Change note");
        System.out.println(ANSI_CYAN + "6)" + ANSI_RESET + "Back");
        System.out.println("Choice:");

        return new EditEventMenu(this,event,index);
    }
}
