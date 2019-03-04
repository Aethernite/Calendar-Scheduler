package com.company;

import java.io.*;
import java.util.ArrayList;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;


public class StorageManager {
    private static ArrayList<Account> listAcc = new ArrayList<Account>();


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
