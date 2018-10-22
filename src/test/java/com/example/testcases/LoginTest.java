package com.example.testcases;

import com.example.base.TestBase;
import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test
    public void loginAsBankManager() {
        BasicConfigurator.configure();
        log.info("Tests run: LoginTest, method: loginAsBankManager");
        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(OR.getProperty("ButtonBankManagerLogin"))));
        driver.findElement(By.cssSelector(OR.getProperty("ButtonBankManagerLogin"))).click();

        log.info("Test passed.");
    }
}