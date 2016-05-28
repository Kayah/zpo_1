package com.akonoriev.database;

import java.io.*;
import java.util.*;

/**
 * Created by sss on 12.03.16.
 */
public class UserInterfaceImpl implements UserInterface {
    UserDao userDao = new UserDaoImpl();
    @Override
    public void changePassword(String password) {
        User user = userDao.getUser(getFromSession().getUserId());
        user.setPswd(password);
        userDao.updateUser(user);
    }

    @Override
    public Map<Integer, User> getAllUsers() {

        return userDao.getAll();
    }

    @Override
    public void addUser(User user) {
        user.setPswd("");
        userDao.addUser(user);
    }
    

    @Override
    public void blockUser(User user) {
        user.setBlocked(true);

    }

    @Override
    public void userEnterSession(User user, String password) {
        File file = new File("userSession.txt");

        try {
            if(!file.exists()){
                file.createNewFile();
            }

            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            try {
                out.print(user.getUserId());
            } finally {
                out.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void userOutSession() {
        System.out.println("goodbye !");
    }


    @Override
    public User getFromSession()  {
        StringBuilder sb = new StringBuilder();
        Scanner in = null;
        try {
            in = new Scanner(new FileInputStream("userSession.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        userDao = new UserDaoImpl();
        while(in.hasNext()) {
            return  userDao.getUser(Integer.parseInt(in.nextLine()));
        }
        in.close();
        throw new NoSuchElementException();
    }




}
