package com.company;

import java.util.ArrayList;

public class AccountLibrary {
    private static ArrayList<Account> list = new ArrayList<Account>();


    public static boolean exists(Account acc){
        for(Account curr: list){
            if(curr.getUsername().equals(acc.getUsername())){
                return true;
            }
        }
        return false;
    }

    public static boolean checkLogin(Account acc){
        for(Account curr: list){
            if(curr.getUsername().equals(acc.getUsername())){
                if(curr.getPassword().equals(acc.getPassword())){
                    return true;
                }
            }
        }
        return false;
    }

    public static void register(Account acc){
        list.add(acc);
    }
}
