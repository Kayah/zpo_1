package com.akonoriev.database;

import java.awt.*;
import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.List;
import java.util.prefs.Preferences;

/**
 * Created by sss on 11.03.16.
 */
public class Main {

    private static final String OS_NAME = System.getProperty("os.name");
    private static final String OS_VERSION = System.getProperty("os.version");
    private static final String USER_NAME = System.getProperty("user.name");
    private static final double WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();

    static String read() throws Exception {
        StringBuilder sb = new StringBuilder();
        Scanner in = new Scanner(new FileInputStream("/home/sss/Рабочий стол/laba_version_2/hash.txt"),"Cp1251");
        while(in.hasNext())
            sb.append(in.nextLine());
        in.close();
        return sb.toString().toLowerCase();
    }

    public static void main(String[] args) throws Exception,IOException, IllegalAccessException, IllegalAccessException, InvocationTargetException{
        Scanner scanner = new Scanner(System.in);
        InetAddress address = InetAddress.getLocalHost();
        String hostname = address.getHostName();
        System.out.println("Enter secret key");
        String userKey = scanner.next();
        //String toHash = OS_NAME+OS_VERSION+USER_NAME+WIDTH+hostname+userKey;
        //String hashCode = String.valueOf(toHash.hashCode());
        //if(hashCode.equals(read())){
            Menu menu = new Menu(userKey);
            menu.Menu();
        //}else System.out.println("incorret key");
    }
}
