package com.example.base;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

    public WebDriver driver;

    public Properties config = new Properties();
    public Properties OR = new Properties();

    private FileInputStream sourceStreamConfig;
    private FileInputStream sourceStreamOR;
    private String filePathConfig = System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties";
    private String filePathOR = System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties";

    public Logger log = Logger.getLogger("rootLogger");

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
            } catch (IOException ioe){
                ioe.printStackTrace();
            }

            if(config.getProperty("browser").equals("firefox")) {
                System.setProperty("webdriver.gecko.driver", "\\src\\test\\resources\\executables\\geckodriver.exe");
                driver = new FirefoxDriver();
                log.info("Launch Firefox browser!");
            } else if(config.getProperty("browser").equals("chrome")) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
                driver = new ChromeDriver();
                log.info("Launch Chrome browser!");
            } else if(config.getProperty("browser").equals("ie")) {
                System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                log.info("Launch IE browser!");
            }

            driver.get(config.getProperty("testsiteurl"));
            // driver.manage().window().maximize();
            // driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
            new WebDriverWait(driver, 5).until(ExpectedConditions.urlContains(config.getProperty("testsiteurl")));
            log.info("Navigate to:" + config.getProperty("testsiteurl"));
        }
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
        log.info("WebDriver quit.");
    }

    public boolean isElementPresent(By by) {
        try{
            driver.findElement(by);
            return true;
        } catch(NoSuchElementException nsee) {
            nsee.printStackTrace();
            return false;
        }
    }
}
