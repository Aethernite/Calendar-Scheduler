package com.company.commands;

import com.company.pages.HomeMenuPage;
import com.company.utils.StorageManager;

public class LoadAccounts implements Command {
    private Command parent;

    public LoadAccounts(Command parent){
        this.parent = parent;
    }

    @Override
    public Command execute(){
        StorageManager.loadAccDataIntoMemory();
        return new HomeMenuPage(this);
    }
}
