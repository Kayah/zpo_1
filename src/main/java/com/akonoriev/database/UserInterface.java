package com.akonoriev.database;

import java.util.List;
import java.util.Map;

/**
 * Created by sss on 12.03.16.
 */
public interface UserInterface {
    void changePassword(String password);
    Map<Integer,User> getAllUsers();
    void addUser(User user);
    default boolean login(String userName, String password){
        throw new UnsupportedOperationException();
    };
    default void blockUser(User user){
        user.setBlocked(true);}
    void userEnterSession(User user, String password);
    default void userOutSession(){System.out.println("goodBye, see you soon ! ");}
    User getFromSession();
    default void enableRestriction(User user){user.setRestricted(true);}
    default void exit() {userOutSession();System.exit(0);}

}
