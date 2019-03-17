package com.company.View;

public class CommandManager {
    public static void main(String[] args) {
        CommandManager main = new CommandManager();
        main.start();
    }
        public void start() {
            Command nextCommand = new HomePage(null);

            while(nextCommand != null) {
                nextCommand = nextCommand.execute();
            }
        }
}
