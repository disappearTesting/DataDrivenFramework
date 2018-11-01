package com.example.webpages.toptal;

import com.example.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends TestBase {

    // Page URL
    private static String PAGE_URL = "https://toptal.com";

    // Define Locators
    @FindBy(name = "")
    private WebElement developerApplyButton;

    // Constructor
    // WebDriver driver extends TestBase
    public HomePage() {
        driver.get(PAGE_URL);
        log.info("Navigate to: " + PAGE_URL);
        // Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public void clickOnDeveloperApplyButton() {
        developerApplyButton.click();
    }
}
