package com.example.base;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class TestBase {
    /*
    * Will initializing:
    * WebDriver
    * Properties
    * Logs
    * ExtentReports
    * DB
    * Excel
    * Mail
    * ReportNG, ExtendReport
    * Jenkins
     */

    protected static WebDriver driver;
    protected static WebDriverWait explicitWait;

    protected static Properties config = new Properties();
    protected static Properties OR = new Properties();
    public static String browser;

    private FileInputStream sourceStreamConfig;
    private FileInputStream sourceStreamOR;
    private String filePathConfig = System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties";
    private String filePathOR = System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties";
    private String driversPath = System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\";
    protected static Logger log = Logger.getLogger("rootLogger");

    protected static ExtentReports extentReports;
    // Defines a node in the report file.
    protected static ExtentTest extentTest;

    @BeforeSuite
    public void setUp() {
        if(driver == null) {
            PropertyConfigurator.configure("./src/test/resources/properties/log4j.properties");
            log.info("WebDriver init.");

            try {
                sourceStreamConfig = new FileInputStream(filePathConfig);
            } catch (FileNotFoundException fe) {
                fe.printStackTrace();
            }
            try {
                config.load(sourceStreamConfig);
                log.info("Load Config.properties file.");
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

            try {
                sourceStreamOR = new FileInputStream(filePathOR);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                OR.load(sourceStreamOR);
                log.info("Load OR.properties file.");
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

            // Jenkins global Environment
            if(System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {
                browser = System.getenv("browser");
            } else {
                browser = config.getProperty("browser");
            }

            // set the new value of browser property
            config.setProperty("browser", browser);

            switch (browser) {
                case "firefox":
                    System.setProperty("webdriver.gecko.driver", driversPath + "geckodriver.exe");
                    driver = new FirefoxDriver();
                    log.info("Launch Firefox browser!");
                    break;
                case "chrome":
                    System.setProperty("webdriver.chrome.driver",  driversPath + "chromedriver.exe");
                    driver = new ChromeDriver();
                    log.info("Launch Chrome browser!");
                    break;
                case "ie":
                    System.setProperty("webdriver.ie.driver",  driversPath + "IEDriverServer.exe");
                    driver = new InternetExplorerDriver();
                    log.info("Launch IE browser!");
                    break;
            }

            driver.get(config.getProperty("testsiteurl"));
            log.debug("Navigated to:" + config.getProperty("testsiteurl"));
            explicitWait = new WebDriverWait(driver, 5);
            explicitWait.until(ExpectedConditions.urlContains(config.getProperty("testsiteurl")));
            //driver.manage().window().maximize();
            //driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
        }
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
        log.info("WebDriver quit.");
    }

    protected boolean isElementPresent(By by) {
        try{
            driver.findElement(by);
            return true;
        } catch(NoSuchElementException nsee) {
            nsee.printStackTrace();
            return false;
        }
    }

    protected boolean isAlertPresent(String textAlert) {
        try {
            Alert alert = driver.switchTo().alert();
            if(alert.getText().contains(textAlert)) {
                return true;
            }
        } catch (NoAlertPresentException nape) {
            nape.printStackTrace();
        }
        return false;
    }

    protected boolean isElementSelect(By by, String visibleText) {
        try {
            Select select = new Select(driver.findElement(by));
            for(WebElement option : select.getOptions()) {
                if(option.getText().equals(visibleText) && option.isEnabled()) {
                    select.selectByVisibleText(visibleText);
                    return true;
                }
            }
        } catch (NoSuchElementException nsee) {
            nsee.printStackTrace();
        }
        return false;
    }
}