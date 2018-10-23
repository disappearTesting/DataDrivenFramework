package com.example.testcases;

import com.example.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddCustomerTest extends TestBase{

    @DataProvider
    public static Object[][] getTestData() {
        return new Object[][] {
            {"Kyryl", "Makarov", "95W66S"}
        };
    }

    @Test(dataProvider = "getTestData")
    public void addCustomer(String firstName, String lastName, String postCode) {
        log.info("Test start.");
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(OR.getProperty("ButtonAddCustomer"))));

        driver.findElement(By.cssSelector(OR.getProperty("ButtonAddCustomer"))).click();
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(OR.getProperty("ButtonAddCustomerSubmit"))));

        log.info("Set the First Name.");
        driver.findElement(By.cssSelector(OR.getProperty("InputFirstName"))).sendKeys(firstName);
        log.info("Set the Last Name.");
        driver.findElement(By.cssSelector(OR.getProperty("InputLastName"))).sendKeys(lastName);
        log.info("Set the Post Code.");
        driver.findElement(By.cssSelector(OR.getProperty("InputPostCode"))).sendKeys(postCode);

        driver.findElement(By.cssSelector(OR.getProperty("ButtonAddCustomerSubmit"))).submit();

        explicitWait.until(ExpectedConditions.alertIsPresent());
        log.info("Alert is present.");

        Assert.assertTrue(driver.switchTo().alert().getText().contains(OR.getProperty("TextAlertSuccessfully")));
        driver.switchTo().alert().accept();
        log.info("Alert do Accept.");
        log.info("Success. Test Pass.");
    }
}