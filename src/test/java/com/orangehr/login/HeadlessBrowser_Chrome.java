package com.orangehr.login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by pu00160 on 4/14/2021.
 */
public class HeadlessBrowser_Chrome {

    WebDriver driver;

    @BeforeClass
    public  void LaunchBrowser(){
        //Define Chrome Driver
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true); //Headless
        //options.addArguments("incognito");//incognito mode
        driver = new ChromeDriver(options);
    }

    @AfterClass
    public void CloseBrowser(){
        //driver.close();  // will close only current browser
        driver.quit(); //Will close all browser opened ny Selenium
    }

    @Test
    public void LoginTest() throws InterruptedException {

        driver.get("https://opensource-demo.orangehrmlive.com/index.php/dashboard");
        Thread.sleep(10*1000);
        driver.findElement(By.name("txtUsername")).sendKeys("Admin");
        driver.findElement(By.name("txtPassword")).sendKeys("admin123");
        driver.findElement(By.id("btnLogin")).click();

        String ExpResult = "Dashboard";
        String ActResult = driver.findElement(By.linkText("Dashboard")).getText();
        Assert.assertEquals(ExpResult, ActResult);
    }

}
