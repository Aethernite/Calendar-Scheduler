package com.company;

import java.io.IOException;
import java.io.InputStreamReader;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Scanner;

import static com.company.AnsiColorCodes.*;

public class Main {
    private static boolean loggedIn = false;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        Validator v = new Validator();
        StorageManager.loadAccDataIntoMemory();
        do {
            menu();
            char choice = sc.nextLine().charAt(0);
            switch (choice) {
                case '1': {
                    Account user = menuLoginRegister(true);
                    if (StorageManager.checkLogin(user)) {
                        StorageManager.setUser(user);
                        System.out.println(ANSI_GREEN + "LOGIN SUCCESSFUL!" + ANSI_RESET);
                        loggedIn = true;
                        break;
                    } else {
                        System.out.println(ANSI_RED +"LOGIN FAILED!" + ANSI_RESET);
                        break;
                    }
                }
                case '2': {
                            Account user = menuLoginRegister(false);
                        if (StorageManager.exists(user)) {
                            System.out.println(ANSI_RED + "USER ALREADY EXISTS" + ANSI_RESET);
                            break;
                        } else if (!(v.checkAccount(user))) {
                            System.out.println(ANSI_RED + "INVALID USERNAME/PASSWORD TRY AGAIN!"+ ANSI_RESET);
                            break;
                        } else {
                            StorageManager.register(user);
                            System.out.println(ANSI_GREEN + "Registration successful!" + ANSI_RESET);
                            break;
                        }
                }
                case '3':
                    System.exit(0);
                default:
                    System.out.println(ANSI_RED + "Invalid choice" + ANSI_RESET);
                    return;
            }
        }while(!loggedIn);
        StorageManager.loadAccountFile();
        CalendarService calService = new CalendarService();
        EventService evtService = new EventService();
        char choice;
        calService.defaultPrint();
        do {
            menuLoggedIn();
            choice = sc.nextLine().charAt(0);
            switch(choice){
                case 'a':
                    calService.printPreviousMonth();
                    break;
                case 'd':
                    calService.printNextMonth();
                    break;
                case '1':
                    EventManager.createEvent();
                    break;
                case '2':
                    char evMenuChoice;
                    do {
                        menuEvents();
                        evMenuChoice = sc.nextLine().charAt(0);

                        switch(evMenuChoice){
                            case '1':
                                evtService.showAllEvents();
                                break;
                            case '2':
                                evtService.showAllEventsForCurrentMonth();
                                break;
                            case '3':
                                System.out.println("Enter first date:");
                                ChronoLocalDateTime date1 = EventManager.dateInput();
                                System.out.println("Enter second date:");
                                ChronoLocalDateTime date2 = EventManager.dateInput();
                                evtService.showAllEventsBetweenTwoDates(date1, date2);
                                break;
                            case '4':
                                break;
                            default:
                                System.out.println(ANSI_RED + "Invalid input" + ANSI_RESET);
                                break;
                        }
                    }while(evMenuChoice!='4');
                    calService.defaultPrint();
                    break;
                case '3':
                    EventManager.editEvent();
                    calService.defaultPrint();
                    break;
                case '4':
                    EventManager.deleteEvent();
                    calService.defaultPrint();
                    break;
                case '5':
                    System.exit(0);
                default:
                    break;
            }
        }while(choice != '6');







    }
//MENUS
    private static void menuLoggedIn(){
        System.out.println("USER:" + ANSI_CYAN + StorageManager.getUser() + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "__________________________" + ANSI_RESET);
        System.out.println("a <- previous month ----- next month -> d");
        System.out.println(ANSI_CYAN + "1)" + ANSI_RESET + "Create new event");
        System.out.println(ANSI_CYAN + "2)"+ ANSI_RESET + "Show events menu");
        System.out.println(ANSI_CYAN + "3)"+ ANSI_RESET + "Edit event");
        System.out.println(ANSI_CYAN + "4)" + ANSI_RESET + "Delete event");
        System.out.println(ANSI_CYAN + "5)" + ANSI_RESET +  "Exit");
        System.out.println("Choice:");
    }




    private static void menu(){
        System.out.println(ANSI_YELLOW + "=========================" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "Options:" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "1)" + ANSI_RESET + "Login");
        System.out.println(ANSI_CYAN + "2)" + ANSI_RESET + "Register");
        System.out.println(ANSI_CYAN + "1)" + ANSI_RESET + "Exit");
        System.out.print(ANSI_YELLOW + "Enter choice:" + ANSI_RESET);
    }

    private static Account menuLoginRegister(boolean login){
        System.out.println(ANSI_YELLOW +"=========================" + ANSI_RESET);
        if(login){
            System.out.println(ANSI_YELLOW + "Login form:" + ANSI_RESET);}
        else{
            System.out.println(ANSI_YELLOW + "Register form:" + ANSI_RESET);}
        System.out.print("Enter username:");
        String username = sc.nextLine();
        System.out.print("Enter password:");
        String password = sc.nextLine();
        return new Account(username, password);
    }

    private static void menuEvents(){
        System.out.println(ANSI_YELLOW +"=========================" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "1)" + ANSI_RESET + "Show all events");
        System.out.println(ANSI_CYAN + "2)" + ANSI_RESET + "Show events for the current month");
        System.out.println(ANSI_CYAN + "3)" + ANSI_RESET + "Show events between two dates");
        System.out.println(ANSI_CYAN + "4)" + ANSI_RESET + " Back");
        System.out.println("Choice:");
    }
}
