package com.company.commands.views;

import com.company.commands.Command;
import com.company.utils.storage.StorageManager;
import com.company.utils.validators.AccountValidator;
import com.company.objects.Account;
import com.company.commands.pages.HomeMenuPage;

import java.util.Scanner;

import static com.company.utils.frontend.AnsiColorCodes.*;

public class RegisterView implements Command {
    private Command parent;
    private static Scanner sc = new Scanner(System.in);

    public RegisterView(Command parent){
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
        Account user = new Account(username.toLowerCase(), password);
        AccountValidator v = new AccountValidator();
        if (StorageManager.exists(user)) {
            System.out.println(ANSI_RED + "USER ALREADY EXISTS" + ANSI_RESET);
            return this;
        }
        else if (!(v.checkAccount(user))) {
            System.out.println(ANSI_RED + "Password must be:\n- at least 1 number\n- at least one lowercase letter\n- at least one uppercase letter!\n- minimum 8 characters long"+ ANSI_RESET);
            return this;
        }
        else {
            StorageManager.register(user);
            System.out.println(ANSI_GREEN + "Registration successful!" + ANSI_RESET);
            return new HomeMenuPage(this);
        }
    }
}
