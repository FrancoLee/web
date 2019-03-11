package com.lx.animation.dao;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by root on 18-6-20.
 */
public class MysqlTest {
    @Test
    public void testAdd(){
        String driver = "com.mysql.jdbc.Driver";
        String URL = "jdbc:mysql://127.0.0.1/web?characterEncoding=utf8&useSSL=false" ;
        Connection con = null;
        try
        {
            Class.forName(driver);
        }
        catch(java.lang.ClassNotFoundException e)
        {
            System.out.println("Connect Successfull.");
            System.out.println("Cant't load Driver");
        }
        try
        {
            con= DriverManager.getConnection(URL,"root","`123qwe");
            System.out.println("Connect Successfull.");
        }
        catch(Exception e)
        {
            System.out.println("Connect fail:" + e.getMessage());
        }
    }
}
