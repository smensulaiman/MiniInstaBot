package com.miniiinstabot.scraper;

import com.miniiinstabot.interfaces.ResponseInterface;
import com.miniiinstabot.manager.BrowserController;
import com.miniiinstabot.manager.DriverManager;
import com.miniiinstabot.utils.Utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
    private Thread scrapThread;
    private XSSFWorkbook workbook;
    private XSSFSheet spreadsheet;
    private FileOutputStream out;
    private XSSFRow row;
    private Map< String, Object[]> empinfo;
    int rowIndex = 1;

    static int listIndex = 1;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        ZipCodeScrapper scrapper = new ZipCodeScrapper();

        if (scrapper.prepareList()) {
            try {
                scrapper.loadWebDriver();
                scrapper.gotoHomePage();
                scrapper.prepareSheet();
                scrapper.getZip(listIndex++);
                scrapper.printList();
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
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 2);

    }

    public boolean prepareList() {

        zipModels = new ArrayList<>();
        zipModels.clear();

        try {
            System.out.println("Sart...");
            File file = new File("LISTA_LOGRADOURO - COM O CEP- 11H01.xlsx");

            Workbook workbook = WorkbookFactory.create(new FileInputStream(file));
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                Cell cell1 = row.getCell(0);
                Cell cell2 = row.getCell(1);
                Cell cell3 = row.getCell(2);
                Cell cell4 = row.getCell(3);
                Cell cell5 = row.getCell(4);
                zipModels.add(new ZipModel(String.valueOf(cell1),String.valueOf(cell2),String.valueOf(cell3),String.valueOf(cell4),String.valueOf(cell4), "",""));
            }

            System.out.println("List Created...");

            itr = zipModels.iterator();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void gotoHomePage() {
        if (driver != null) {
            new BrowserController().gotoURL(driver, URL);
        }
    }

    public void prepareSheet() {
        try {
            workbook = new XSSFWorkbook();
            spreadsheet = workbook.createSheet("Zip");
            spreadsheet.setColumnWidth(0, 2000);
            spreadsheet.setColumnWidth(1, 8000);
            spreadsheet.setColumnWidth(2, 3000);
            spreadsheet.setColumnWidth(3, 8000);
            spreadsheet.setColumnWidth(4, 8000);
            spreadsheet.setColumnWidth(5, 3000);
            spreadsheet.setColumnWidth(6, 8000);
            empinfo = new TreeMap< String, Object[]>();
            out = new FileOutputStream(new File("excel.xlsx"));
            
            empinfo.put(String.valueOf(rowIndex), new Object[]{"TIPO","DESCRIÇÃO ANTIGA","TIPO NOVO","DESCRIÇÃO NOVA","BAIRRO","CEP","NOME DA RUA"});
            rowIndex++;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private String getZip(int index) throws InterruptedException {
        System.out.println("Index : "+index);
        
        //15293
        
        if(index == 101)
            return "";

        String A = zipModels.get(index).getA();
        String B = zipModels.get(index).getB();
        String C = zipModels.get(index).getC();
        String D = zipModels.get(index).getD();
        String E = zipModels.get(index).getE();
        
        updateTabs();
        driver.switchTo().window(tabs.get(0));

        if (isPresent(driver, By.id("acesso-busca"))) {
            
            WebElement txtSearch = driver.findElement(By.id("acesso-busca"));
            txtSearch.clear();
            txtSearch.sendKeys(D);
            txtSearch.sendKeys(Keys.ENTER);

            updateTabs();
            driver.switchTo().window(tabs.get(1));
            sleep(1000);
            if (isPresent(driver, "/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/table/tbody/tr[2]/td[4]")) {
                //System.out.println(driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/table/tbody/tr[2]/td[4]")).getText());

                WebElement table = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/table"));
                List<WebElement> allRows = table.findElements(By.tagName("tr"));

                int isMatched = 0;

                for (int i = 2; i <= allRows.size(); i++) {
                    String zipAddress = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/table/tbody/tr[" + i + "]/td[1]")).getText();
                    String zipCode = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]/table/tbody/tr[" + i + "]/td[4]")).getText();
                    if (zipCode.substring(0, 2).equals("69")) {
                        System.out.println("zip matched at " + i + " is " + zipCode);
                        empinfo.put(String.valueOf(rowIndex), new Object[]{A,B,C,D,E,zipCode,zipAddress});
                        rowIndex++;
                        isMatched = 1;
                        break;
                    }
                }

                if (isMatched == 0) {
                    empinfo.put(String.valueOf(rowIndex), new Object[]{A,B,C,D,E,"",""});
                    rowIndex++;
                    //System.out.println("no zip matched");
                }

                driver.close();
                getZip(++index);
            } else {
                empinfo.put(String.valueOf(rowIndex), new Object[]{A,B,C,D,E,"",""});
                rowIndex++;
                driver.close();
                getZip(++index);
            }
            //driver.close();

        } else {
            //System.out.println("Field not Found!!!");
            gotoHomePage();
            getZip(index);
        }

        return "";
    }

    private void printList() {
        try {

            Set< String> keyid = empinfo.keySet();
            int rowid = 0;

            for (String key : keyid) {
                row = spreadsheet.createRow(rowid++);
                //row.setHeight((short) 600);
                Object[] objectArr = empinfo.get(key);
                int cellid = 0;

                for (Object obj : objectArr) {
                    Cell cell = row.createCell(cellid++);
                    cell.setCellValue((String) obj);
                }
            }

            workbook.write(out);
            out.close();
            System.out.println("Zip LIst.xlsx written successfully");

        } catch (IOException ex) {
            ex.printStackTrace();
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

    public void updateTabs() {
        tabs = new ArrayList<String>(driver.getWindowHandles());
    }

}
