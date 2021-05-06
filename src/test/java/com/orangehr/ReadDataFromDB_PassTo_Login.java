package com.orangehr;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by pu00160 on 4/20/2021.
 */
public class ReadDataFromDB_PassTo_Login extends DBConnect {

    ChromeDriver driver;

    @BeforeTest
    public void LaunchApp() throws Exception {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/");
        Thread.sleep(5000);
    }

    @Test
    public void ConnectSQLDB_With_LoginApp() throws ClassNotFoundException, SQLException, InterruptedException,
            InstantiationException, IllegalAccessException {

        ArrayList<String> sqlData = ConnectMySQLDatabase("jdbc:mysql://localhost:3306/orangehrm", "root", "Sharayu@12");
        System.out.println("SQL Data from DB Example Class : " + sqlData + "\n Array List Size : " + sqlData.size());

        for (String sqlValues : sqlData) {
            System.out.println(sqlValues + " split : 0\t" + sqlValues.split("~")[0]);
            System.out.println(sqlValues + " split : 1\t" + sqlValues.split("~")[1]);
            String uname=sqlValues.split("~")[0];
            String upass=sqlValues.split("~")[1];
            //String expresult=sqlValues.split("~")[1];

            driver.findElementById("txtUsername").clear();
            driver.findElementById("txtUsername").sendKeys(uname);
            driver.findElementById("txtPassword").clear();
            driver.findElementById("txtPassword").sendKeys(upass);
            driver.findElementById("btnLogin").click();
            Thread.sleep(4000);
            driver.findElementById("welcome").click();
            Thread.sleep(3000);
            driver.findElementByLinkText("Logout").click();
            Thread.sleep(3000);
        }

    }

    @AfterTest
    public void CloseApp() throws Exception {

        driver.quit();
    }

}
