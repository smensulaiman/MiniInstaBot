package com.miniiinstabot.manager;

import com.miniiinstabot.gui.MainDashboard;
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
    
    WebPageManager webPageManager = new WebPageManager();
    private Utils utils = new Utils();
    private WebDriver driver;

    public void openLogInUrl(PageLoadInterface pageLoadInterface) {
        Thread firstTimeLogInLoad = new Thread(new Runnable() {
            @Override
            public void run() {

                new BrowserController().gotoURL(driver, Constants.BASE_URL_INSTA);
                try {
                    sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainDashboard.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        firstTimeLogInLoad.start();
    }

    private void login(List<UserAuthModel> userAuthModels) {
        int attempt = 1;
        //onResponse("Attempt : " + attempt);
        //logInSingleUser(userAuthModels.get(0));
    }

    private void logInSingleUser(WebDriver driver, UserAuthModel userAuthModel) {
        if (driver.getTitle().contains(Constants.TITLE_LOGIN)) {

            try {
                driver.findElement(By.xpath(Constants.XPATH_UID)).sendKeys(userAuthModel.getEmail());
                driver.findElement(By.xpath(Constants.XPATH_PASS)).sendKeys(userAuthModel.getPassword());
                sleep(500);
                driver.findElement(By.xpath(Constants.XPATH_LINBTN)).click();
                utils.waitForLoad(driver);
                sleep(2000);
                if (driver.getPageSource().contains("We Detected An Unusual Login Attempt")) {
                    //onResponse("Suspicious Login Attempt");
                    driver.findElement(By.xpath(Constants.XPATH_LOUTBTN)).click();
                    sleep(2000);
                    //login(userAuthModels, userIndex++);
                } else if (driver.getPageSource().contains("Sorry, your password was incorrect. Please double-check your password.") || driver.getPageSource().contains("The username you entered doesn't belong to an account. Please check your username and try again.")) {
                    //onResponse("Sorry, your username or password was incorrect. Please double-check.");
                    openLogInUrl(new PageLoadInterface() {
                        @Override
                        public void onPageLoad() {
                            try {
                                sleep(2000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(MainDashboard.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                } else {
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        } else {
            openLogInUrl(new PageLoadInterface() {
                @Override
                public void onPageLoad() {
                    try {
                        sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainDashboard.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }
    }

}
