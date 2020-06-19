package com.miniiinstabot.utils;

import com.miniiinstabot.model.UserAuthModel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

    public static String imageToBase(String path) throws IOException {
        String filePath = path;

        byte[] fileContent = FileUtils.readFileToByteArray(new File(filePath));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);

        return "data:image/png;base64,"+encodedString;
    }

    public List<UserAuthModel> getUserAuthList(List<String> txt) {

        List<UserAuthModel> userAuthModels = new ArrayList<>();
        userAuthModels.clear();
        for (String line : txt) {
            if (line.contains("-")) {
                String[] s = line.split("-");
                if (s[0].contains("Login: ") && s[1].contains("Password: ")) {

                    String uId = s[0].replace("Login: ", "");
                    String pass = s[1].replace("Password: ", "");

                    userAuthModels.add(new UserAuthModel(uId.trim(), pass.trim()));

                }
            }
        }

        return userAuthModels;

    }

    public void waitForLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }

    public static ExpectedCondition<WebElement> visibilityofelementlocated(final By by) {
        return new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement element = driver.findElement(by);
                return element.isDisplayed() ? element : null;
            }
        };
    }

    public boolean isElementPresent(WebDriver driver, By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);

            WebElement searchButton = wait.until(
                    visibilityOfElementLocated(by));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
