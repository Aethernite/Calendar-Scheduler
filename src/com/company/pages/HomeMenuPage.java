package com.company.pages;

import com.company.utils.StorageManager;
import com.company.commands.Command;
import com.company.commands.HomeMenu;

import static com.company.utils.AnsiColorCodes.*;
import static com.company.utils.AnsiColorCodes.ANSI_RESET;

public class HomeMenuPage implements Command {
    private Command parent;

    public HomeMenuPage(Command parent){
        this.parent = parent;
    }

    @Override
    public Command execute(){
        StorageManager.loadAccDataIntoMemory();
        System.out.println(ANSI_YELLOW + "=========================" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "Options:" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "1)" + ANSI_RESET + "Login");
        System.out.println(ANSI_CYAN + "2)" + ANSI_RESET + "Register");
        System.out.println(ANSI_CYAN + "3)" + ANSI_RESET + "Exit");
        System.out.print(ANSI_YELLOW + "Enter choice:" + ANSI_RESET);

        return new HomeMenu(this);
    }
}
