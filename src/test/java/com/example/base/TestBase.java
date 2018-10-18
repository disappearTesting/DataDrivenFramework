package com.example.base;

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
     */

    private WebDriver driver;

    private Properties config = new Properties();
    private Properties OR = new Properties();

    private FileInputStream sourceStreamConfig;
    private FileInputStream sourceStreamOR;
    private String filePathConfig = System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties";
    private String filePathOR = System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties";

    @BeforeSuite
    public void setUp() {
        if(driver == null) {
            try {
                sourceStreamConfig = new FileInputStream(filePathConfig);
            } catch (FileNotFoundException fe) {
                fe.printStackTrace();
            }
            try {
                config.load(sourceStreamConfig);
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
            } catch (IOException ioe){
                ioe.printStackTrace();
            }

            if(config.getProperty("browser").equals("firefox")) {
                System.setProperty("webdriver.gecko.driver", "\\src\\test\\resources\\executables\\geckodriver.exe");
                driver = new FirefoxDriver();
            } else if(config.getProperty("browser").equals("chrome")) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
                driver = new ChromeDriver();
            } else if(config.getProperty("browser").equals("ie")) {
                System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
                driver = new InternetExplorerDriver();
            }

            driver.get(config.getProperty("testsiteurl"));
            driver.manage().window().maximize();
            // driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
            new WebDriverWait(driver, 5).until(ExpectedConditions.urlContains(config.getProperty("testsiteurl")));
        }
    }

    @AfterSuite
    public void tearDown() {

    }
}
