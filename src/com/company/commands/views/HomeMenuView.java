package com.company.commands.views;

import com.company.commands.Command;

import java.util.Scanner;


public class HomeMenuView implements Command {
    private Command parent;
    private static Scanner sc = new Scanner(System.in);

    public HomeMenuView(Command parent) {
        this.parent = parent;
    }

    @Override
    public Command execute(){
        char choice = sc.nextLine().charAt(0);
        switch(choice){
            case '1':
                return new LoginView(this);
            case '2':
                return new RegisterView(this);
            case '3':
                return new Exit(this);
            default:
                System.out.println("Invalid choice!");
                return new HomeMenuView(this);
        }
    }
}
