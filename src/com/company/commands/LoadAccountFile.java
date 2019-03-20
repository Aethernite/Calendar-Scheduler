package com.company.commands;

import com.company.utils.StorageManager;
import com.company.pages.DefaultPage;

public class LoadAccountFile implements Command {
    private Command parent;


    public LoadAccountFile(Command parent){
        this.parent = parent;
    }

    @Override
    public Command execute(){
        StorageManager.loadAccountFile();
        return new DefaultPage(this);
    }
}
