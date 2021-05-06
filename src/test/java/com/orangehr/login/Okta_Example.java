package com.orangehr.login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by pu00160 on 4/21/2021.
 */
public class Okta_Example {
    WebDriver driver;

    @BeforeClass
    public  void LaunchBrowser(){
        //Define Chrome Driver
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        //options.setHeadless(true); //Headless
        options.addArguments("incognito");//incognito mode
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

        WebDriverWait wait = new WebDriverWait(driver, 60);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("okta-signin-username")));

        //Okta Login
        driver.findElement(By.id("okta-signin-username")).sendKeys("pu00160");
        driver.findElement(By.id("okta-signin-password")).sendKeys("Sharayu%33");
        driver.findElement(By.id("okta-signin-submit")).click();


        Thread.sleep(10000);
        driver.findElement(By.name("txtUsername")).sendKeys("Admin");
        driver.findElement(By.name("txtPassword")).sendKeys("admin123");
        driver.findElement(By.id("btnLogin")).click();

        String ExpResult = "Dashboard";
        String ActResult = driver.findElement(By.linkText("Dashboard")).getText();
        Assert.assertEquals(ExpResult, ActResult);
    }

}
