package com.example.testcases;

import com.example.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test
    public void loginAsBankManager() {
        log.info("Test start.");
        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(OR.getProperty("ButtonBankManagerLogin"))));

        driver.findElement(By.cssSelector(OR.getProperty("ButtonBankManagerLogin"))).click();

        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(OR.getProperty("ButtonAddCustomer"))));

        Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("ButtonAddCustomer"))), "Failure. Element isn't presented!");
        log.info("Success. Test Pass.");
    }
}