package com.company;

import java.util.Scanner;

public class Main {
    private static boolean loggedIn = false;
    public static void main(String[] args) {
        Validator v = new Validator();
        StorageManager.loadAccDataIntoMemory();
        do {
            clearScreen();
            menu();
            System.out.flush();
            char choice = choice();
            switch (choice) {
                case '1': {
                    Account user = menuLoginRegister(true);
                    if (StorageManager.checkLogin(user)) {
                        StorageManager.setUser(user);
                        System.out.println("LOGIN SUCCESSFUL!");
                        loggedIn = true;
                        break;
                    } else {
                        System.out.println("LOGIN FAILED!");
                        break;
                    }
                }
                case '2': {
                        clearScreen();
                            Account user = menuLoginRegister(false);
                        if (StorageManager.exists(user)) {
                            System.out.println("USER ALREADY EXISTS");
                            break;
                        } else if (!(v.checkAccount(user))) {
                            System.out.println("INVALID USERNAME/PASSWORD TRY AGAIN!");
                            break;
                        } else {
                            StorageManager.register(user);
                            System.out.println("Registration successful!");
                            break;
                        }
                }
                default:
                    System.out.println("Invalid choice");
                    return;
            }
        }while(!loggedIn);
        StorageManager.loadAccountFile();
        CalendarService calService = new CalendarService();
        char choice;
        do {
            calService.defaultPrint();
            menuLoggedIn();
            choice = choice();
            switch(choice){
                case 'a':
                    clearScreen();
                    calService.printPreviousMonth();
                    menuLoggedIn();
                    break;
                case 'd':
                    clearScreen();
                    calService.printNextMonth();
                    menuLoggedIn();
                    break;
                case '1':
                    EventManager.createEvent();
                    break;
                case '2':
                    EventService.showAllEvents();
                    break;
                case '3':
                    break;
                case '4':
                    break;
                case '5':
                    break;
                default:
                    break;
            }
        }while(choice != '6');







    }

    private static void menuLoggedIn(){
        System.out.println("USER:" + StorageManager.getUser());
        System.out.println("__________________________");
        System.out.println("a <- previous month ----- next month -> d");
        System.out.println("1) Create new event");
        System.out.println("2) Show all events");
        System.out.println("3) Edit event");
        System.out.println("4) Delete event");
        System.out.println("5) Exit");
        System.out.println("Choice:");
    }

    static char choice(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().charAt(0);
    }


    private static void menu(){
        System.out.println("=========================");
        System.out.println("Options:");
        System.out.println("1)Login");
        System.out.println("2)Register");
        System.out.print("Enter choice:");
    }

    private static Account menuLoginRegister(boolean login){
        Scanner scanner = new Scanner(System.in);
        System.out.println("=========================");
        if(login) { System.out.println("Login form:");}
        else{ System.out.println("Register form:");}
        System.out.println("=========================");
        System.out.print("Enter username:");
        String username = scanner.next();
        System.out.print("Enter password:");
        String password = scanner.next();

        return new Account(username, password);
    }


    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
