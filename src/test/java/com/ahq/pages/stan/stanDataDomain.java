package com.ahq.pages.stan;

import com.ahq.addons.patternLoc;
import com.ahq.globals.web;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.qmetry.qaf.automation.util.Validator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static com.ahq.globals.BrowserGlobal.*;
import static com.ahq.pages.com.stanLibrary.*;

public class stanDataDomain {

    @QAFTestStep(description = "{page}: {admin} enters {user} email {email} into {searchfield} and click on {field} action in {listing}")
    public static void IClickOnDataDomain(String page,String admin,String user, String email, String searchfield, String field,String listing) throws Exception
    {   iSearchEmail(page,admin,user,email,searchfield);
        iVerifyEmail(page,email,listing);
        iClickAction(page,field);
    }

    @QAFTestStep(description = "{page}: I click on {action} action, verify popup title is {title} and selects {accessList} in {dropdown} dropdown and click on {button}")
    public static void ISelectInDropdown(String page,String action,String title, String[] accessList, String dropdown, String button) throws Exception
    {
        for (String value: accessList){
            iClickAction(page,action);
            IVerifyPopupTitle("Popup",title);
            iSelectDataDomainAccess(page,dropdown,value,button);
        }

    }

    @QAFTestStep(description = "{page}: I verify popup with title {title}")
    public static void IVerifyPopupTitle(String page, String title) throws Exception
    {
        web.waitInSecs("3");
        iVerifyElementPresent(patternLoc.text(page,title));
        web.takeScreenShot();
    }

    @QAFTestStep(description = "{page}: I select {dropdown} dropdown with value {value} and click {button}")
    public static void iSelectDataDomainAccess(String page,String dropdown, String value,String button) throws Exception {
        iSelectDropdownWithValue(patternLoc.dropdown(page,dropdown),value);
        iClickOn(patternLoc.button(page,button));
        iWaitForPageToLoad();
    }

    @QAFTestStep(description = "{page}: In {header} listing I click {action} and verify {value} not found in {dropdownID} dropdown")
    public static void iVerifyDataDomainAccessListingFound(String page, String header,String action, String[] valueList, String dropdownID) throws Exception {
        iWaitForPageToLoad();
        QAFWebElement element = new QAFExtendedWebElement(patternLoc.dropdown(page,dropdownID));
        Select s = new Select(element);
        iScrollToAnElement(patternLoc.header(page,header));
        iClickAction(page,action);
        web.waitInSecs("3");
        element.click();
        web.waitInSecs("3");

        // Check if value in dropdown
        for (String value : valueList) {
            boolean found = false;

            for(WebElement option : s.getOptions() ){
                String innerText = (String) new WebDriverTestBase().getDriver().executeScript("return arguments[0].textContent.trim();", option);
                if (innerText.equals(value)) {
                    found = true;
                    break;
                }
            }
            if (found){
                Validator.verifyFalse(found,value + " is found in dropdown",value + " is not found in dropdown");
            }else{
                Validator.verifyFalse(found,value + " is found in dropdown",value + " is not found in dropdown");
            }
        }
        WebDriver driver = new WebDriverTestBase().getDriver();
        driver.navigate().refresh();
    }

    @QAFTestStep(description = "{page}: In {header} listing I click {action} and verify {value} found in {dropdownID} dropdown")
    public static void iVerifyDataDomainAccessListingNotFound(String page, String header,String action, String[] valueList, String dropdownID) throws Exception {
        iWaitForPageToLoad();
        QAFWebElement element = new QAFExtendedWebElement(patternLoc.dropdown(page,dropdownID));
        Select s = new Select(element);
        iScrollToAnElement(patternLoc.header(page,header));
        iClickAction(page,action);
        web.waitInSecs("3");
        element.click();
        web.waitInSecs("3");

        // Check if value in dropdown
        for (String value : valueList) {
            boolean found = false;

            for(WebElement option : s.getOptions() ){
                String innerText = (String) new WebDriverTestBase().getDriver().executeScript("return arguments[0].textContent.trim();", option);
                if (innerText.equals(value)) {
                    found = true;
                    break;
                }
            }
            if (found){
                Validator.verifyTrue(found,value + " is not found in dropdown",value + " is found in dropdown");
            } else {
                Validator.verifyTrue(found,value + " is not found in dropdown",value + " is found in dropdown");
            }
        }
        WebDriver driver = new WebDriverTestBase().getDriver();
        driver.navigate().refresh();
    }

    @QAFTestStep(description = "{page}: Click on delete button of {submenuItemList}, verify popup title is {title} and click on {button} button")
    public static void clickDeleteButtonOfDataDomain(String page,String[] submenuItemList,String title, String button) throws Exception {
        for (String submenuItem: submenuItemList){
            iScrollToAnElement(patternLoc.header(page,page));
            web.waitInSecs("3");

            // Find the delete button that belongs to the submenu item text
            QAFWebElement delButton = new WebDriverTestBase().getDriver().findElement(patternLoc.text(page,submenuItem));

            // Click the delete button
            delButton.click();
            iWaitForPageToLoad();
            IVerifyPopupTitle("Popup",title);
            web.waitInSecs("3");
            iClickAction(page,button);
        }
    }

}
