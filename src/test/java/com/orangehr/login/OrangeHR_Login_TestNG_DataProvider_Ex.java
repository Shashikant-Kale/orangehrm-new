package com.orangehr.login;

import com.orangehr.OrangeHR_TestData;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by pu00160 on 4/12/2021.
 */
public class OrangeHR_Login_TestNG_DataProvider_Ex extends OrangeHR_TestData {

    WebDriver driver;

    @BeforeClass
    public  void LaunchBrowser(){
        //Define Chrome Driver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
    }

    @AfterClass
    public void CloseBrowser(){
        //driver.close();  // will close only current browser
        driver.quit(); //Will close all browser opened ny Selenium
    }

    @Test(dataProvider = "LoginData")
    public void LoginTest(String uname, String upass) throws InterruptedException {

        Thread.sleep(10*1000);
        driver.findElement(By.name("txtUsername")).sendKeys(uname);
        driver.findElement(By.name("txtPassword")).sendKeys(upass);
        driver.findElement(By.id("btnLogin")).click();
        String ExpURL="https://opensource-demo.orangehrmlive.com/index.php/dashboard";
        String ActURL=driver.getCurrentUrl();
        Assert.assertEquals(ExpURL, ActURL);
        Thread.sleep(2000);

        // Logout
        driver.findElement(By.id("welcome")).click();
        Thread.sleep(4000);
        driver.findElement(By.linkText("Logout")).click();
        String ExpLoginURL="https://opensource-demo.orangehrmlive.com/index.php/auth/login";
        String ActLoginURL=driver.getCurrentUrl();
        Assert.assertEquals(ExpLoginURL, ActLoginURL);
    }

}

