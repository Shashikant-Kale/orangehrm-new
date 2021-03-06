package com.orangehr.usermanagement;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Random;

/**
 * Created by pu00160 on 4/15/2021.
 */
public class DeleteAddedUserFromTable {
    ChromeDriver driver;
    @BeforeTest
    public void LaunchBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
    }
    @Test(priority=1)
    // This is for Sign On Functionality
    public void Sign_On() throws InterruptedException {
        Thread.sleep(10*1000);
        driver.findElement(By.name("txtUsername")).sendKeys("Admin");
        driver.findElement(By.name("txtPassword")).sendKeys("admin123");
        driver.findElement(By.id("btnLogin")).click();
        String Element = driver.findElement(By.linkText("Dashboard")).getText();
        System.out.println(Element);

    }

    @Test(priority=2)
    public void AddUsers_Page() {
        WebElement admin = driver.findElementById("menu_admin_viewAdminModule");
        Actions action = new Actions(driver);
        action.moveToElement(admin).build().perform();
        WebElement usermanagement = driver.findElementByLinkText("User Management");
        action.moveToElement(usermanagement).build().perform();
        driver.findElementByLinkText("Users").click();
        //User Click on Add Button to add users
        driver.findElementById("searchBtn").click();
        driver.findElementById("btnAdd").isDisplayed();

    }
    @Test(priority=3)
    public void VerifyAddedUser() throws InterruptedException {
        driver.findElementById("btnAdd").click();

        Select SelectPass = new Select(driver.findElementById("systemUser_userType"));
        //SelectPass.selectByValue("1");
        //SelectPass.selectByIndex(0);
        SelectPass.selectByVisibleText("Admin");
        driver.findElementById("systemUser_employeeName_empName").sendKeys("Fiona Grace");

        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(1000);
        driver.findElementById("systemUser_userName").sendKeys("abhi" + randomInt);
        //String ExpUserName=driver.findElementById("systemUser_userName").getText();
        driver.findElementById("systemUser_password").sendKeys("admin123");
        driver.findElementById("systemUser_confirmPassword").sendKeys("admin123");
        Thread.sleep(5000);
        driver.findElementById("btnSave").click();
        //Verify the Added User in WebTable
        Thread.sleep(2000);
        String ExpUserName = "abhi" + randomInt;
        driver.navigate().refresh();
        Thread.sleep(2000);
        WebElement cellIneed = driver.findElementByXPath("//*[text()='"+ ExpUserName +"']");
        String ActualUserName = cellIneed.getText();
        System.out.println("Cell value is : " + ActualUserName);
        Assert.assertEquals(ExpUserName, ActualUserName);

        //Delete User from Search List

        //driver.findElementByXPath("//a[text()='"+ExpUserName+"']/parent::td/preceding-sibling::td/input").click();
        //driver.findElementByXPath("//td/a[text()='"+ExpUserName+"']//parent::td/../td/input").click();
        driver.findElementByXPath("//td/a[text()='"+ExpUserName+"']//parent::td/../td[text()='Admin']").isDisplayed();
        driver.findElementByXPath("//td/a[text()='"+ExpUserName+"']//parent::td/../td[text()='Admin']/../td/input").click();;
        Thread.sleep(2000);
        driver.findElementById("btnDelete").click();
        driver.findElementById("dialogDeleteBtn").click();

    }

    @AfterTest
    public void CloseBrowser()
    {
        driver.quit();
    }
}
