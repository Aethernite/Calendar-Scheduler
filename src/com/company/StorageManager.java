package com.company;

import java.io.*;
import java.util.ArrayList;



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
            System.out.println("ERROR LOADING DB INTO MEMORY");
        }
    }
    public static void updateDataBase(){
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
            System.out.println("ERROR UPDATING DB FILE");
        }
    }


//EVENT LOADER METHOD
    public static void loadAccountFile(){
        File accFile = new File("./Data/AccountData/" + user.getUsername().toLowerCase() + ".dat");
        try {
            accFile.createNewFile(); // if file already exists will do nothing
        } catch(Exception e){
            System.out.println("ERROR LOADING FILE INTO PROGRAM");
        }
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
        }catch(Exception e){
            System.out.println("ERROR OPENING STREAMS TO FILE");
        }
    }

    public static void updateAccountFile(){
        File accFile = new File("./Data/AccountData/" + user.getUsername().toLowerCase() + ".dat");
        try {
            accFile.createNewFile(); // if file already exists will do nothing
        } catch(Exception e){
            System.out.println("ERROR LOADING FILE INTO PROGRAM");
        }
        try {
            FileOutputStream fos = new FileOutputStream(accFile,false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for(Event event: listEvents){
                oos.writeObject(event);
            }
            oos.close();
            fos.close();
        }catch(Exception e){
            System.out.println("ERROR OPENING STREAMS TO FILE");
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
    }

}
