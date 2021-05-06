package com.orangehr;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

/**
 * Created by pu00160 on 4/16/2021.
 */
public class Reading_PropertiesFile_Json {

    @Test
    public static void Flight_Login() throws IOException, ParseException, InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        //Creating a JSON parser Object
        JSONParser jsonparser = new JSONParser();
        //Parsing the content of the JSON file
        JSONObject jsonobj = (JSONObject) jsonparser.parse(new FileReader(System.getProperty("user.dir")+"\\src\\test\\resource\\ObjectRepository.json"));
        //Reading data from the JSON file
        String url=(String) jsonobj.get("URL");
        String uname=(String) jsonobj.get("iUserName");
        String upass=(String) jsonobj.get("iUserPass");
        String btnLogin=(String) jsonobj.get("bLogin");

        driver.get((String) jsonobj.get("URL"));
        Thread.sleep(5000);

        driver.findElement(By.id((String) jsonobj.get("iUserName"))).sendKeys("Admin");
        driver.findElement(By.id((String) jsonobj.get("iUserPass"))).sendKeys("admin123");
        driver.findElement(By.id((String) jsonobj.get("bLogin"))).click();
        driver.quit();
    }
}
