package com.company.commands;

import com.company.pages.MainMenuPage;
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
