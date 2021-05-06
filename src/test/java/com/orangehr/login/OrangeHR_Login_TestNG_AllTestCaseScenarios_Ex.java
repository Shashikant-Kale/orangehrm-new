package com.orangehr.login;

import com.orangehr.OrangeHR_TestData;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Created by pu00160 on 4/12/2021.
 */
public class OrangeHR_Login_TestNG_AllTestCaseScenarios_Ex extends OrangeHR_TestData {

    WebDriver chDriver;

    @Test(dataProvider="LoginScenario")
    public void LoginAllCases(String uName, String uPassword, String expectedMessage) throws InterruptedException {
        Thread.sleep(10*1000);
        chDriver.findElement(By.name("txtUsername")).clear();
        chDriver.findElement(By.name("txtUsername")).sendKeys(uName);
        chDriver.findElement(By.name("txtPassword")).clear();
        chDriver.findElement(By.name("txtPassword")).sendKeys(uPassword);
        chDriver.findElement(By.name("Submit")).click();

        try {

            Boolean boolDisplayed = chDriver.findElement(By.linkText("Dashboard")).isDisplayed();

            if (boolDisplayed) {
                System.out.println("Successful Login");
                String strCurrDashboardLabel = chDriver.findElement(By.xpath("//h1[contains(text(),'Dashboard')]")).getText();
                Assert.assertEquals(expectedMessage, strCurrDashboardLabel);
                //Logout
                chDriver.findElement(By.partialLinkText("Welcome")).click();
                Thread.sleep(3000);
                chDriver.findElement(By.linkText("Logout")).isDisplayed();
                chDriver.findElement(By.linkText("Logout")).click();
                chDriver.findElement(By.name("Submit")).isDisplayed();
            } else {
                System.out.println("Unsuccessful Login");
                String strInvalidLoginMessage = chDriver.findElement(By.id("spanMessage")).getText();
                Assert.assertEquals(expectedMessage, strInvalidLoginMessage);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeTest
    public void LaunchBrowser() {
        WebDriverManager.chromedriver().setup();
        chDriver = new ChromeDriver();
        chDriver.manage().window().maximize();
        chDriver.get("https://opensource-demo.orangehrmlive.com/");

    }

    @AfterTest
    public void CloseBrowser() {
        chDriver.quit();
    }

}

