package com.miniiinstabot.manager;

import javax.naming.Context;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class BrowserController {
    
    private WebDriver webDriver;

    public void gotoURL(WebDriver driver, String url) {
        System.out.println("nevigate");
        this.webDriver = driver;
        this.webDriver.navigate().to(url);
        //this.webDriver.manage().window().maximize();
//        JavascriptExecutor js = (JavascriptExecutor)webDriver;  
//        js.executeScript("scrollBy(0, 5000)"); 
    }
}
