package com.company.commands;

import com.company.objects.Event;
import com.company.pages.DefaultPage;
import com.company.pages.EditEventMenuPage;
import com.company.utils.EventService;
import com.company.utils.StorageManager;

import java.util.Scanner;

import static com.company.utils.AnsiColorCodes.*;
import static com.company.utils.AnsiColorCodes.ANSI_RESET;

public class EditEvent implements Command {
    private Command parent;
    private static Scanner sc = new Scanner(System.in);

    public EditEvent(Command parent) {
        this.parent = parent;
    }

    @Override
    public Command execute() {
        int index = Integer.MIN_VALUE;
        if (EventService.showAllEvents()){
            int size = StorageManager.getSizeEventsList();
            char choice;
            do {
                System.out.println("Choose an event to delete(0 to go back):");
                choice = sc.nextLine().charAt(0);
                if (choice == '0') {
                    return new DefaultPage(this);
                }
                index = choice - 49;
                if (index < 0 || index > size - 1) {
                    System.out.println(ANSI_RED + "Invalid index!" + ANSI_RESET);
                }
            } while (index < 0 || index > size - 1);
            Event event = StorageManager.getListEvents().get(index);
            return new EditEventMenuPage(this, event, index);

        }
        return new DefaultPage(this);
    }
}

