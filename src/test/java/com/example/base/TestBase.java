package com.example.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

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
        }
    }

    @AfterSuite
    public void tearDown() {

    }
}
