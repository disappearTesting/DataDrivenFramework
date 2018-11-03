package com.example.webpages.toptal;

import com.example.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeveloperPortalPage extends TestBase {

    // Define Locators
    @FindBy(xpath = "/html/body/div[1]/div/div/header/div/h1")
    private WebElement heading;

    @FindBy(linkText = "JOIN TOPTAL")
    private WebElement joinToptalButton;

    public DeveloperPortalPage() {
        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened() {
        return heading.getText().toString().contains("developer portal");
    }

    public void clickOnJoin() {
        joinToptalButton.click();
    }
}
