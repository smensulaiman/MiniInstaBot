package com.miniiinstabot.scraper;

import com.miniiinstabot.interfaces.ResponseInterface;
import com.miniiinstabot.manager.BrowserController;
import com.miniiinstabot.manager.DriverManager;
import com.miniiinstabot.utils.Utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ZipCodeScrapper {

    private static final String URL = "http://correios.com.br/";

    private List<ZipModel> zipModels;
    private WebDriver driver;
    private List<String> tabs;
    private Wait<WebDriver> wait;
    private Iterator<ZipModel> itr;

    static int listIndex = 0;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        ZipCodeScrapper scrapper = new ZipCodeScrapper();

        if (scrapper.prepareList()) {
            try {
                scrapper.loadWebDriver();
                scrapper.gotoHomePage();
                scrapper.getZip(listIndex++);
                //scrapper.printList();
            } catch (InterruptedException ex) {
                Logger.getLogger(ZipCodeScrapper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void loadWebDriver() throws InterruptedException {

        System.setProperty("webdriver.gecko.driver", "driver\\geckodriver.exe");
        DriverManager driverManager = new DriverManager();
        driverManager.setDriverInterface(new ResponseInterface() {
            @Override
            public void onResponse(String message) {
                gotoHomePage();
            }
        });

        driver = driverManager.getFirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);

    }

    public boolean prepareList() {

        zipModels = new ArrayList<>();
        zipModels.clear();

        try {
            System.out.println("Sart");
            File file = new File("LISTA_LOGRADOURO - COM O CEP- 11H01.xlsx");

            Workbook workbook = WorkbookFactory.create(new FileInputStream(file));
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                Cell cell = row.getCell(3);
                zipModels.add(new ZipModel(String.valueOf(cell), ""));
            }

            itr = zipModels.iterator();

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void gotoHomePage() {
        if (driver != null) {
            new BrowserController().gotoURL(driver, URL);
        }
    }

    private String getZip(int index) throws InterruptedException {

        String address = zipModels.get(index).getAddress();
        
        System.out.println("Got a new address at index "+index+" is "+address);
        
        updateTabs(); 
        driver.switchTo().window(tabs.get(0)); 
        
        if(isPresent(driver, By.id("acesso-busca"))){ 
            
            System.out.println("Zip Search Field Found!!!");
            
            WebElement txtSearch = driver.findElement(By.id("acesso-busca"));
            txtSearch.clear();
            txtSearch.sendKeys(address);
            txtSearch.sendKeys(Keys.ENTER);
            
            updateTabs(); 
            driver.switchTo().window(tabs.get(1)); 
            sleep(2000);
            if(isPresent(driver, "/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/table/tbody/tr[2]/td[4]")){
                System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/table/tbody/tr[2]/td[4]")).getText());
                driver.close();
                getZip(++index);
            }else{
                System.out.println("ZIP EXIST");
                driver.close();
                getZip(++index);
            }
            //driver.close();
            
        }else{
            
            System.out.println("Field not Found!!!");
            gotoHomePage();
            getZip(index);          
        }
        
        return "";
    }

    private void printList() {
        for (ZipManager manager : zipModels) {
            System.out.println(manager.getAddress());
        }
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
    
    public void updateTabs(){
        tabs = new ArrayList<String>(driver.getWindowHandles());  
    }

}
