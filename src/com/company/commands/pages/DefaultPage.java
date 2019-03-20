package com.company.commands.pages;

import com.company.utils.calendar.CalendarService;
import com.company.commands.Command;

public class DefaultPage implements Command {
    private Command parent;

    public DefaultPage(Command parent){
        this.parent = parent;
    }

    @Override
    public Command execute(){
        CalendarService calService = new CalendarService();
        calService.defaultPrint();

        return new MainMenuPage(this);
    }
}
