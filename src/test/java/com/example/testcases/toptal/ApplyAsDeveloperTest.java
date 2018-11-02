package com.example.testcases.toptal;

import com.example.base.TestBase;
import com.example.webpages.toptal.DeveloperApplyPage;
import com.example.webpages.toptal.DeveloperPortalPage;
import com.example.webpages.toptal.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApplyAsDeveloperTest extends TestBase {

    @Test
    public void applyAsDeveloper() throws InterruptedException {
        // Create object of HomePage Class
        HomePage homePage = new HomePage();
        homePage.clickOnDeveloperApplyButton();

        // Create object of DeveloperPortalPage
        DeveloperPortalPage developerPortalPage = new DeveloperPortalPage();
        Assert.assertTrue(developerPortalPage.isPageOpened());
        developerPortalPage.clickOnJoin();

        //  Create object of DeveloperApplyPage
        DeveloperApplyPage developerApplyPage = new DeveloperApplyPage();
        Assert.assertTrue(developerApplyPage.isPageOpened());

        developerApplyPage.setDeveloper_Email("dejan@toptal.com");
        developerApplyPage.setDeveloper_FullName("Dejan Zivanovic Automated Test");
        developerApplyPage.setDeveloper_Password("password123");
        developerApplyPage.setDeveloper_PasswordConfirmation("password123");
        developerApplyPage.toggleOnCheckbox();
        developerApplyPage.clickOnJoin();
    }
}
