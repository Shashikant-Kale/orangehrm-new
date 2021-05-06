package com.orangehr.login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by pu00160 on 4/12/2021.
 */
public class OrangeHR_Login_TestNG_Parameters_Ex {

    WebDriver driver;

    @Test
    @Parameters({"url","uname","upass"})
    public void Login(String url,String uname,String upass) throws InterruptedException {
        //driver.navigate().to(url);
        driver.get(url);
        Thread.sleep(10*1000);
        driver.findElement(By.name("txtUsername")).sendKeys(uname);
        driver.findElement(By.name("txtPassword")).sendKeys(upass);
        driver.findElement(By.id("btnLogin")).click();
        // Verify that Dashboard page displayed
        String ActElement = driver.findElement(By.linkText("Dashboard")).getText();
        String ExpElement = "Dashboard";
        Assert.assertEquals(ActElement, ExpElement);
        System.out.println(ActElement);

        driver.findElement(By.xpath("//a[contains(text(),'Welcome')]")).click();
        Thread.sleep(3000);
        driver.findElement(By.linkText("Logout")).click();
        driver.findElement(By.id("logInPanelHeading")).isDisplayed();

    }

    @BeforeTest
    public void LaunchBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterTest
    public void CloseBrowser() {
        // driver.close();//Close will close only current chrome browser
        driver.quit();
    }
}

