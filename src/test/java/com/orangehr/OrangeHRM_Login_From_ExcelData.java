package com.orangehr;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by pu00160 on 4/16/2021.
 */
public class OrangeHRM_Login_From_ExcelData extends OrangeHR_TestData{

    ChromeDriver driver;

    /*@Test
    public void Sign_On() throws IOException, InterruptedException {


            ReadExcel excel = new ReadExcel();
            String RelativePath = System.getProperty("user.dir");
            //Object[][] testObjArray = excel.getExcelData("C:\\Users\\adixit\\git\\abhikdixit-Maven_Selenium_WebDriver_4\\Maven_Selenium_WebDriver_4\\OrangeHRM_TestData.xlsx","SignIn");
            Object[][] testObjArray = excel.getExcelData("C:\\Trainings\\Selenium_Training\\orangehr\\TestData.xlsx", "Login");
            System.out.println(testObjArray);

            System.out.println("+++++++++ " + testObjArray[0][0] + ":" + testObjArray[0][1]);

            driver.findElement(By.name("txtUsername")).sendKeys((String) testObjArray[0][0]);
            driver.findElement(By.name("txtPassword")).sendKeys((String) testObjArray[0][1]);
            driver.findElement(By.id("btnLogin")).click();
            // ----------------To Verify Logout Function without using
            driver.findElement(By.id("welcome")).click();
            Thread.sleep(5000);
            driver.findElement(By.linkText("Logout")).click();
            driver.findElement(By.id("logInPanelHeading")).isDisplayed();

    }*/

    @Test(dataProvider = "LoginExcelData")
    public void Sign_On(String uname, String password) throws InterruptedException {

        driver.findElement(By.name("txtUsername")).sendKeys(uname);
        driver.findElement(By.name("txtPassword")).sendKeys(password);
        driver.findElement(By.id("btnLogin")).click();
        // ----------------To Verify Logout Function without using
        driver.findElement(By.id("welcome")).click();
        Thread.sleep(5000);
        driver.findElement(By.linkText("Logout")).click();
        driver.findElement(By.id("logInPanelHeading")).isDisplayed();
    }


    @BeforeTest
    public void LaunchBrowser() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
        Thread.sleep(5000);
    }

    @AfterTest
    public void CloseBrowser(){
        driver.quit();
    }

}
