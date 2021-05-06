package com.orangehr.login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by pu00160 on 4/12/2021.
 */
public class OrangeHR_LoginVerification_GetCommand {

    WebDriver driver;

    @Before
    public  void LaunchBrowser(){
        //Define Chrome Driver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @After
    public void CloseBrowser(){
        //driver.close();  // will close only current browser
        driver.quit(); //Will close all browser opened ny Selenium
    }

    @Test
    public void Login_Successful(){

        //driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
        driver.get("https://opensource-demo.orangehrmlive.com/index.php/dashboard");
        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.name("txtUsername")).sendKeys("Admin");
        driver.findElement(By.name("txtPassword")).sendKeys("admin123");
        driver.findElement(By.id("btnLogin")).click();
       // driver.findElement(By.linkText("Dashboard")).isDisplayed();
        
        String ExpResult = "Dashboard";
        String ActResult = driver.findElement(By.linkText("Dashboard")).getText();
        Assert.assertEquals(ExpResult, ActResult);

        //to verify landing page url
        String ExpURL = "https://opensource-demo.orangehrmlive.com/index.php/dashboard";
        String ActURL = driver.getCurrentUrl();
        Assert.assertEquals(ExpResult, ActResult);

        //to verify landing page
        String ExpTitle = "OrangeHRM";
        String ActTitle = driver.getTitle();
        Assert.assertEquals(ExpResult, ActResult);

    }
}

