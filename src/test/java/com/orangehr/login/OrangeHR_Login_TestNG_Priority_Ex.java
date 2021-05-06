package com.orangehr.login;

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
public class OrangeHR_Login_TestNG_Priority_Ex {

    WebDriver driver;

    @BeforeClass
    public  void LaunchBrowser(){
        //Define Chrome Driver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterClass
    public void CloseBrowser(){
        //driver.close();  // will close only current browser
        driver.quit(); //Will close all browser opened ny Selenium
    }

    @Test(priority = 1)
    public void Login() throws InterruptedException {

        driver.get("https://opensource-demo.orangehrmlive.com/index.php/dashboard");
        Thread.sleep(10*1000);
        driver.findElement(By.name("txtUsername")).sendKeys("Admin");
        driver.findElement(By.name("txtPassword")).sendKeys("admin123");
        driver.findElement(By.id("btnLogin")).click();

        String ExpResult = "Dashboard";
        String ActResult = driver.findElement(By.linkText("Dashboard")).getText();
        Assert.assertEquals(ExpResult, ActResult);
    }

    @Test(priority = 2)
    public void Logout() throws InterruptedException {
        driver.findElement(By.id("welcome")).click();
        Thread.sleep(5000);
        driver.findElement(By.linkText("Logout")).click();

        String ExpURL="https://opensource-demo.orangehrmlive.com/index.php/auth/login";
        String ActURL=driver.getCurrentUrl();
        Assert.assertEquals(ExpURL, ActURL);

    }

}

