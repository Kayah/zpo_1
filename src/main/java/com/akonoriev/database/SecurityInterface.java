package com.akonoriev.database;

/**
 * Created by sss on 22.03.16.
 */
public interface SecurityInterface {
    boolean verification(User user, String pswd);

    default boolean isAdmin(User user){
        return user.isRootRights();
    }
    default boolean isUser(User user){
        return !user.isRootRights();
    }
    default boolean isBlocked(User user){
        return user.isBlocked();
    }
}
