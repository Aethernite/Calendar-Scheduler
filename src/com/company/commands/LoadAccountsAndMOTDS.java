package com.company.commands;

import com.company.commands.pages.HomeMenuPage;
import com.company.utils.storage.StorageManager;

public class LoadAccountsAndMOTDS implements Command {
    private Command parent;

    public LoadAccountsAndMOTDS(Command parent){
        this.parent = parent;
    }

    @Override
    public Command execute(){
        StorageManager.loadAccDataIntoMemory();
        StorageManager.loadMOTDFile();
        return new HomeMenuPage(this);
    }
}
