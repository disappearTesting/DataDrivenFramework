package com.example.testcases;

import com.example.base.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test
    public void loginAsBankManager() {
        driver.findElement(By.cssSelector(OR.getProperty("ButtonBankManagerLogin"))).click();
    }
}