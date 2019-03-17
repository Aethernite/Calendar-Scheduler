package com.company.View;

import com.company.StorageManager;

public class LoadAccountFile implements Command{
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
