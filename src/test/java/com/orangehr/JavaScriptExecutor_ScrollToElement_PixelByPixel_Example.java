package com.orangehr;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

/**
 * Created by pu00160 on 4/20/2021.
 */
public class JavaScriptExecutor_ScrollToElement_PixelByPixel_Example {

    ChromeDriver driver;
    @Test
    public void ByPage() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Launch the application
        driver.get("https://stackoverflow.com/");
        Thread.sleep(5000);

        //This will scroll the web page till pixel by pixel.
        js.executeScript("window.scrollTo(0, 1500)");
        Thread.sleep(2000);
        js.executeScript("window.scrollTo(1500, 3000)");
        Thread.sleep(2000);
        js.executeScript("window.scrollTo(3000, 4500)");
        Thread.sleep(2000);
        js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
        Thread.sleep(2000);
        js.executeScript("window.scrollTo(4500, 6000)");
        Thread.sleep(3000);
        //WebElement element = driver.findElementByLinkText("Developer Story");
        //element.click();
        driver.quit();
    }
}
