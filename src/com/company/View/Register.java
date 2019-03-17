package com.company.View;

import com.company.Account;
import com.company.StorageManager;
import com.company.Validator;

import java.util.Scanner;

import static com.company.AnsiColorCodes.*;

public class Register implements Command{
    private Command parent;
    private static Scanner sc = new Scanner(System.in);

    public Register(Command parent){
        this.parent = parent;
    }
    @Override
    public Command execute(){
        System.out.println(ANSI_YELLOW +"=========================" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "Register form:" + ANSI_RESET);
        System.out.print("Enter username:");
        String username = sc.nextLine();
        System.out.print("Enter password:");
        String password = sc.nextLine();
        Account user = new Account(username, password);
        Validator v = new Validator();
        if (StorageManager.exists(user)) {
            System.out.println(ANSI_RED + "USER ALREADY EXISTS" + ANSI_RESET);
            return this;
        }
        else if (!(v.checkAccount(user))) {
            System.out.println(ANSI_RED + "INVALID USERNAME/PASSWORD TRY AGAIN!"+ ANSI_RESET);
            return this;
        }
        else {
            StorageManager.register(user);
            System.out.println(ANSI_GREEN + "Registration successful!" + ANSI_RESET);
            return new HomePage(this);
        }
    }
}
