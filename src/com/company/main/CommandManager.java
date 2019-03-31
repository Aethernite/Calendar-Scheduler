package com.company.main;

import com.company.commands.Command;
import com.company.commands.LoadAccountsAndMOTDS;

public class CommandManager {
    public static void main(String[] args) {
        CommandManager main = new CommandManager();
        main.start();
    }
        public void start() {
            Command nextCommand = new LoadAccountsAndMOTDS(null);

            while(nextCommand != null) {
                nextCommand = nextCommand.execute();
            }
        }
}
