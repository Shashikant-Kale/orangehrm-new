package com.orangehr.login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by pu00160 on 4/12/2021.
 */
public class OrangeHR_LoginVerification_BakingExp {

    //WebDriver driver;
    ChromeDriver driver;

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

    /*@Test
    public void Login_Successful(){

        //driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.switchTo().frame("login_page");
        driver.findElementByName("fldLoginUserId").sendKeys("1000");
        driver.findElementByXPath("//tbody/tr/td/a/img[1]").click();

        driver.switchTo().defaultContent();
        driver.switchTo().frame("footer");
        //driver.findElement(By.linkText("Terms and Conditions")).click();
        driver.findElementByLinkText("Terms and Conditions").click();



    }*/

    @Test
    public void Login_UnSuccessful_PopupAlert() throws InterruptedException {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.switchTo().frame("login_page");
       // driver.findElementByName("fldLoginUserId").sendKeys("1000");
        driver.findElementByXPath("//tbody/tr/td/a/img[1]").click();

        String ExpAlertTxt = "Customer ID  cannot be left blank.";
        String ActAlertTxt = driver.switchTo().alert().getText();
        Assert.assertEquals(ExpAlertTxt, ActAlertTxt);

        Thread.sleep(5000);

        driver.switchTo().alert().accept();

    }
}

