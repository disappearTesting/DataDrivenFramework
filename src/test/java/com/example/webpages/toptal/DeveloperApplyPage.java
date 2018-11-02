package com.example.webpages.toptal;

import com.example.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeveloperApplyPage extends TestBase {

    // web page - as class

    // various elements on the page - as variables on the class
    // Define Locators
    @FindBy(tagName = "h1")
    private WebElement heading;

    @FindBy(id = "talent_create_applicant_email")
    private WebElement developer_email;

    @FindBy(id = "talent_create_applicant_password")
    private WebElement developer_password;

    @FindBy(id = "talent_create_applicant_password_confirmation")
    private WebElement developer_password_confirmation;

    @FindBy(id = "talent_create_applicant_full_name")
    private WebElement developer_full_name;

    @FindBy(id = "developer_skype")
    private WebElement developer_skype;

    @FindBy(id = "save_new_talent_create_applicant")
    private WebElement join_toptal_button;

    @FindBy(className = "label_wrap")
    private WebElement confirm_checkbox;

    // Constructor
    public DeveloperApplyPage() {
        PageFactory.initElements(driver, this);
    }

    // user interactions - as methods on the class
    public void setDeveloper_Email(String email) {
        developer_email.clear();
        developer_email.sendKeys(email);
    }

    public void setDeveloper_Password(String password) {
        developer_password.clear();
        developer_password.sendKeys(password);
    }

    public void setDeveloper_PasswordConfirmation(String password_confirmation) {
        developer_password_confirmation.clear();
        developer_password_confirmation.sendKeys(password_confirmation);
    }

    public void setDeveloper_FullName(String fullname) {
        developer_full_name.clear();
        developer_full_name.sendKeys(fullname);
    }

    public void setDeveloper_Skype(String skype) {
        developer_skype.clear();
        developer_skype.sendKeys(skype);
    }

    public void clickOnJoin() {
        join_toptal_button.click();
    }

    public void toggleOnCheckbox() {
        confirm_checkbox.click();
    }

    public boolean isPageOpened() {
        return heading.getText().contains("Apply to Join our Network of");
    }
}