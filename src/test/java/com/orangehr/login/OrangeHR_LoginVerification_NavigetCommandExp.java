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
public class OrangeHR_LoginVerification_NavigetCommandExp {

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
    public void Login_Successful() throws InterruptedException {

        //driver.get("https://opensource-demo.orangehrmlive.com/index.php/dashboard");
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/index.php/dashboard");

        Thread.sleep(10*1000);

        driver.findElement(By.name("txtUsername")).sendKeys("Admin");
        driver.findElement(By.name("txtPassword")).sendKeys("admin123");
        driver.findElement(By.id("btnLogin")).click();
       // driver.findElement(By.linkText("Dashboard")).isDisplayed();

        String ExpResult = "Dashboard";
        String ActResult = driver.findElement(By.linkText("Dashboard")).getText();
        Assert.assertEquals(ExpResult, ActResult);

        driver.findElement(By.linkText("Admin")).click();
        String ExpAdmin = "Admin";
        String ActAdmin = driver.findElement(By.linkText("Admin")).getText();
        Assert.assertEquals(ExpAdmin, ActAdmin);

        Thread.sleep(5000);
        driver.navigate().back();
        Thread.sleep(5000);

        String ExpDash = "Dashboard";
        String ActDash = driver.findElement(By.linkText("Dashboard")).getText();
        Assert.assertEquals(ExpDash, ActDash);

        driver.navigate().forward();
    }
}

