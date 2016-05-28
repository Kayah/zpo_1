package com.akonoriev.database;

/**
 * Created by sss on 22.03.16.
 */
public class ShowMenu {
    public static void showMenu(){
        System.out.println("==================");
        System.out.println("       Menu       ");
        System.out.println("------------------");
        System.out.println("1) Login          ");
        System.out.println("------------------");
        System.out.println("2) Exit           ");
        System.out.println("==================");
    }
    public static void showForAdmin(){
        System.out.println("==================");
        System.out.println("       Menu       ");
        System.out.println("------------------");
        System.out.println("1) Show Users     ");
        System.out.println("------------------");
        System.out.println("2) Change Password");
        System.out.println("------------------");
        System.out.println("3) Add user       ");
        System.out.println("------------------");
        System.out.println("4) Block user     ");
        System.out.println("------------------");
        System.out.println("5) Set limits     ");
        System.out.println("--------------------");
        System.out.println("6) Add encryption key ");
        System.out.println("--------------------");
        System.out.println("7) Exit           ");
        System.out.println("==================");


    }
    public static void showForUser(){
        System.out.println("==================");
        System.out.println("       Menu       ");
        System.out.println("------------------");
        System.out.println("1) Change Password");
        System.out.println("------------------");
        System.out.println("2) Exit           ");
        System.out.println("==================");
    }
}
