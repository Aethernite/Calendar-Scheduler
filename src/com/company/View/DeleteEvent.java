package com.company.View;
import com.company.EventService;
import com.company.StorageManager;
import java.util.Scanner;



public class DeleteEvent implements Command {
    private Command parent;
    private static Scanner sc = new Scanner(System.in);

    public DeleteEvent(Command parent) {
        this.parent = parent;
    }

    @Override
    public Command execute(){
                if(EventService.showAllEvents()){
                    System.out.println("Choose an event to delete:");
                    char choice = sc.nextLine().charAt(0);
                    int index = choice - 49;
                    StorageManager.deleteEvent(index);
                }
                return new DefaultPage(this);

            }
        }
