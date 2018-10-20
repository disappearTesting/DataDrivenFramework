package com.example.testcases;

import com.example.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test
    public void loginAsBankManager() {
        log.info("Test started.");
        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(OR.getProperty("ButtonBankManagerLogin"))));
        driver.findElement(By.cssSelector(OR.getProperty("ButtonBankManagerLogin"))).click();
        log.info("Success. Test passed.");
    }
}