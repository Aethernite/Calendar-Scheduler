package com.company.commands.pages;

import com.company.utils.calendar.CalendarService;
import com.company.commands.Command;

public class DefaultCalendarPrint implements Command {
    private Command parent;

    public DefaultCalendarPrint(Command parent){
        this.parent = parent;
    }

    @Override
    public Command execute(){
        CalendarService.defaultPrint();
        return new MainMenuPage(this);
    }
}
