package com.akonoriev.database;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sss on 22.03.16.
 */
public class Menu {
    SecurityInterface SecurityInterface = new SecurityImpl();
    UserInterface UserInterface = new UserInterfaceImpl();
    UserDao UserDao = new UserDaoImpl();
    static Scanner scanner = new Scanner(System.in);
    SecurityInterface securityInterface = new SecurityImpl();
    private UserInterface userInterface = new Security(SecurityInterface, UserInterface, UserDao);
    UserDao userDao = new UserDaoImpl();
    String key = "";
    Menu(String string){
        key = string;
    }

    public void Menu(){
        ShowMenu.showMenu();
        int chose = scanner.nextInt();
        scanner.nextLine();
        switch (chose){
            case 1:userDao.decryption(key);login();
            case 2:userInterface.exit();
                default:{
                    System.out.println("invalid format");
                    Menu();
                }
        }
    }

    private void login(){
        int counter = 0;
        String userName;
        String password;
        do{
            if(counter==3){
                userInterface.exit();
            }
            System.out.println("input your name");
            userName = scanner.nextLine();
            System.out.println("input your password");
            password = scanner.nextLine();
            if(userInterface.login(userName,password))break;
            else {
                counter++;
                if(counter!=3)
                    System.out.println("input valid username or password");
                if(counter==3)
                    System.out.println("invalid input, closing..");
            }
        }
        while (true);

        if(securityInterface.isBlocked(userInterface.getFromSession())){
            System.out.println("You are blocked, contact the admin");
            userInterface.exit();
        }
        if(securityInterface.isAdmin(userInterface.getFromSession())){
            showForAdmin();
        }
        if(securityInterface.isUser(userInterface.getFromSession())){
            showForUser();
        }
    }

    void showForAdmin(){
        ShowMenu.showForAdmin();
        int chose = scanner.nextInt();
        scanner.nextLine();
        switch (chose){
            case 1: {
                userInterface.getAllUsers().values().stream().forEach(System.out::println);
                showForAdmin();
            }
            case 2: changePassword();
            case 3: addUser();
            case 4: blockUser();
            case 5: restrictions();
            case 6: addEncrKey();
            case 7: {
                userInterface.userOutSession();
                userDao.encryption(key);
                Menu();
            }
            default:{
                System.out.println("invalid format");
                showForAdmin();
            }
        }

    }

    private void addEncrKey() {
        scanner = new Scanner(System.in);
        System.out.println("Введите новый ключ");
        String newKey = scanner.next();
        System.out.println("Старый ключ - " + key +" " + " новый ключ - " + newKey);
        System.out.println("Не забудьте его !!!");
        key = newKey;
        showForAdmin();
    }

    void showForUser(){
        ShowMenu.showForUser();
        int chose = scanner.nextInt();
        scanner.nextLine();
        switch (chose){
            case 1: changePassword();
            case 2: {
                userInterface.userOutSession();
                userDao.encryption(key);
                Menu();
            }
            default:{
                System.out.println("invalid format");
                showForUser();
            }
        }
    }

    void restrictions(){
        System.out.println("Input id");
        int id = scanner.nextInt();
        scanner.nextLine();
        User user = userDao.getUser(id);
        user.setRestricted(true);
        userDao.updateUser(user);
        showForAdmin();

    }

    boolean restriction(String password){
        //Pattern p = Pattern.compile("^[a-zA-Zа-яА-Я]+");
        Pattern p = Pattern.compile("^[A-Za-z\\D]+");
        Matcher m = p.matcher(password);
        return m.matches();
    }

    void changePassword(){
        User user = userInterface.getFromSession();
        String password;
        System.out.println("input your password");
        password = scanner.nextLine();
        if(password.equals(user.getPswd())){
            System.out.println("input new password");
            password = scanner.nextLine();
           if(user.isRestricted()){
               System.out.println("Restriction on password ");
               if(restriction(password)){
                   user.setPswd(password);
                   userDao.updateUser(user);
               }else System.out.println("invalid password");

            }else {
               user.setPswd(password);
               userDao.updateUser(user);
            }
        }else {
            System.out.println("Invalid input");
        }
        if(user.isRootRights()) showForAdmin();
        if(!user.isRootRights()) showForUser();
    }

    void addUser(){
        System.out.println("Input username");
        String userName = scanner.nextLine();
        userInterface.addUser(new User(userName));
        System.out.println("user is added");
        showForAdmin();
    }

    void blockUser(){
        System.out.println("Input id");
        int id = scanner.nextInt();
        scanner.nextLine();
        User user = userDao.getUser(id);
        user.setBlocked(true);
        userDao.updateUser(user);
        showForAdmin();
    }




}
