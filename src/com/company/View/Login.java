package com.company.View;

import com.company.Account;
import com.company.StorageManager;

import java.util.Scanner;

import static com.company.AnsiColorCodes.*;

public class Login implements Command{
    private Command parent;
    private static Scanner sc = new Scanner(System.in);

    public Login(Command parent){
    this.parent = parent;
    }

    @Override
    public Command execute(){
        System.out.println(ANSI_YELLOW +"=========================" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "Login form:" + ANSI_RESET);
        System.out.print("Enter username:");
        String username = sc.nextLine();
        System.out.print("Enter password:");
        String password = sc.nextLine();
        Account user = new Account(username, password);
        if (StorageManager.checkLogin(user)){
            StorageManager.setUser(user);
            System.out.println(ANSI_GREEN + "LOGIN SUCCESSFUL!" + ANSI_RESET);
            return new LoadAccountFile(this);
        }
        return this;

    }
}
