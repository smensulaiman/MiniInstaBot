package com.miniiinstabot.gui;

import com.miniiinstabot.utils.Constants;
import com.miniiinstabot.interfaces.ResponseInterface;
import com.miniiinstabot.manager.BrowserController;
import com.miniiinstabot.manager.DriverManager;
import com.miniiinstabot.model.UserAuthModel;
import com.miniiinstabot.scraper.InstagramScraperManager;
import com.miniiinstabot.utils.Utils;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
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
import me.postaddict.instagram.scraper.model.Account;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
                e.getWindow().dispose();
                if (firstTimeLogInLoad != null) {
                    firstTimeLogInLoad.stop();
                }
                if (driver != null) {
                    driver.close();
                    driver.quit();
                }
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
        jButton2 = new javax.swing.JButton();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
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
        btnCheck = new javax.swing.JButton();
        btnLoad = new javax.swing.JButton();
        btnStart = new javax.swing.JButton();
        txtDirectUrl = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("INSTAGRAM AUTO COMMENT BOT v1.0");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
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
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 564, 150, 20));

        jTextField1.setForeground(new java.awt.Color(153, 153, 153));
        jTextField1.setText("Proxy");
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 97, 30));

        jTextField2.setForeground(new java.awt.Color(153, 153, 153));
        jTextField2.setText("Port");
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 58, 30));

        jCheckBox2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jCheckBox2.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox2.setText("Enable Proxy");
        jPanel1.add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jTextField3.setText("password");
        jTextField3.setEnabled(false);
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 72, 161, -1));

        jTextField4.setText("username");
        jTextField4.setEnabled(false);
        jPanel1.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 46, 161, -1));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 204, 51));
        jButton2.setText("Enter");
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 160, -1));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 150, 30));

        btnSearch.setText("SEARCH");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel2.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 150, 30));

        jTabbedPane3.addTab("tab1", jPanel2);
        jTabbedPane3.addTab("tab2", jPanel3);

        jPanel1.add(jTabbedPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 180, 150));
        jTabbedPane3.getAccessibleContext().setAccessibleName("Search");

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 600));

        tableUsers.setBackground(new java.awt.Color(255, 255, 204));
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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 650, 220));

        txtResult.setBackground(new java.awt.Color(102, 102, 102));
        txtResult.setColumns(20);
        txtResult.setForeground(new java.awt.Color(255, 255, 255));
        txtResult.setRows(5);
        txtResult.setText("Please wait...");
        jScrollPane2.setViewportView(txtResult);
        txtResult.getAccessibleContext().setAccessibleName("");

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 350, 650, 250));

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtAuth.setForeground(new java.awt.Color(153, 153, 153));
        txtAuth.setText("User and Password Path");
        jPanel4.add(txtAuth, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 440, 30));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 102));
        jLabel4.setText("Instagram Auth : ");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 3, -1, 30));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 102));
        jLabel5.setText("Post Url : ");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, 30));

        txtUrl.setForeground(new java.awt.Color(153, 153, 153));
        txtUrl.setText("User and Password Path");
        jPanel4.add(txtUrl, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 440, 30));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 102));
        jLabel6.setText("Comment Path :");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, 30));

        txtComment.setForeground(new java.awt.Color(153, 153, 153));
        txtComment.setText("User and Password Path");
        jPanel4.add(txtComment, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 440, 30));

        btnComment.setText("jButton3");
        jPanel4.add(btnComment, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 60, 20, 30));

        btnAuth.setText("jButton3");
        btnAuth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAuthActionPerformed(evt);
            }
        });
        jPanel4.add(btnAuth, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 20, 30));

        btnUrl.setText("jButton3");
        jPanel4.add(btnUrl, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, 20, 30));

        btnCheck.setBackground(new java.awt.Color(0, 153, 153));
        btnCheck.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnCheck.setForeground(new java.awt.Color(255, 255, 255));
        btnCheck.setText("CHECK");
        btnCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckActionPerformed(evt);
            }
        });
        jPanel4.add(btnCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, 80, 30));

        btnLoad.setBackground(new java.awt.Color(255, 153, 0));
        btnLoad.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnLoad.setForeground(new java.awt.Color(255, 255, 255));
        btnLoad.setText("LOAD");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });
        jPanel4.add(btnLoad, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 0, 80, 30));

        btnStart.setBackground(new java.awt.Color(0, 153, 102));
        btnStart.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnStart.setForeground(new java.awt.Color(255, 255, 255));
        btnStart.setText("START");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });
        jPanel4.add(btnStart, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, 80, 30));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(177, 29, 650, -1));

        txtDirectUrl.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtDirectUrl.setForeground(new java.awt.Color(153, 153, 153));
        txtDirectUrl.setText("Enter Post Url");
        getContentPane().add(txtDirectUrl, new org.netbeans.lib.awtextra.AbsoluteConstraints(277, 0, 440, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnStartActionPerformed

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

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
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
    }//GEN-LAST:event_btnLoadActionPerformed

    private void btnCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckActionPerformed
        // TODO add your handling code here:
        if (userAuthModels != null) {
        }
    }//GEN-LAST:event_btnCheckActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        try {
            // TODO add your handling code here:
            String tag = txtSearch.getText().toString().trim();
            onResponse("\n\nSearchinr for : " + tag + ".......................................");
            
            InstagramScraperManager manager = new InstagramScraperManager();
            Account account = manager.getAccountByUsername(tag);
            
            onResponse("\nID : "+account.getId()+"\nName : "+account.getFullName()+"\nUser Name : "+account.getUsername()+"\nTotal Followers : "+account.getFollowedBy()+"\nBiography : "+account.getBiography());
            
        } catch (IOException ex) {
            onResponse(ex.getMessage());
        }
    }//GEN-LAST:event_btnSearchActionPerformed
    
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
    private javax.swing.JButton btnCheck;
    private javax.swing.JButton btnComment;
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnUrl;
    private javax.swing.JCheckBox chkPostComment;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTable tableUsers;
    private javax.swing.JTextField txtAuth;
    private javax.swing.JTextField txtComment;
    private javax.swing.JTextField txtDirectUrl;
    private javax.swing.JTextArea txtResult;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtUrl;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onResponse(String message) {
        String time = new SimpleDateFormat("hh:mm:ss").format(new Date());
        txtResult.append(time + " --> " + message + "\n");
    }
}
