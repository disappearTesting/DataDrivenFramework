package com.example.testcases;

import com.example.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class BankManagerLoginTest extends TestBase {

    @Test
    public void loginAsBankManager() {
        log.info("Test start.");
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(OR.getProperty("ButtonBankManagerLogin"))));
        driver.findElement(By.cssSelector(OR.getProperty("ButtonBankManagerLogin"))).click();

        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(OR.getProperty("ButtonAddCustomer"))));
        Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("ButtonAddCustomer"))), "Failure. Element isn't present!");
        log.info("Success. Test Pass.");
        Reporter.log("Success. Test Pass.");
    }
}