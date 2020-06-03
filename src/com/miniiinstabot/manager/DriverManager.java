package com.miniiinstabot.manager;

import com.miniiinstabot.interfaces.ResponseInterface;
import java.io.File;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class DriverManager{
    
    private FirefoxDriver firefoxDriver;
    private ChromeDriver chromeDriver;
    
    private ResponseInterface responseInterface;
    
    public void setDriverInterface(ResponseInterface responseInterface){
        this.responseInterface = responseInterface;
    }

    public DriverManager() {
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.addExtension(new File("addons\\multifox.xpi"));
        
        FirefoxOptions opt = new FirefoxOptions();
        opt.setProfile(firefoxProfile);
        
        this.firefoxDriver = new FirefoxDriver(opt);
        //this.chromeDriver = new ChromeDriver();
    }

    public WebDriver getFirefoxDriver() {
        if(responseInterface != null){
            responseInterface.onResponse("Driver Called...");
        }
        return firefoxDriver;
    }

    public WebDriver getChromeDriver() {
        
        if(responseInterface != null){
            responseInterface.onResponse("Driver Called...");
        }
        
        return chromeDriver;
    }     

}
