package com.miniiinstabot.manager;

import com.miniiinstabot.gui.MainDashboard;
import com.miniiinstabot.interfaces.CommentInterface;
import com.miniiinstabot.interfaces.LogInInterface;
import com.miniiinstabot.interfaces.PageLoadInterface;
import com.miniiinstabot.model.UserAuthModel;
import com.miniiinstabot.utils.Constants;
import com.miniiinstabot.utils.Utils;
import static java.lang.Thread.sleep;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebPageManager {

    private Utils utils = new Utils();
    private WebDriver driver;

    public void openLogInUrl(PageLoadInterface pageLoadInterface) {
        Thread firstTimeLogInLoad = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    new BrowserController().gotoURL(driver, Constants.BASE_URL_INSTA);
                    sleep(2000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        firstTimeLogInLoad.start();
    }

    public void logInSingleUser(WebDriver driver, UserAuthModel userAuthModel, LogInInterface logInInterface) {
        System.out.println("Log in called");
        try {
            driver.findElement(By.xpath(Constants.XPATH_UID)).sendKeys(userAuthModel.getEmail());
            driver.findElement(By.xpath(Constants.XPATH_PASS)).sendKeys(userAuthModel.getPassword());
            sleep(500);
            driver.findElement(By.xpath(Constants.XPATH_LINBTN)).click();
            utils.waitForLoad(driver);
            sleep(2000);

            if (isPresent(driver, Constants.XPATH_DIALOG_ONE)) {
                System.out.println("Alert Dialog displayed");
                driver.findElement(By.xpath(Constants.XPATH_DIALOG_ONE)).click();
                sleep(1000);
            }

            if (isPresent(driver, Constants.XPATH_DIALOG_TWO)) {
                System.out.println("Notification Dialog displayed");
                driver.findElement(By.xpath(Constants.XPATH_DIALOG_TWO)).click();
                sleep(1000);

                logInInterface.onSuccess();
            }

        } catch (Exception e) {
            e.printStackTrace();
            logInInterface.onFaild(e.getMessage());
        }
    }

    public int gotToPostLink(WebDriver driver, String link) {

        new BrowserController().gotoURL(driver, link);
        return 1;
    }

    public int makeComment(WebDriver driver, String comment,CommentInterface commentInterface) {

        try{
        
        if (isPresent(driver, By.cssSelector(Constants.CSS_COMMENT))) {
            System.out.println("Comment Field Found.");  
            
            driver.findElement(By.cssSelector(Constants.CSS_COMMENT)).click();
            driver.findElement(By.cssSelector(Constants.CSS_COMMENT)).sendKeys(comment);

            if (isPresent(driver, Constants.XPATH_BTN_POST)) {
                driver.findElement(By.xpath(Constants.XPATH_BTN_POST)).click();
                commentInterface.onComment();
            }
        }
        }catch(Exception e){
            e.printStackTrace();
        }

        return 1;
    }

    public boolean isPresent(WebDriver driver, String xpath) {
        Utils utils = new Utils();
        Boolean display = utils.isElementPresent(driver, By.xpath(xpath));
        return display;
    }

    public boolean isPresent(WebDriver driver, By by) {
        Utils utils = new Utils();
        Boolean display = utils.isElementPresent(driver, by);
        return display;
    }

    public boolean validateError(String message) {

        if (message.contains("Sorry, your password was incorrect. Please double-check your password.")
                || message.contains("Sorry, your password was incorrect. Please double-check your password.")
                || message.contains("The username you entered doesn't belong to an account. Please check your username and try again.")) {
            return false;
        }
        return true;
    }

}
