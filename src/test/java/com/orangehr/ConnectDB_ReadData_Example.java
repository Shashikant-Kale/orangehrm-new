package com.orangehr;

import org.testng.annotations.Test;

import java.sql.*;

/**
 * Created by pu00160 on 4/20/2021.
 */
public class ConnectDB_ReadData_Example {

    @Test
    public void  ConnectSQLDB() throws  ClassNotFoundException, SQLException {
        //Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"
        String dbUrl = "jdbc:mysql://localhost:3306/orangehrm";
        //Database Username
        String username = "root";
        //Database Password
        String password = "Sharayu@12";
        //Query to Execute
        String query = "select * from hrm_login;";
        //Load mysql jdbc driver
        Class.forName("com.mysql.jdbc.Driver");
        //Create Connection to DB
        Connection con = DriverManager.getConnection(dbUrl,username,password);
        //Create Statement Object
        Statement stmt = con.createStatement();
        // Execute the SQL Query. Store results in ResultSet
        ResultSet rs= stmt.executeQuery(query);

        // While Loop to iterate through all data and print results
        while (rs.next()){
            String Username = rs.getString("uname");
            String Password = rs.getString("pass");
            System. out.println(Username+"  "+Password);

        }
        // closing DB Connection
        con.close();
    }

}
