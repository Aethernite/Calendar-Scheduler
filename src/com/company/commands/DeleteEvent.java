package com.company.commands;
import com.company.utils.EventService;
import com.company.utils.StorageManager;
import com.company.pages.DefaultPage;

import java.util.Scanner;

import static com.company.utils.AnsiColorCodes.*;


public class DeleteEvent implements Command {
    private Command parent;
    private static Scanner sc = new Scanner(System.in);

    public DeleteEvent(Command parent) {
        this.parent = parent;
    }

    @Override
    public Command execute(){
        int index = Integer.MIN_VALUE;
        int size = StorageManager.getSizeEventsList();
                if(EventService.showAllEvents()){
                    do {
                        System.out.println("Choose an event to delete(0 to go back):");
                        char choice = sc.nextLine().charAt(0);
                        if(choice=='0'){
                            return new DefaultPage(this);
                        }
                        index = choice - 49;
                        if(index<0 || index>size-1){
                            System.out.println(ANSI_RED + "Invalid index!" + ANSI_RESET);
                        }
                    }while(index<0 || index>size-1);
                    StorageManager.deleteEvent(index);
                    System.out.println(ANSI_GREEN + "Event deleted!" + ANSI_RESET);
                }
                return new DefaultPage(this);

            }
        }
