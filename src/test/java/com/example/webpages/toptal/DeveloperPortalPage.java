package com.example.webpages.toptal;

import com.example.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeveloperPortalPage extends TestBase {

    // web page - as class

    // various elements on the page - as variables on the class
    // Define Locators
    @FindBy(className = "portal_body-title")
    private WebElement heading;

    @FindBy(linkText = "JOIN TOPTAL")
    private WebElement joinToptalButton;

    // Constructor
    public DeveloperPortalPage() {
        PageFactory.initElements(driver, this);
    }

    // user interactions - as methods on the class
    public boolean isPageOpened() {
        return heading.getText().contains("Developer Portal");
    }

    public void clickOnJoin() {
        joinToptalButton.click();
    }
}
