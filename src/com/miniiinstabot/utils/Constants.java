package com.miniiinstabot.utils;

/**
 *
 * @author Solaiman
 */
public class Constants {
    
    public static final String BASE_URL_INSTA = "https://www.instagram.com/accounts/login/?source=auth_switcher";
    public static final String BASE_URL_INSTA_HOME = "https://r3.vfsglobal.com/";
    public static final String BASE_URL_2CAPTCHA = "https://2captcha.com/";
    public static final String API_KEY_2CAPTCHA = "";
    public static final String LINK = "https://www.instagram.com/tv/CAoPI_ZHeEp/?utm_source=ig_web_copy_link";
    public static final String CAPTCHA_ID = "CaptchaImage";
    public static final String IP_BLOCK = "This website is secured against online attacks. Your request was blocked due to suspicious behavior";
    public static final String STAR = "**********************************";

    /*=========================================================================================================================================================
                                                                        HEAD TITLE
     =========================================================================================================================================================*/
    
    public static final String TITLE_LOGIN = "Login • Instagram";
    
    
    /*=========================================================================================================================================================
                                                                        X-PATH
     =========================================================================================================================================================*/
    
    
    public static final String XPATH_UID = "//*[@id=\"react-root\"]/section/main/div/article/div/div[1]/div/form/div[2]/div/label/input";
    public static final String XPATH_UID2 = "//*[@id=\"react-root\"]/section/main/article/div[2]/div[1]/div/form/div[2]/div/label/input";
    public static final String XPATH_PASS = "//*[@id=\"react-root\"]/section/main/div/article/div/div[1]/div/form/div[3]/div/label/input";
    public static final String XPATH_PASS2 = "//*[@id=\"react-root\"]/section/main/article/div[2]/div[1]/div/form/div[3]/div/label/input";
    public static final String XPATH_LINBTN = "//*[@id=\"react-root\"]/section/main/div/article/div/div[1]/div/form/div[4]/button";
    public static final String XPATH_LINBTN2 = "//*[@id=\"react-root\"]/section/main/article/div[2]/div[1]/div/form/div[4]/button";
    
    
    public static final String XPATH_SEND_CODE = "//*[@id=\"react-root\"]/section/div/div/div[3]/form/span/button";
    public static final String XPATH_LOUTBTN = "//*[@id=\"react-root\"]/section/div/div/div[4]/a";
    
    
    /*=========================================================================================================================================================
                                                                        EXCEPTIONS
     =========================================================================================================================================================*/
    
    public static final String EXC_UID = "Sorry, your password was incorrect. Please double-check your password.";
    public static final String EXC_PASS = "The username you entered doesn't belong to an account. Please check your username and try again.";
    public static final String EXC_INT = "internet";
    
}
