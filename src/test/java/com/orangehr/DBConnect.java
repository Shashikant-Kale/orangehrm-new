package com.orangehr;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by pu00160 on 4/20/2021.
 */
public class DBConnect {
    // Connection objectmy
    static Connection con = null;
    // Statement object
    private static Statement stmt;

    public ArrayList<String> ConnectMySQLDatabase(String DB_URL, String DB_USER, String DB_PASSWORD) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {

        // String[][] arrayDBData = null;
        // Make the database connection
        String dbClass = "com.mysql.jdbc.Driver";
        //String dbClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        Class.forName(dbClass);
        // Get connection to DB
        con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        // Statement object to send the SQL statement to the Database
        stmt = con.createStatement();

        String query = "select uname, pass from hrm_login";
        // Get the contents of userinfo table from DB
        ResultSet res = stmt.executeQuery(query);

        ArrayList<String> sqlData = new ArrayList<String>();
        while (res.next()) {
            sqlData.add(res.getString("uname")+"~"+res.getString("pass"));
        }

        // Close DB connection
        if (con != null) {
            con.close();
        }
        return sqlData;
    }
}
