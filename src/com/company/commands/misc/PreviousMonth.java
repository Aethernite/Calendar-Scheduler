package com.company.commands.misc;

import com.company.commands.Command;
import com.company.commands.pages.MainMenuPage;
import com.company.utils.calendar.CalendarService;

public class PreviousMonth implements Command {
    private Command parent;
    public PreviousMonth(Command parent){
        this.parent = parent;
    }

    @Override
    public Command execute(){
        CalendarService.printPreviousMonth();
        return new MainMenuPage(this);
    }
}
