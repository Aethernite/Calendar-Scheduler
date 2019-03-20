package com.company.commands.misc;

import com.company.commands.Command;
import com.company.utils.storage.StorageManager;
import com.company.commands.pages.DefaultPage;

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
