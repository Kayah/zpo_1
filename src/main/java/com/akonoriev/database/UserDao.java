package com.akonoriev.database;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sss on 12.03.16.
 */
public interface UserDao {
    void addUser(User user);
    Map<Integer,User> getAll();
    void deleteUser(int id);
    User getUser(int id);
    void updateUser(User user);
    default User getByName (String name){
        return ((HashMap<Integer,User>)getAll()).values().stream().filter(u ->u.getUserName().equalsIgnoreCase(name)).findFirst().get();
    }
    void encryption(String str);
    void decryption(String str);
}
