package com.miniiinstabot.manager;

import com.miniiinstabot.interfaces.ResponseInterface;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager{
    
    private FirefoxDriver firefoxDriver;
    private ChromeDriver chromeDriver;
    
    private ResponseInterface responseInterface;
    
    public void setDriverInterface(ResponseInterface responseInterface){
        this.responseInterface = responseInterface;
    }

    public DriverManager() {
        //this.firefoxDriver = new FirefoxDriver();
        this.chromeDriver = new ChromeDriver();
    }

    public WebDriver getFirefoxDriver() {
        return firefoxDriver;
    }

    public WebDriver getChromeDriver() {
        
        if(responseInterface != null){
            responseInterface.onResponse("Driver Called...");
        }
        
        return chromeDriver;
    }     

}
