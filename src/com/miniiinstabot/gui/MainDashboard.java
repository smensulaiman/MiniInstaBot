package com.miniiinstabot.gui;

import com.miniiinstabot.database.QueryHelper;
import com.miniiinstabot.interfaces.LogInInterface;
import com.miniiinstabot.utils.Constants;
import com.miniiinstabot.interfaces.ResponseInterface;
import com.miniiinstabot.manager.BrowserController;
import com.miniiinstabot.manager.DriverManager;
import com.miniiinstabot.manager.WebPageManager;
import com.miniiinstabot.model.UserAuthModel;
import com.miniiinstabot.scraper.InstagramScraperManager;
import com.miniiinstabot.utils.Utils;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.DefaultCaret;
import javax.swing.text.html.HTMLDocument;
import me.postaddict.instagram.scraper.model.Account;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainDashboard extends javax.swing.JFrame implements ResponseInterface {

    private DefaultTableModel tableModel;
    private WebDriver driver;
    private Thread firstTimeLogInLoad;
    private Utils utils;
    private WebPageManager webPageManager;
    private Wait<WebDriver> wait;
    private List<UserAuthModel> userAuthModels;

    public MainDashboard() {
        initComponents();
        initTable();

        chkPostComment.setSelected(true);
        panelMenu.setVisible(false);

        utils = new Utils();
        userAuthModels = new ArrayList<>();

        try {
            loadWebDriver();
        } catch (Exception e) {
            onResponse(e.getMessage());
        }

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                e.getWindow().dispose();

                try {
                    driver.close();
                    driver.quit();
                } catch (Exception ex) {
                    //ex.printStackTrace();
                }

                if (firstTimeLogInLoad != null) {
                    firstTimeLogInLoad.stop();
                }
            }

            public void windowOpened(java.awt.event.WindowEvent e) {
                firstTimeLogInLoad = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new BrowserController().gotoURL(driver, Constants.BASE_URL_INSTA);
                        if (driver.getTitle().contains(Constants.TITLE_LOGIN)) {
                            onResponse("Log In page loaded....");
                            try {
                                Robot robot = new Robot();
                                //robot.keyPress(Ke);
                            } catch (AWTException ex) {
                                ex.printStackTrace();
                            }

                            firstTimeLogInLoad.stop();
                        }
                    }
                });
                firstTimeLogInLoad.start();
            }
        });
        DefaultCaret caret = (DefaultCaret) txtResult.getCaret();
        caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);
    }

    public void initTable() {
        txtResult.append("\nPreparing header\n");

        JTableHeader tableHeader = tableUsers.getTableHeader();
        tableHeader.setForeground(Color.decode("#4CAF50"));

        tableModel = (DefaultTableModel) tableUsers.getModel();
        tableModel.setColumnCount(4);

        tableUsers.getColumnModel().getColumn(0).setHeaderValue("USERID");
        tableUsers.getColumnModel().getColumn(1).setHeaderValue("PASSWORD");
        tableUsers.getColumnModel().getColumn(2).setHeaderValue("PROCESS");
        tableUsers.getColumnModel().getColumn(3).setHeaderValue("RESULT");

        txtResult.append("Loaded Successfully\n");
    }

    public void loadWebDriver() throws InterruptedException {
        //System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "driver\\geckodriver.exe");
        DriverManager driverManager = new DriverManager();
        driverManager.setDriverInterface(this);

        driver = driverManager.getFirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);

        onResponse("Redirecting to Instagram login :" + Constants.BASE_URL_INSTA);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        chkPostComment = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jCheckBox2 = new javax.swing.JCheckBox();
        txtPassword = new javax.swing.JTextField();
        txtUsername = new javax.swing.JTextField();
        btnEnter = new javax.swing.JButton();
        tabbedPanel = new javax.swing.JTabbedPane();
        tabOnePannel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableUsers = new javax.swing.JTable();
        txtDirectUrl = new javax.swing.JTextField();
        txtLogInStatus = new javax.swing.JLabel();
        panelMenu = new javax.swing.JPanel();
        btnRefresh = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnPlay = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnStop = new javax.swing.JButton();
        tabTwoPannel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtStreet = new javax.swing.JTextField();
        btnScrap = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtResult = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("INSTAGRAM AUTO COMMENT BOT v1.0");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Instagram Bot");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jCheckBox1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setText("#Hash Tag");
        jCheckBox1.setEnabled(false);
        jPanel1.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 259, 95, -1));

        chkPostComment.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        chkPostComment.setForeground(new java.awt.Color(255, 255, 255));
        chkPostComment.setText("Post Comment");
        jPanel1.add(chkPostComment, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 287, -1, -1));

        jCheckBox3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jCheckBox3.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox3.setText("Post Search");
        jCheckBox3.setEnabled(false);
        jPanel1.add(jCheckBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 343, -1, -1));

        jCheckBox4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jCheckBox4.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox4.setText("Post Like");
        jCheckBox4.setEnabled(false);
        jPanel1.add(jCheckBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 315, -1, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Select Features");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 219, -1, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 247, 181, 5));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 375, 181, 1));

        jLabel3.setBackground(new java.awt.Color(102, 102, 102));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Copyright : MiniideA");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 570, 150, 20));

        jTextField1.setForeground(new java.awt.Color(153, 153, 153));
        jTextField1.setText("Proxy");
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 97, 30));

        jTextField2.setForeground(new java.awt.Color(153, 153, 153));
        jTextField2.setText("Port");
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 58, 30));

        jCheckBox2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jCheckBox2.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox2.setText("Enable Proxy");
        jPanel1.add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        txtPassword.setForeground(new java.awt.Color(102, 102, 102));
        txtPassword.setText("enter password");
        txtPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPasswordFocusLost(evt);
            }
        });
        jPanel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 72, 161, -1));

        txtUsername.setForeground(new java.awt.Color(102, 102, 102));
        txtUsername.setText("enter username");
        txtUsername.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUsernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUsernameFocusLost(evt);
            }
        });
        jPanel1.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 46, 161, -1));

        btnEnter.setBackground(new java.awt.Color(255, 255, 255));
        btnEnter.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnEnter.setForeground(new java.awt.Color(0, 204, 51));
        btnEnter.setText("Enter");
        btnEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnterActionPerformed(evt);
            }
        });
        jPanel1.add(btnEnter, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 160, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 600));

        tabbedPanel.setBackground(new java.awt.Color(0, 204, 255));

        tabOnePannel.setBackground(new java.awt.Color(255, 255, 255));
        tabOnePannel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tableUsers);

        tabOnePannel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 650, 220));

        txtDirectUrl.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtDirectUrl.setForeground(new java.awt.Color(153, 153, 153));
        txtDirectUrl.setText("https://www.instagram.com/tv/CAoPI_ZHeEp/?utm_source=ig_web_copy_link");
        tabOnePannel.add(txtDirectUrl, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, -1));

        txtLogInStatus.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtLogInStatus.setForeground(new java.awt.Color(255, 51, 51));
        txtLogInStatus.setText("Not Connected");
        tabOnePannel.add(txtLogInStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, -1, -1));

        panelMenu.setBackground(new java.awt.Color(255, 255, 255));
        panelMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh.png"))); // NOI18N
        btnRefresh.setBorder(null);
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        panelMenu.add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, 70));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 204, 153));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("REFRESH");
        panelMenu.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 100, 20));

        btnPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/play.png"))); // NOI18N
        btnPlay.setBorder(null);
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });
        panelMenu.add(btnPlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 100, 70));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 204, 153));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("START");
        panelMenu.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 100, 20));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 204, 153));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("STOP");
        panelMenu.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, 100, 20));

        btnStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/stop.png"))); // NOI18N
        btnStop.setBorder(null);
        panelMenu.add(btnStop, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 100, 70));

        tabOnePannel.add(panelMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 650, 100));

        tabbedPanel.addTab("Dashboard", tabOnePannel);

        tabTwoPannel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 150, 30));

        btnSearch.setText("SEARCH");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel2.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 150, 30));

        tabTwoPannel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 175, 122));

        jPanel3.setBackground(new java.awt.Color(0, 204, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(txtStreet, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 150, 30));

        btnScrap.setText("SCRAP");
        btnScrap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScrapActionPerformed(evt);
            }
        });
        jPanel3.add(btnScrap, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 150, 30));

        tabTwoPannel.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 170, 120));

        tabbedPanel.addTab("Scrapper", tabTwoPannel);

        getContentPane().add(tabbedPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 650, 370));

        txtResult.setBackground(new java.awt.Color(102, 102, 102));
        txtResult.setColumns(20);
        txtResult.setForeground(new java.awt.Color(255, 255, 255));
        txtResult.setRows(5);
        txtResult.setText("Please wait...");
        jScrollPane2.setViewportView(txtResult);
        txtResult.getAccessibleContext().setAccessibleName("");

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 370, 650, 230));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        try {
            // TODO add your handling code here:
            String tag = txtSearch.getText().toString().trim();
            onResponse("\n\nSearchinr for : " + tag + ".......................................");

            InstagramScraperManager manager = new InstagramScraperManager();
            Account account = manager.getAccountByUsername(tag);

            onResponse("\nID : " + account.getId()
                    + "\nName : " + account.getFullName()
                    + "\nUser Name : " + account.getUsername()
                    + "\nTotal Followers : " + account.getFollowedBy()
                    + "\nBiography : " + account.getBiography());

        } catch (IOException ex) {
            onResponse(ex.getMessage());
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnterActionPerformed
        // TODO add your handling code here:
        if (txtUsername.getText().trim().toLowerCase().equals("admin")) {
            if (txtPassword.getText().trim().toLowerCase().equals("admin")) {
                txtLogInStatus.setVisible(false);
                panelMenu.setVisible(true);
            } else {
                onResponse("Incorrect Password!!!");
            }
        } else {
            onResponse("Incorrect Username!!!");
        }
    }//GEN-LAST:event_btnEnterActionPerformed

    private void txtUsernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsernameFocusLost
        // TODO add your handling code here:
        if (txtUsername.getText().toString().trim().equals("")) {
            txtUsername.setText("enter username");
        }
    }//GEN-LAST:event_txtUsernameFocusLost

    private void txtPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPasswordFocusLost
        // TODO add your handling code here:
        if (txtPassword.getText().toString().trim().equals("")) {
            txtPassword.setText("enter password");
        }
    }//GEN-LAST:event_txtPasswordFocusLost

    private void txtUsernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsernameFocusGained
        // TODO add your handling code here:
        if (txtUsername.getText().toString().trim().equals("enter username")) {
            txtUsername.setText("");
        }
    }//GEN-LAST:event_txtUsernameFocusGained

    private void txtPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPasswordFocusGained
        // TODO add your handling code here:
        if (txtPassword.getText().toString().trim().equals("enter password")) {
            txtPassword.setText("");
        }
    }//GEN-LAST:event_txtPasswordFocusGained

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        onResponse("Connecting to databse...");
        tableModel.setRowCount(0);

        userAuthModels.clear();

        QueryHelper queryHelper = new QueryHelper();

        if (queryHelper.getAllUsers(userAuthModels)) {

            Iterator<UserAuthModel> it = userAuthModels.iterator();
            UserAuthModel model;
            while (it.hasNext()) {
                model = it.next();
                tableModel.addRow(new Object[]{model.getEmail(), model.getPassword(), "Enque"});
                onResponse(model.getEmail());
            }

        } else {
            onResponse("No User Found...");
        }

    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        // TODO add your handling code here:
        onResponse("Started................");

        try {

            if (userAuthModels != null) {
                if (userAuthModels.size() > 0) {
                    webPageManager = new WebPageManager();
                    webPageManager.logInSingleUser(driver, userAuthModels.get(0), new LogInInterface() {
                        @Override
                        public void onSuccess() {
                            onResponse("Success!!!");
                        }

                        @Override
                        public void onFaild(String message) {
                            onResponse(message);
                        }
                    });
                }
            }

        } catch (Exception e) {
            onResponse(e.getMessage());
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnPlayActionPerformed

    Thread scrapThread;
    XSSFWorkbook workbook;
    XSSFSheet spreadsheet;
    FileOutputStream out;

    private void btnScrapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScrapActionPerformed
        // TODO add your handling code here:
        scrapThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    workbook = new XSSFWorkbook();
                    spreadsheet = workbook.createSheet("Address");
                    spreadsheet.setColumnWidth(0, 8000);
                    spreadsheet.setColumnWidth(1, 8000);
                    spreadsheet.setColumnWidth(2, 8000);
                    //This data needs to be written (Object[])
                    empinfo = new TreeMap< String, Object[]>();
                    out = new FileOutputStream(new File("createBlankWorkBook.xlsx"));
                    
                    
                    scrapData(1, Integer.parseInt(String.valueOf(txtStreet.getText())));
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        scrapThread.start();
    }//GEN-LAST:event_btnScrapActionPerformed

    //Create row object
    XSSFRow row;
    Map< String, Object[]> empinfo;
    int rowIndex = 1;

    public void scrapData(int from, int to) {
        if (to >= from) {
            new BrowserController().gotoURL(driver, "https://cep.guiamais.com.br/busca/manaus-am?page=" + from);

            WebElement table = driver.findElement(By.xpath("/html/body/div[2]/div[6]/div[1]/table"));
            List<WebElement> allRows = table.findElements(By.tagName("tr"));

            for (int i = 1; i < allRows.size(); i++) {

                final String LOGRADOURO = driver.findElement(By.xpath("/html/body/div[2]/div[6]/div[1]/table/tbody/tr[" + i + "]/td[1]")).getText();
                final String BAIRRO = driver.findElement(By.xpath("/html/body/div[2]/div[6]/div[1]/table/tbody/tr[" + i + "]/td[2]")).getText();
                final String CEP = driver.findElement(By.xpath("/html/body/div[2]/div[6]/div[1]/table/tbody/tr[" + i + "]/td[5]")).getText();

                empinfo.put(String.valueOf(rowIndex), new Object[]{LOGRADOURO, BAIRRO, CEP});
                rowIndex++;
                //System.out.println(LOGRADOURO + "\t" + BAIRRO + "\t" + CEP);
            }

            from++;
            scrapData(from, to);

        } else {
            try {

                Set< String> keyid = empinfo.keySet();
                int rowid = 0;

                for (String key : keyid) {
                    row = spreadsheet.createRow(rowid++);
                    row.setHeight((short) 600);
                    Object[] objectArr = empinfo.get(key);
                    int cellid = 0;

                    for (Object obj : objectArr) {
                        Cell cell = row.createCell(cellid++);
                        cell.setCellValue((String) obj);
                    }
                }

                workbook.write(out);
                out.close();
                System.out.println("createworkbook.xlsx written successfully");
                if (scrapThread.isAlive()) {
                    scrapThread.stop();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnter;
    private javax.swing.JButton btnPlay;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnScrap;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnStop;
    private javax.swing.JCheckBox chkPostComment;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel tabOnePannel;
    private javax.swing.JPanel tabTwoPannel;
    private javax.swing.JTabbedPane tabbedPanel;
    private javax.swing.JTable tableUsers;
    private javax.swing.JTextField txtDirectUrl;
    private javax.swing.JLabel txtLogInStatus;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextArea txtResult;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtStreet;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onResponse(String message) {
        String time = new SimpleDateFormat("hh:mm:ss").format(new Date());
        txtResult.append(time + " --> " + message + "\n");
    }
}
