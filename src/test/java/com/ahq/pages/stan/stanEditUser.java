package com.ahq.pages.stan;

import com.ahq.addons.patternLoc;
import com.ahq.globals.web;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.qmetry.qaf.automation.util.Validator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.ahq.globals.BrowserGlobal.*;

import static com.ahq.pages.com.stanLibrary.*;
public class stanEditUser {

    @QAFTestStep(description = "{page}: I verify page title {title} and click {edit} in {userListing} and change {currentRole} to {newRole} and click {save-button}")
    public static void iChangeUserRole(String page, String title, String edit,String userListing, String currentRole, String newRole, String button) throws Exception
    {
        WebDriver driver = new WebDriverTestBase().getDriver();
        driver.navigate().back();
        driver.navigate().back();

        // Go to Edit page
        iWaitForPageToLoad();
        iScrollToAnElement(patternLoc.text(page,userListing));
        iClickAction(page,edit);
        checkSTBTitle(page,title);

        iWaitForPageToLoad();

        iScrollToAnElementAndClick(patternLoc.text(page,currentRole));
        iScrollToTopOfWindow();
        iScrollToAnElementAndClick(patternLoc.text(page,newRole));

        iScrollToAnElementAndClick(patternLoc.button(page,button));
        iWaitForPageToLoad();

    }

    @QAFTestStep(description = "{page}: I verify {oldRole} role changed to {newRole} in {userRoleList} list")
    public static void iVerifyUserRole(String page,String oldRole, String newRole, String list) throws Exception
    {
        WebElement oldCB = new WebDriverTestBase().getDriver().findElement(patternLoc.input(page,oldRole));
        WebElement newCB = new WebDriverTestBase().getDriver().findElement(patternLoc.input(page,newRole));
        String oldVal = oldCB.getAttribute("value");
        String newVal = newCB.getAttribute("value");

        iScrollToTopOfWindow();
        iScrollToAnElement(patternLoc.text(page,list));
        web.waitInSecs("3");
        Validator.verifyFalse(oldCB.isSelected(), oldVal+" is selected",oldVal+ " is not selected");
        Validator.verifyTrue(newCB.isSelected(), newVal+" is not selected",newVal+ " is selected");

    }

}
