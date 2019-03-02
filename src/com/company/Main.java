package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Validator v = new Validator();
        System.out.println("Options:");
        System.out.println("1)Login");
        System.out.println("2)Register");
        System.out.print("Enter choice:");
        char choice = scanner.nextLine().charAt(0);
        switch(choice){
            case '1': {
                System.out.print("Enter username:");
                String username = scanner.next();
                System.out.print("Enter password:");
                String password = scanner.next();
                Account user = new Account(username, password);
                if (AccountLibrary.checkLogin(user)) {
                    System.out.println("LOGIN SUCCESSFUL!");
                    return;
                } else {
                    System.out.println("LOGIN FAILED!");
                    return;
                }
            }
            case '2': {
                System.out.println("Registration form:");
                System.out.println("Enter username:");
                String username = scanner.next();
                System.out.print("Enter password:");
                String password = scanner.next();
                Account user = new Account(username, password);
                if(AccountLibrary.exists(user)){
                    System.out.println("USER ALREADY EXISTS");
                    return;
                }
                else if(!(v.checkAccount(user))){
                    System.out.println("WRONG USERNAME/PASSWORD TRY AGAIN!");
                    return;
                }
                else{
                    AccountLibrary.register(user);
                    return;
                }
            }
            default:
                System.out.println("Invalid choice");
                return;
        }
    }
}
