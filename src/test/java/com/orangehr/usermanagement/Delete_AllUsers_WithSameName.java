package com.orangehr.usermanagement;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by pu00160 on 4/15/2021.
 */
public class Delete_AllUsers_WithSameName {
    WebDriver driver;

    @BeforeTest
    public void Login_HRM() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        // create Edge instance and maximize it
        driver = new ChromeDriver();
        //driver.manage().window().maximize();
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
        Thread.sleep(3000);
        driver.findElement(By.name("txtUsername")).sendKeys("Admin");
        driver.findElement(By.name("txtPassword")).sendKeys("admin123");
        driver.findElement(By.id("btnLogin")).click();
        String ActElement = driver.findElement(By.linkText("Dashboard")).getText();
        String ExpElement = "Dashboard";
        Assert.assertEquals(ActElement, ExpElement);
        System.out.println(ActElement);
    }

    @Test(priority = 1)
    public void Employeelist() throws InterruptedException {

        WebElement Admin = driver.findElement(By.id("menu_admin_viewAdminModule"));
        Actions action = new Actions(driver);
        action.moveToElement(Admin).build().perform();
        WebElement user = driver.findElement(By.linkText("User Management"));
        action.moveToElement(user).build().perform();
        driver.findElement(By.xpath("//a[contains(text(),'Users')]")).click();

    }

    @Test(priority = 2)
    public void Add_Employee() throws InterruptedException {
        driver.findElement(By.name("btnAdd")).click();

        // Enter all the mandatory Fields
        Select SelectPass = new Select(driver.findElement(By.id("systemUser_userType")));
        // SelectPass.selectByValue("1");
        SelectPass.selectByVisibleText("Admin");
        // SelectPass.selectByIndex(0);
        driver.findElement(By.id("systemUser_employeeName_empName")).sendKeys("Fiona Grace");
        driver.findElement(By.id("systemUser_userName")).sendKeys("Shashi012");
        driver.findElement(By.id("systemUser_password")).sendKeys("Password@123");
        driver.findElement(By.id("systemUser_confirmPassword")).sendKeys("Password@123");
        Thread.sleep(5000);
        driver.findElement(By.id("btnSave")).click();
        Thread.sleep(5000);
        WebElement cellactive = driver.findElement(By.linkText("Shashi012"));
        String value = cellactive.getText();
        System.out.println("Cell value is : " + value);
        Assert.assertEquals("AbhiDixit011", value);
        Thread.sleep(3000);
    }

    @Test(priority = 3)
    public void Delete_Employees() throws InterruptedException {
        //*[@id="resultTable"]/tbody/tr[1]/td[2]
        List<WebElement> rows=driver.findElements(By.xpath("//*[@id='resultTable']/tbody/tr"));
        int rowsLength=rows.size();
        System.out.println(rowsLength);
        String beforXpath="//*[@id='resultTable']/tbody/tr[";
        String AfterXpath="]/td[2]";
        for(int i=1; i<=rowsLength;i++)
        {
            String name=driver.findElement(By.xpath(beforXpath + i + AfterXpath)).getText();
            System.out.println(name);
            if(name.toLowerCase().contains("sha"))
            {
                driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr["+i+"]/td[1]/input")).click();

            }
        }
        driver.findElement(By.id("btnDelete")).click();
        driver.findElement(By.id("dialogDeleteBtn")).click();
    }
/*	@Test(priority = 4)
	public void Delete_Employees_WithXpath() throws InterruptedException {
	 List<WebElement> element = (List<WebElement>) driver.findElementsByXPath("//td/a[contains(text(),'"+username+"')]");
     int length = element.size();
     String beforXpath="//*[@id='resultTable']/tbody/tr[";
     String AfterXpath="]/td[2]";
     for(int i=1; i<=length;i++)
     {
         driver.findElementByXPath("//td/a[contains(text(),'"+username+"')]//parent::td/../td/input").click();
             driver.findElement(By.id("btnDelete")).click();
             driver.findElement(By.id("dialogDeleteBtn")).click();
         }
	}*/

    @AfterTest
    public void CloseBrowser() {
        driver.quit();
    }
}
