package com.example.webpages.toptal;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeveloperApplyPage {

    // Define Locators
    @FindBy(tagName = "h1")
    private WebElement heading;

    @FindBy(id = "developer_email")
    private WebElement developer_email;

    @FindBy(id = "developer_password")
    private WebElement developer_password;

    @FindBy(id = "developer_password_confirmation")
    private WebElement developer_password_confirmation;
}