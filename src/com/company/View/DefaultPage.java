package com.company.View;

import com.company.CalendarService;
import com.company.StorageManager;

public class DefaultPage implements Command{
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
