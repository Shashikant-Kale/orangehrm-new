package com.orangehr;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.*;

/**
 * Created by pu00160 on 4/20/2021.
 */
public class ConnectDB_ReadData_PassData_Orangehrm_Login {

    ChromeDriver driver;
    @BeforeMethod
    public void LaunchApp() throws Exception {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/");
        Thread.sleep(5000);
        //driver.manage().window().maximize();
    }

    @Test
    public void  ConnectSQLDB() throws  ClassNotFoundException, SQLException, InterruptedException {
        //Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"
        String dbUrl = "jdbc:mysql://localhost:3306/orangehrm";

        //Database Username
        String username = "root";

        //Database Password
        String password = "Sharayu@12";

        //Query to Execute
        String query = "select * from hrmlogin;";

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
            //Pass Data to Login OrnageHRM

            driver.findElementById("txtUsername").clear();
            driver.findElementById("txtUsername").sendKeys(Username);
            driver.findElementById("txtPassword").clear();
            driver.findElementById("txtPassword").sendKeys(Password);
            driver.findElementById("btnLogin").click();
            Thread.sleep(3000);
            driver.findElementById("welcome").click();
            Thread.sleep(3000);
            driver.findElementByLinkText("Logout").click();
            Thread.sleep(3000);

        }
        // closing DB Connection
        con.close();
    }
    @AfterTest
    public void CloseAPP() throws Exception {

        driver.quit();
    }


}
