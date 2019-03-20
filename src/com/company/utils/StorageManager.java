package com.company.utils;

import com.company.objects.Account;
import com.company.objects.Event;

import java.io.*;
import java.util.ArrayList;

import static com.company.utils.AnsiColorCodes.*;


public class StorageManager {
    private static Account user;
    private static ArrayList<Account> listAcc = new ArrayList<Account>();
    private static ArrayList<Event> listEvents = new ArrayList<Event>();

//DATA BASE UPDATE METHODS
    public static void loadAccDataIntoMemory(){
        try{
            FileInputStream fis = new FileInputStream(new File("./Data/db.dat"));
            ObjectInputStream ois = new ObjectInputStream(fis);
            listAcc = new ArrayList<Account>();
            while(true){
                try{
                    listAcc.add((Account)ois.readObject());
                }
                catch(Exception e){
                    break;
                }
            }
            ois.close();
            fis.close();
        }
        catch(Exception e){
            System.out.println(ANSI_RED + "ERROR LOADING DB INTO MEMORY" + ANSI_RESET);
        }
    }
    private static void updateDataBase(){
        try{
            FileOutputStream fos = new FileOutputStream(new File("./Data/db.dat"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for(Account acc: listAcc){
                oos.writeObject(acc);
            }
            oos.close();
            fos.close();
        }
        catch(Exception e){
            System.out.println(ANSI_RED + "ERROR UPDATING DB FILE" + ANSI_RESET);
        }
    }


//EVENT LOADER METHOD
    public static void loadAccountFile(){
        File accFile = new File("./Data/AccountData/" + user.getUsername().toLowerCase() + ".dat");
        try {
            FileInputStream fis = new FileInputStream(accFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            listEvents = new ArrayList<Event>();
            while(true){
                try{
                    listEvents.add((Event)ois.readObject());
                }
                catch(Exception e){
                    break;
                }
            }
            ois.close();
            fis.close();
        }catch(IOException e){
            System.out.println(ANSI_GREEN + "ACCOUNT FILE EMPTY" + ANSI_RESET);
        }
        catch(Exception e){
            System.out.println(ANSI_RED + "ERROR IN LOADING FILE" + ANSI_RESET);
        }
    }

    private static void updateAccountFile(){
        File accFile = new File("./Data/AccountData/" + user.getUsername().toLowerCase() + ".dat");
        try {
            FileOutputStream fos = new FileOutputStream(accFile,false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for(Event event: listEvents){
                oos.writeObject(event);
            }
            oos.close();
            fos.close();
        }catch(Exception e){
            System.out.println(ANSI_RED + "ERROR IN UPDATING ACCOUNT FILE" + ANSI_RESET);
        }
    }

    public static void addEvent(Event event){
        listEvents.add(event);
        updateAccountFile();
    }

    public static ArrayList<Event> getListEvents(){
        return listEvents;
    }

    public static void setUser(Account account){
        user = account;
    }

    public static String getUser(){
        return user.getUsername();
    }




//ACCOUNT CHECK METHODS

    public static boolean exists(Account acc){
        for(Account curr: listAcc){
            if(curr.getUsername().equals(acc.getUsername())){
                return true;
            }
        }
        return false;
    }

    public static boolean checkLogin(Account acc){
        for(Account curr: listAcc){
            if(curr.getUsername().equals(acc.getUsername())){
                if(curr.getPassword().equals(acc.getPassword())){
                    return true;
                }
            }
        }
        return false;
    }

    public static void register(Account acc){
        listAcc.add(acc);
        updateDataBase();
        File accFile = new File("./Data/AccountData/" + acc.getUsername().toLowerCase() + ".dat");
        try {
            accFile.createNewFile();
        } catch(IOException e){
            System.out.println(ANSI_RED + "ERROR CREATING FILE FOR THE ACCOUNT" + ANSI_RESET);
        }
    }
   //EVENT EDIT

    public static void editEvent(int index,Event ev){
        listEvents.set(index,ev);
        updateAccountFile();
    }

    public static void deleteEvent(int index){
        listEvents.remove(index);
        updateAccountFile();
    }

    //List size

    public static int getSizeEventsList(){
        return listEvents.size();
    }
}
