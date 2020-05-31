package com.miniiinstabot.manager;

import org.openqa.selenium.WebDriver;

public class BrowserController {

    private WebDriver webDriver;
    public void gotoURL(WebDriver driver, String url) {
        this.webDriver = driver;
        this.webDriver.navigate().to(url);
    }
}
