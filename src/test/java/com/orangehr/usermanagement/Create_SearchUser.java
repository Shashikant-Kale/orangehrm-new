package com.orangehr.usermanagement;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Random;

/**
 * Created by pu00160 on 4/12/2021.
 */
public class Create_SearchUser {

    WebDriver driver;
    @Test
    public void Search() throws InterruptedException {

        driver.navigate().to("https://opensource-demo.orangehrmlive.com/");

        Thread.sleep(10*1000);

        driver.findElement(By.name("txtUsername")).sendKeys("Admin");
        driver.findElement(By.name("txtPassword")).sendKeys("admin123");
        driver.findElement(By.id("btnLogin")).click();


        WebElement Admin = driver.findElement(By.linkText("Admin"));

        //Add User Section
        Actions action = new Actions(driver);
        action.moveToElement(Admin).build().perform();

        WebElement usermanagement = driver.findElement(By.linkText("User Management"));
        action.moveToElement(usermanagement).build().perform();

        driver.findElement(By.linkText("Users")).click();
        driver.findElement(By.xpath("//input[@id='btnAdd']")).isDisplayed();
        driver.findElement(By.xpath("//input[@id='btnAdd']")).click();

        Select SelectPass = new Select(driver.findElement(By.id("systemUser_userType")));
        //SelectPass.selectByValue("1");
        SelectPass.selectByVisibleText("Admin");
        //SelectPass.selectByIndex(0);
        driver.findElement(By.id("systemUser_employeeName_empName")).sendKeys("Fiona Grace");

        //Using Randon class, we can generate Random number between given value
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(100);
        WebElement UserNameInputbox = driver.findElement(By.id("systemUser_userName"));
        UserNameInputbox.sendKeys("sivaharsha" + randomInt);
        //Grab the value after entering
        String EnteredValue = UserNameInputbox.getAttribute("value");
        // driver.findElement(By.id("systemUser_userName")).sendKeys("sivaharsha");
        driver.findElement(By.id("systemUser_password")).sendKeys("harshaaa");
        driver.findElement(By.id("systemUser_confirmPassword")).sendKeys("harshaaa");
        Thread.sleep(2000);
        driver.findElement(By.id("btnSave")).click();
        Thread.sleep(5000);

        //Search Added User
        driver.findElement(By.name("searchSystemUser[userName]")).sendKeys(EnteredValue);
        driver.findElement(By.id("searchBtn")).click();

        //Delete Added User
        driver.findElement(By.name("chkSelectRow[]")).click();
        driver.findElement(By.id("btnDelete")).click();
        driver.findElement(By.id("dialogDeleteBtn")).click();

    }


    @BeforeTest
    public void LaunchBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterTest
    public void CloseBrowser() {
         driver.quit();
    }


}

