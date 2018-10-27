package com.example.testcases;

import com.example.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddCustomerTest extends TestBase {

    @DataProvider
    public static Object[][] getTestData_AddCustomer() {
        return new Object[][] {
            {"Kyryl", "Makarov", "95W66S"}
        };
    }

    @Test(dataProvider = "getTestData_AddCustomer")
    public void addCustomerTest(String firstName, String lastName, String postCode) {
        log.info("Test is started.");
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(OR.getProperty("ButtonAddCustomer_CSS"))));

        driver.findElement(By.cssSelector(OR.getProperty("ButtonAddCustomer_CSS"))).click();
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(OR.getProperty("ButtonAddCustomerSubmit_CSS"))));

        driver.findElement(By.cssSelector(OR.getProperty("InputFirstName_CSS"))).clear();
        driver.findElement(By.cssSelector(OR.getProperty("InputFirstName_CSS"))).sendKeys(firstName);
        log.info("Set the First Name.");
        driver.findElement(By.cssSelector(OR.getProperty("InputLastName_CSS"))).clear();
        driver.findElement(By.cssSelector(OR.getProperty("InputLastName_CSS"))).sendKeys(lastName);
        log.info("Set the Last Name.");
        driver.findElement(By.cssSelector(OR.getProperty("InputPostCode_CSS"))).clear();
        driver.findElement(By.cssSelector(OR.getProperty("InputPostCode_CSS"))).sendKeys(postCode);
        log.info("Set the Post Code.");

        driver.findElement(By.cssSelector(OR.getProperty("ButtonAddCustomerSubmit_CSS"))).submit();
        log.info("Submit data.");

        explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(isAlertPresent(OR.getProperty("TextAlertAddCustomer")), "Failure. Alert isn't contains expected text.");
        log.info("Alert is present. Alert do Accept.");
        log.info("Success. Test is passed.");
    }
}