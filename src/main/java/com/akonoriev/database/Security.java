package com.akonoriev.database;

import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Created by sss on 22.03.16.
 */
public class Security implements UserInterface {
    private UserInterface userInterface;
    private SecurityInterface securityInterface;
    private UserDao userDao;

    public Security(SecurityInterface securityInterface, UserInterface userInterface, UserDao userDao) {
        this.securityInterface = securityInterface;
        this.userInterface = userInterface;
        this.userDao = userDao;
    }

    @Override
    public void changePassword(String password) {
        if(securityInterface.isAdmin(this.getFromSession())||securityInterface.isUser(this.getFromSession())){
            userInterface.changePassword(password);
        }else throw new SecurityException("You don't have this permission !");
    }

    @Override
    public Map<Integer, User> getAllUsers() {
        if(securityInterface.isAdmin(this.getFromSession())){
            return userInterface.getAllUsers();
        }else throw new SecurityException("You don't have this permission !");
    }

    @Override
    public void addUser(User user) {
        if(securityInterface.isAdmin(this.getFromSession())){
            userInterface.addUser(user);
        }else throw new SecurityException("You don't have this permission !");

    }

    @Override
    public void userEnterSession(User user, String password) {
        if(securityInterface.verification(user,password)){
            userInterface.userEnterSession(user, password);
        }else throw new SecurityException("You don't have this permission !");
    }

    @Override
    public User getFromSession() {
        return userInterface.getFromSession();
    }

   @Override
    public boolean login(String userName, String password) {
        try {
            userEnterSession(userDao.getByName(userName),password);
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
       catch (SecurityException e){
           return false;
       }
    }
}
