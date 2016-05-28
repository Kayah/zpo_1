package com.akonoriev.database;

import javax.jws.soap.SOAPBinding;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sss on 12.03.16.
 */
public class UserDaoImpl implements UserDao{
    Connecter con;

    @Override
    public  void addUser(User user) {
        Connecter con = new Connecter();
        try {
            PreparedStatement preparedStatement = con.getConnection().prepareStatement("INSERT INTO user(" +
                    "userName," +
                    " pswd, " +
                    "rootRights," +
                    " isBlocked, " +
                    " isRestricted) " +
                    "VALUES(?,?,?,?,?)");

            preparedStatement.setString(1,user.getUserName());
            preparedStatement.setString(2,user.getPswd());
            preparedStatement.setBoolean(3,user.isRootRights());
            preparedStatement.setBoolean(4,user.isBlocked());
            preparedStatement.setBoolean(5,user.isRestricted());
            preparedStatement.executeUpdate();

            System.out.println("user successfully added");
            con.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public  Map<Integer, User> getAll() {
        Connecter con = new Connecter();
        String query = "select * from user";
        Map<Integer, User> map = new HashMap<>();

        int count = 0;
        try {
            Statement statement = con.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user = new User();
                count++;
                user.setUserId(resultSet.getInt("userId"));
                user.setUserName(resultSet.getString("userName"));
                user.setPswd(resultSet.getString("pswd"));
                user.setRootRights(resultSet.getBoolean("rootRights"));
                user.setBlocked(resultSet.getBoolean("isBlocked"));
                user.setRestricted(resultSet.getBoolean("isRestricted"));
                map.put(count,user);
            }
            con.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public void deleteUser(int id) {
        Connecter con = new Connecter();
        try {
            PreparedStatement preparedStatement = con.getConnection().prepareStatement("DELETE FROM user WHERE userId = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            System.out.println("user successfully deleted");
            con.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public User getUser(int id) {
        Connecter con = new Connecter();
        User user = new User();
        try {
            PreparedStatement preparedStatement = con.getConnection().prepareStatement("SELECT * FROM user WHERE userId =?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                user.setUserId(rs.getInt("userId"));
                user.setUserName(rs.getString("userName"));
                user.setPswd(rs.getString("pswd"));
                user.setRootRights(rs.getBoolean("rootRights"));
                user.setBlocked(rs.getBoolean("isBlocked"));
                user.setRestricted(rs.getBoolean("isRestricted"));
                System.out.println(user.getUserName());
            }con.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public  void updateUser(User user) {
        Connecter con = new Connecter();
        try {
            PreparedStatement preparedStatement = con.getConnection().prepareStatement("UPDATE user SET pswd = ?, rootRights = ?,isBlocked= ?,isRestricted= ? WHERE userId = ?;");
            preparedStatement.setString(1,user.getPswd());
            preparedStatement.setBoolean(2,user.isRootRights());
            preparedStatement.setBoolean(3,user.isBlocked());
            preparedStatement.setBoolean(4,user.isRestricted());
            preparedStatement.setInt(5,user.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void encryption(String Str) {
        con = new Connecter();
        try {

            PreparedStatement preparedStatement = con.getConnection().prepareStatement("Update user set pswd = AES_ENCRYPT(pswd, ?), userName = AES_ENCRYPT(userName, ?);");
            preparedStatement.setString(1,Str);
            preparedStatement.setString(2,Str);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Не верный пароль");
            System.out.println("Аварийное завершение !");
            System.exit(0);
        }
    }

    @Override
    public void decryption(String Str) {
        con = new Connecter();
        try {

            PreparedStatement preparedStatement = con.getConnection().prepareStatement("Update user set pswd = AES_DECRYPT(pswd, ?), userName = AES_DECRYPT(userName, ?);");
            preparedStatement.setString(1,Str);
            preparedStatement.setString(2,Str);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Не верный пароль");
            System.out.println("Аварийное завершение !");
            System.exit(0);
        }
    }
}
