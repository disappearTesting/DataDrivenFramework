package com.example.webpages.toptal;

import com.example.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends TestBase {

    // web page - as class

    // Page URL
    private static String PAGE_URL = "https://www.toptal.com/developers";

    // various elements on the page - as variables on the class
    // Define Locators
    @FindBy(linkText = "APPLY AS A DEVELOPER")
    private WebElement developerApplyButton;

    // Constructor
    // WebDriver driver extends TestBase
    public HomePage() {
        driver.get(PAGE_URL);
        log.info("Navigate to: " + PAGE_URL);
        // Initialise Elements
        PageFactory.initElements(driver, this);
    }

    // user interactions - as methods on the class
    public void clickOnDeveloperApplyButton() {
        developerApplyButton.click();
    }
}
