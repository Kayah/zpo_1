package com.akonoriev.database;

/**
 * Created by sss on 22.03.16.
 */
public class SecurityImpl implements SecurityInterface{
    @Override
    public boolean verification(User user, String pswd) {
        return user.getPswd().equals(pswd);
    }
}
