package com.akonoriev.database;

/**
 * Created by sss on 11.03.16.
 */
public class User {
    private int userId;
    private String userName;
    private String pswd;
    private boolean rootRights;
    private boolean isBlocked;
    private boolean isRestricted;

    public User(){

    }

    public User(String userName) {
        this.userName = userName;
    }

    public User(int userId, String userName, String pswd, boolean rootRights, boolean isBlocked, boolean isRestricted){
        this.userId = userId;
        this.userName = userName;
        this.pswd = pswd;
        this.rootRights = rootRights;
        this.isBlocked = isBlocked;
        this.isRestricted = isRestricted;
    }
    public int getUserId(){
        return userId;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getPswd(){
        return pswd;
    }

    public void setPswd(String pswd){
        this.pswd = pswd;
    }

    public boolean isRootRights(){
        return rootRights;
    }

    public void setRootRights(boolean rootRights){
        this.rootRights = rootRights;
    }

    public boolean isBlocked(){
        return isBlocked;
    }

    public void setBlocked(boolean isBlocked){
        this.isBlocked = isBlocked;
    }

    public void setRestricted(boolean isRestricted){
        this.isRestricted = isRestricted;
    }

    public boolean isRestricted(){
        return this.isRestricted;
    }


    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        User user = (User) obj;
        return userId == user.userId;
    }

    @Override
    public int hashCode() {
        return userId;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{id: " + userId + ", username " + userName+
                ", password " + pswd + ", is root " + rootRights + ", is blocked " + isBlocked + ", any limits  " + isRestricted + "}";
    }
}
