package com.orangehr;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

/**
 * Created by pu00160 on 4/20/2021.
 */
public class JavaScriptExecutor_ScrollDownElement_Example {

    ChromeDriver driver;
    @Test
    public void ScrollElement() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Launch the application
        driver.get("https://stackoverflow.com/");
        Thread.sleep(5000);
        WebElement Element = driver.findElementByLinkText("discover Teams");
        Thread.sleep(5000);
        //This will scroll the page Horizontally till the element is found
        js.executeScript("arguments[0].scrollIntoView();", Element);
        Thread.sleep(5000);
        Element.click();
        driver.quit();
    }
}
