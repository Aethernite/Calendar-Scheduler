package com.company.View;

import java.util.Scanner;


public class HomeMenu implements Command{
    private Command parent;
    private static Scanner sc = new Scanner(System.in);

    public HomeMenu(Command parent) {
        this.parent = parent;
    }

    @Override
    public Command execute(){
        char choice = sc.nextLine().charAt(0);
        switch(choice){
            case '1':
                return new Login(this);
            case '2':
                return new Register(this);
            case '3':
                return new Exit(this);
            default:
                System.out.println("Invalid choice!");
                return new HomeMenu(this);
        }
    }
}
