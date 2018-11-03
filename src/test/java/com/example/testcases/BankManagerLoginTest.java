package com.example.testcases;

import com.example.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BankManagerLoginTest extends TestBase {

    @Test
    public void loginAsBankManagerTest() {
        log.info("Test has Started.");
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(OR.getProperty("ButtonBankManagerLogin_CSS"))));
        driver.findElement(By.cssSelector(OR.getProperty("ButtonBankManagerLogin_CSS"))).click();
        log.info("Click ButtonBankManagerLogin button.");

        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(OR.getProperty("ButtonAddCustomer_CSS"))));
        Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("ButtonAddCustomer_CSS"))), "Failure. Element isn't present!");
        log.info("Success. Test is Pass.");
    }
}