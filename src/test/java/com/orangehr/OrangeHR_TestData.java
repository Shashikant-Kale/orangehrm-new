package com.orangehr;

import org.testng.annotations.DataProvider;

/**
 * Created by pu00160 on 4/14/2021.
 */
public class OrangeHR_TestData {

    @DataProvider(name="LoginData")
    public Object[][] getDataForLogin(){
        // Multidimentional object
        // 3x3 or 4x3 or 4x5
        return new Object[][]{
                {"Admin", "admin123"},
                {"kumar", "admin123"},
                {"dixit", "admin123"}
        };
    }

    @DataProvider(name = "LoginScenario")
    public Object[][] getDataforLoginDifferentScenarios() {
        return new Object[][] {
                { "admin", "", "Password cannot be empty"},
                { "", "admin123", "Username cannot be empty" },
                { "AdminWrong", "admin123", "Invalid credentials" },
                { "admin", "admin", "Invalid credentials" },
                { "admin", "admin123", "Dashboard" } };

    }

    @DataProvider(name = "LoginExcelData")
    public Object[][] Authentication() throws Exception{
        ReadExcel excel = new ReadExcel();
        String RelativePath = System.getProperty("user.dir");
        //Object[][] testObjArray = excel.getExcelData(RelativePath+"\\TestData.xlsx","SignIn");
        Object[][] testObjArray = excel.getExcelData("C:\\Trainings\\Selenium_Training\\orangehr\\TestData.xlsx","Login");
        System.out.println(testObjArray);
        return testObjArray;

    }
}
