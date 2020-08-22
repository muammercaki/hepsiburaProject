package com.hepsiburada.utils;


import com.thoughtworks.gauge.AfterSuite;
import com.thoughtworks.gauge.BeforeSuite;
import org.openqa.selenium.WebDriver;

public class Driver {
    public static WebDriver webDriver;
    public static String baseUrl = "https://www.hepsiburada.com/";

    @BeforeSuite
    public void initializeDriver(){
        webDriver=DriverFactory.getDriver();
        webDriver.get(baseUrl);
        //webDriver.manage().deleteAllCookies();
        webDriver.manage().window().maximize();
    }

    @AfterSuite
    public void closeDriver(){
        webDriver.quit();
    }
}
