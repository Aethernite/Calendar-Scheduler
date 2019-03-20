package com.company.commands.misc;

import com.company.commands.Command;
import com.company.commands.pages.HomeMenuPage;
import com.company.utils.storage.StorageManager;

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
