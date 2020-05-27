package com.miniiinstabot.gui;

import com.miniiinstabot.interfaces.PageLoadInterface;
import com.miniiinstabot.utils.Constants;
import com.miniiinstabot.interfaces.ResponseInterface;
import com.miniiinstabot.manager.BrowserController;
import com.miniiinstabot.manager.DriverManager;
import com.miniiinstabot.model.UserAuthModel;
import com.miniiinstabot.utils.Utils;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.DefaultCaret;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.text.normalizer.UTF16;

/**
 *
 * @author Solaiman
 */
public class MainDashboard extends javax.swing.JFrame implements ResponseInterface {

    private DefaultTableModel tableModel;
    private WebDriver driver;
    private WebElement element;
    private Thread firstTimeLogInLoad;
    private Utils utils;
    private int userIndex = 0;

    private List<UserAuthModel> userAuthModels;

    public MainDashboard() {
        initComponents();
        initTable();
        chkPostComment.setSelected(true);
        utils = new Utils();
        try {
            loadWebDriver();
        } catch (Exception e) {
            onResponse(e.getMessage());
        }

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                if (driver != null) {
                    driver.close();
                    driver.quit();
                }

                if (firstTimeLogInLoad != null) {
                    firstTimeLogInLoad.stop();
                }

                e.getWindow().dispose();
            }

            public void windowOpened(java.awt.event.WindowEvent e) {

                firstTimeLogInLoad = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        new BrowserController().gotoURL(driver, Constants.BASE_URL_INSTA);

                        if (driver.getTitle().contains(Constants.TITLE_LOGIN)) {
                            onResponse("Log In page loaded....");
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
        tableModel.setColumnCount(3);

        tableUsers.getColumnModel().getColumn(0).setHeaderValue("USERID");
        tableUsers.getColumnModel().getColumn(1).setHeaderValue("PASSWORD");
        tableUsers.getColumnModel().getColumn(2).setHeaderValue("STATUS");

        txtResult.append("Loaded Successfully\n");
    }

    public void loadWebDriver() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
        DriverManager driverManager = new DriverManager();
        driverManager.setDriverInterface(this);

        driver = driverManager.getChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        onResponse("Redirecting to Instagram login :" + Constants.BASE_URL_INSTA);
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
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableUsers = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtResult = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        txtAuth = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtUrl = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtComment = new javax.swing.JTextField();
        btnComment = new javax.swing.JButton();
        btnAuth = new javax.swing.JButton();
        btnUrl = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        txtDirectUrl = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("INSTAGRAM AUTO COMMENT BOT v1.0");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 102, 102));

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Instagram Bot");

        jCheckBox1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setText("#Hash Tag");
        jCheckBox1.setEnabled(false);

        chkPostComment.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        chkPostComment.setForeground(new java.awt.Color(255, 255, 255));
        chkPostComment.setText("Post Comment");

        jCheckBox3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jCheckBox3.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox3.setText("Post Search");
        jCheckBox3.setEnabled(false);

        jCheckBox4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jCheckBox4.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox4.setText("Post Like");
        jCheckBox4.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Select Features");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Copyright : MiniideA");

        jTextField1.setForeground(new java.awt.Color(153, 153, 153));
        jTextField1.setText("Proxy");

        jTextField2.setForeground(new java.awt.Color(153, 153, 153));
        jTextField2.setText("Port");

        jCheckBox2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jCheckBox2.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox2.setText("Enable Proxy");

        jTextField3.setText("password");
        jTextField3.setEnabled(false);

        jTextField4.setText("username");
        jTextField4.setEnabled(false);

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 51, 51));
        jButton1.setText("Clear");

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 204, 51));
        jButton2.setText("Enter");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField4)
                    .addComponent(jTextField3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jCheckBox3)
                            .addComponent(jCheckBox4)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jCheckBox1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(chkPostComment)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                .addComponent(jCheckBox2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkPostComment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox3)
                .addGap(13, 13, 13)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(223, 223, 223)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tableUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
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

        txtResult.setBackground(new java.awt.Color(102, 102, 102));
        txtResult.setColumns(20);
        txtResult.setForeground(new java.awt.Color(255, 255, 255));
        txtResult.setRows(5);
        txtResult.setText("Please wait...");
        jScrollPane2.setViewportView(txtResult);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        txtAuth.setForeground(new java.awt.Color(153, 153, 153));
        txtAuth.setText("User and Password Path");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 204, 102));
        jLabel4.setText("Instagram Auth : ");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 204, 102));
        jLabel5.setText("Post Url : ");

        txtUrl.setForeground(new java.awt.Color(153, 153, 153));
        txtUrl.setText("User and Password Path");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 204, 102));
        jLabel6.setText("Comment Path :");

        txtComment.setForeground(new java.awt.Color(153, 153, 153));
        txtComment.setText("User and Password Path");

        btnComment.setText("jButton3");

        btnAuth.setText("jButton3");
        btnAuth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAuthActionPerformed(evt);
            }
        });

        btnUrl.setText("jButton3");

        jButton3.setBackground(new java.awt.Color(0, 153, 153));
        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("CHECK");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 153, 0));
        jButton4.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("LOAD");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 51, 51));
        jButton5.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("STOP");

        jButton6.setBackground(new java.awt.Color(0, 153, 102));
        jButton6.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("START");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(4, 4, 4)
                        .addComponent(txtComment, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAuth, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUrl, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAuth, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUrl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnComment, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtAuth, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAuth))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUrl, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUrl)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txtComment, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(btnComment, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        txtDirectUrl.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtDirectUrl.setForeground(new java.awt.Color(153, 153, 153));
        txtDirectUrl.setText("Enter Post Url");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDirectUrl)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(txtDirectUrl, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnAuthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAuthActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(MainDashboard.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {

            File file = fileChooser.getSelectedFile();
            txtAuth.setText(file.getAbsolutePath());

        }
    }//GEN-LAST:event_btnAuthActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (txtAuth.getText().toString().trim() != null) {
            try {
                File file = new File(txtAuth.getText().toString().trim());
                userAuthModels = utils.getUserAuthList(Files.readAllLines(file.toPath(), StandardCharsets.UTF_8));

                if (userAuthModels != null) {
                    if (userAuthModels.size() > 0) {
                        txtResult.append("\n" + Constants.STAR);
                        txtResult.append("\n" + userAuthModels.size() + " User(s) Found");
                        txtResult.append("\n" + Constants.STAR + "\n");

                        int index = 1;
                        tableModel.setRowCount(0);
                        for (UserAuthModel userAuthModel : userAuthModels) {
                            txtResult.append("\nFor User :" + index + "\nID : " + userAuthModel.getEmail() + "\nPassword : " + userAuthModel.getPassword() + "\n");
                            tableModel.addRow(new Object[]{userAuthModel.getEmail(), userAuthModel.getPassword(), "Enque"});
                            index++;
                        }
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(MainDashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        if (userAuthModels != null) {
            login(userAuthModels);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

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
    private javax.swing.JButton btnAuth;
    private javax.swing.JButton btnComment;
    private javax.swing.JButton btnUrl;
    private javax.swing.JCheckBox chkPostComment;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTable tableUsers;
    private javax.swing.JTextField txtAuth;
    private javax.swing.JTextField txtComment;
    private javax.swing.JTextField txtDirectUrl;
    private javax.swing.JTextArea txtResult;
    private javax.swing.JTextField txtUrl;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onResponse(String message) {
        String time = new SimpleDateFormat("hh:mm:ss").format(new Date());
        txtResult.append(time + " --> " + message + "\n");
    }

    private void openLogInUrl(PageLoadInterface pageLoadInterface) {
        firstTimeLogInLoad = new Thread(new Runnable() {
            @Override
            public void run() {

                new BrowserController().gotoURL(driver, Constants.BASE_URL_INSTA);
                try {
                    sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainDashboard.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (driver.getTitle().contains(Constants.TITLE_LOGIN)) {
                    onResponse("Log In page loaded....");
                    pageLoadInterface.onPageLoad();
                    firstTimeLogInLoad.stop();
                }else{
                    onResponse("Log In page loaded....Another Title");
                    pageLoadInterface.onPageLoad();
                    firstTimeLogInLoad.stop();
                }
            }
        });
        firstTimeLogInLoad.start();
    }

    private void login(List<UserAuthModel> userAuthModels) {
        int attempt = 1;
        onResponse("Attempt : " + attempt);
        logInSingleUser(userAuthModels.get(0));
    }

    private void login(List<UserAuthModel> userAuthModels, int index) {
        int attempt = 1;
        onResponse("Attempt : " + index);
        if (index < userAuthModels.size()) {
            logInSingleUser(userAuthModels.get(index));
        }
    }

    private void logInSingleUser(UserAuthModel userAuthModel) {
        if (driver.getTitle().contains(Constants.TITLE_LOGIN)) {

            try {
                driver.findElement(By.xpath(Constants.XPATH_UID)).sendKeys(userAuthModel.getEmail());
                driver.findElement(By.xpath(Constants.XPATH_PASS)).sendKeys(userAuthModel.getPassword());
                sleep(500);
                driver.findElement(By.xpath(Constants.XPATH_LINBTN)).click();
                waitForLoad(driver);
                sleep(2000);
                if (driver.getPageSource().contains("We Detected An Unusual Login Attempt")) {
                    onResponse("Suspicious Login Attempt");
                    driver.findElement(By.xpath(Constants.XPATH_LOUTBTN)).click();
                    sleep(2000);
                    login(userAuthModels, userIndex++);
                } else if (driver.getPageSource().contains("Sorry, your password was incorrect. Please double-check your password.") || driver.getPageSource().contains("The username you entered doesn't belong to an account. Please check your username and try again.")) {
                    onResponse("Sorry, your username or password was incorrect. Please double-check.");

                    openLogInUrl(new PageLoadInterface() {
                        @Override
                        public void onPageLoad() {

                            try {
                                sleep(2000);
                                login(userAuthModels, userIndex++);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(MainDashboard.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    });
                } else {
                    onResponse("ERROR");
                }

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        } else if (driver.findElement(By.xpath(Constants.XPATH_UID2)).isDisplayed()) {
            try {

                driver.findElement(By.xpath(Constants.XPATH_UID2)).sendKeys(userAuthModel.getEmail());
                driver.findElement(By.xpath(Constants.XPATH_PASS2)).sendKeys(userAuthModel.getPassword());
                sleep(500);
                driver.findElement(By.xpath(Constants.XPATH_LINBTN2)).click();
                waitForLoad(driver);
                sleep(2000);
                if (driver.getPageSource().contains("We Detected An Unusual Login Attempt")) {
                    onResponse("Suspicious Login Attempt");
                    driver.findElement(By.xpath(Constants.XPATH_LOUTBTN)).click();
                    sleep(2000);
                    login(userAuthModels, userIndex++);
                } else if (driver.getPageSource().contains("Sorry, your password was incorrect. Please double-check your password.") || driver.getPageSource().contains("The username you entered doesn't belong to an account. Please check your username and try again.")) {
                    onResponse("Sorry, your username or password was incorrect. Please double-check.");

                    openLogInUrl(new PageLoadInterface() {
                        @Override
                        public void onPageLoad() {

                            try {
                                sleep(2000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(MainDashboard.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            login(userAuthModels, userIndex++);

                        }
                    });

                } else {
                    onResponse("ERROR");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            openLogInUrl(new PageLoadInterface() {
                @Override
                public void onPageLoad() {
                    try {
                        sleep(2000);
                        logInSingleUser(userAuthModel);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainDashboard.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }
    }
}
