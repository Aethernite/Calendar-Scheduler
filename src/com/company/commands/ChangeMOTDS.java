package com.company.commands;

import com.company.commands.pages.DefaultCalendarPrint;
import com.company.commands.pages.MainMenuPage;
import com.company.utils.storage.StorageManager;

import java.util.Scanner;

public class ChangeMOTDS implements Command {
    private Command parent;
    private Scanner sc = new Scanner(System.in);


    public ChangeMOTDS(Command parent){
        this.parent = parent;
    }

    @Override
    public Command execute(){
        printMOTDS();
        return interactWithUser();
    }
    private Command interactWithUser(){
        System.out.println("Choose day of month to set new MOTD(0 to go back):");
        int day;
        while(true) {
            try {
                day = Integer.parseInt(sc.nextLine());
                if(day==0){
                    return new DefaultCalendarPrint(this);
                }
                if (day >= 1 && day <= 31) {
                    break;
                }
                else{
                    System.out.println("Enter a valid number!(1-31)");
                }
            } catch (Exception e) {
                System.out.println("Enter a number!");
            }
        }
        System.out.println("Enter your motd:");
        String motd = sc.nextLine();
        StorageManager.setMOTD(day,motd);
        return new DefaultCalendarPrint(this);
    }


    private void printMOTDS(){
        String[] motds = StorageManager.getMOTDS();
        int i = 1;
        System.out.println("Message of the day for all days:");
        for(String motd: motds){
            if(motd == null){
                System.out.println(i++ + ")No motd found");
                continue;
            }
            System.out.println(i++ + ")" + motd);
        }
    }
}
