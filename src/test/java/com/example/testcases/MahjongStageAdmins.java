package com.example.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MahjongStageAdmins {

    private static String PATH_EXECUTABLES_DRIVERS = System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\";
    private static String Page_URL = "https://mahjong-stage.smapps.net/ios/admin/dailyStatistics";

    public static void main(String[] args) throws InterruptedException {
        Date currentDate = new Date();
        System.out.println(currentDate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String DateToStr = dateFormat.format(currentDate);
        System.out.println(DateToStr);

        System.setProperty("webdriver.chrome.driver",  PATH_EXECUTABLES_DRIVERS + "chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get(Page_URL);
        new WebDriverWait(driver, 5).until(ExpectedConditions.urlContains(Page_URL));

        new WebDriverWait(driver,5).until(ExpectedConditions.presenceOfElementLocated(By.id("daterange")));
        driver.findElement(By.id("daterange")).clear();
        driver.findElement(By.id("daterange")).sendKeys(DateToStr);
        driver.findElement(By.xpath("//*[@id=\"multiselectForm\"]/div[7]/button")).click();

        Thread.sleep(5000);
        if(driver.findElement(By.className("table-column-dau")).getText().equals("0") || driver.findElement(By.className("table-column-wau")).getText().equals("0") || driver.findElement(By.className("table-column-mau")).getText().equals("0")) {
            System.out.println("Vadim Achtung!");
        }

        driver.quit();
    }
}