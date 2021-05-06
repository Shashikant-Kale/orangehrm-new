package com.orangehr;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by pu00160 on 4/16/2021.
 */
public class Capture_Screenshot_Example {

    ChromeDriver driver;
    //Absolute Path, rather use the relative path
    //String filePath_failure = "D:\\F Drive\\Selenium Training Data\\workspace\\Maven_Selenium_WebDriver_4\\Screenshot_Failure";
    String filePath = System.getProperty("user.dir");

    String Relativepath_failure = filePath+"\\Screenshot_Failure";
    String filePath_Success = filePath+"\\Screenshot_Success";

    @Test(priority=2)
    public void OrangeHRM_Login() throws Exception
    {
        System.out.println(filePath);

        Thread.sleep(5000);

        driver.findElement(By.name("txtUsername")).sendKeys("Admin");
        driver.findElement(By.name("txtPassword")).sendKeys("admin123");
        driver.findElement(By.id("btnLogin")).click();
        driver.findElement(By.linkText("Dashboardd")).isDisplayed();
    }

    @Test(priority=3)
    public void OrangeHRM_Logout() throws Exception
    {
        driver.findElement(By.id("welcome")).click();
        Thread.sleep(2000);
        driver.findElement(By.linkText("Logout")).click();
    }

    @Test(priority=1)
    public void LaunchBrowser()

    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
    }

    @AfterMethod
    public void CaptureScreenShot(ITestResult result) throws IOException
    {
        if ( ITestResult.FAILURE==result.getStatus())
        {
            File Browserscreenshot= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            //FileUtils.copyFile(Browserscreenshot, new File(Relativepath_failure+"\\Login.png"));
            FileUtils.copyFile(Browserscreenshot, new File(Relativepath_failure +"\\"+result.getName()+"_"+System.currentTimeMillis() + ".png"));
        }else if ( ITestResult.SUCCESS==result.getStatus())
        {
            File Browserscreenshot= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            //FileUtils.copyFile(Browserscreenshot, new File(filePath_Success+"\\Login.png"));
            FileUtils.copyFile(Browserscreenshot, new File(filePath_Success +"\\"+result.getName()+"_"+System.nanoTime() + ".png"));
        }


    }

    @AfterTest
    public void CloseBrowser() {
        driver.quit();
    }
}
