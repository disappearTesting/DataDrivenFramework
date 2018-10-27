package com.example.testcases;

import com.example.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class OpenAccountTest extends TestBase {

    @DataProvider
    public static Object[][] getTestData_OpenAccountTest() {
        return new Object[][] {
                {"Hermoine Granger", "Dollar"}
        };
    }

    @Test(dataProvider = "getTestData_OpenAccountTest")
    public void openAccountTest(String customer, String currency) {
        log.info("Test is started.");
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(OR.getProperty("ButtonOpenAccount_CSS"))));
        driver.findElement(By.cssSelector(OR.getProperty("ButtonOpenAccount_CSS"))).click();
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.name(OR.getProperty("SelectCustomer_NAME"))));
        driver.findElement(By.name(OR.getProperty("SelectCustomer_NAME"))).click();
        log.info("Open a dropdown list 'Customer'.");
        Assert.assertTrue(isElementSelect(By.name(OR.getProperty("SelectCustomer_NAME")), customer));
        log.info("Select the Customer.");

        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.name(OR.getProperty("SelectCurrency_NAME"))));
        driver.findElement(By.name(OR.getProperty("SelectCurrency_NAME")));
        log.info("Open a dropdown list 'Currency'.");
        Assert.assertTrue(isElementSelect(By.name(OR.getProperty("SelectCurrency_NAME")), currency));
        log.info("Select the Currency.");

        driver.findElement(By.cssSelector(OR.getProperty("ButtonProcess_CSS"))).submit();
        log.info("Submit data.");

        explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(isAlertPresent(OR.getProperty("TextAlertOpenAccount")));
        log.info("Alert is present. Alert do Accept.");
        log.info("Success. Test is passed.");
    }
}