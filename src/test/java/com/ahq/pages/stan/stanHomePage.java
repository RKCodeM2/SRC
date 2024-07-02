package com.ahq.pages.stan;

import com.ahq.addons.patternLoc;
import com.ahq.globals.BrowserGlobal;
import com.ahq.globals.web;
import com.ahq.pages.com.stanLibrary;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.qmetry.qaf.automation.util.Validator;

import java.util.List;

import static com.ahq.globals.BrowserGlobal.*;

public class stanHomePage {
    @QAFTestStep(description = "stanHomePage: I Click on {0} Link")
    public void clickOnStanNavigationLink(String link) throws Exception {
        web.clickLink(link);
    }
    @QAFTestStep(description = "stanHomePage: I Signin as {0} User with {1} Password")
    public void siginAsRegisteredUser(String userName, String password) throws Exception {
        web.clickLink("Sign In");
        web.input(userName,"username");
        web.input(password,"password");
        web.clickButton("signInBtn");
    }
    @QAFTestStep(description =  "StanHomePage: I navigate form {0} to {1}")
    public void navigateToCategory(String categoryLvl1,String categoryLvl2) throws Exception {
        web.clickLink(categoryLvl1);
        web.clickLink(categoryLvl2);
    }

    @QAFTestStep(description = "stanHomePage: I hover over {0} on the navbar and click {1} on the submenu")
    public void hoverOverNavbarAndClickOnSubmenu(String navbarName, String submenuName) throws Exception {
        web.hoverOverNavbar(navbarName);
        iWaitForMilliseconds("500");
        web.clickOnNavbarSubmenu(submenuName);
    }

    @QAFTestStep(description = "{page}:I Click on {0} Link in my dashboard")
    public void clickOnLinkinMyDashboard(String page,String link) throws Exception {
        web.clickGraphLink(link);
    }

    @QAFTestStep(description =  "stanHomePage: I assert that the user is logged in")
    public void verifyUserIsLoggedIn() throws Exception {
        BrowserGlobal.iAssertTextPresentInPage("Sign Out");
        BrowserGlobal.iAssertTextPresentInPage("Welcome");
    }

    @QAFTestStep(description = "Verify User Admin Login Successfully: {title} top menu is present")
    public static void iTakeDashBoardScreenshot(String title) throws Exception {
        iWaitForPageToLoad();
        iVerifyElementPresent(patternLoc.text("STAN",title));
    }

    @QAFTestStep(description =  "stanHomePage: I log out")
    public void stanUserLogOut() throws Exception {
        BrowserGlobal.iScrollToTheTopOfThePage();
        web.hoverOverButton("profileTab");
        web.clickButton("Sign Out");
        BrowserGlobal.iVerifyTextPresentInPage("Sign out successful");
    }

    @QAFTestStep(description = "stanHomePage: I verify that {0} tab is active")
    public void verifyWhichTabIsOpen(String tabName) throws Exception {
        stanLibrary.waitForStanPageToLoad();
        String activeNavbarTab = patternLoc.navbar("STAN","navbar-item active");
        BrowserGlobal.iVerifyElementText(activeNavbarTab, tabName);
    }


    @QAFTestStep(description = "{page}: I hover on {menuName} and click {submenu} sub-menu in {menuID}")
    public static void iclickSubmenu(String page, String menuName, String submenu, String menuID) throws Exception {
        // Find the list of menu elements
        // Step: "StanHomePage": I hover on "Data Visualisation" and click "Hotel Performance" sub-menu in "DataVisualisation"

        List<QAFWebElement> list = new WebDriverTestBase().getDriver().findElements(patternLoc.link(page, menuID));
        boolean listStatus = !list.isEmpty();

        if (listStatus){
            // mouseover on the menu
            iMouseoverOn(patternLoc.text("Stan", menuName));

            for (QAFWebElement element : list) {
                String innerText = (String) new WebDriverTestBase().getDriver().executeScript("return arguments[0].textContent.trim();", element);
                if (innerText.equals(submenu)){
                    element.click();
                    break;
                }
            }
        }
        else{
            Validator.verifyFalse(false, "menu found! '" + menuName + "'", "Menu not found! '" + menuName + "'");
        }
    }



    /* @Author Winson
    @QAFTestStep(description = "{page}: I navigate to {submenu} dashboard under {menu}")
    public static void iNavigateToDashboard(String page, String submenu,String menu) throws Exception {
        iClickOn(patternLoc.text(page,menu));
        iWaitUntilElementVisible(patternLoc.text(page,submenu));
        iClickOn(patternLoc.text(page,submenu));
    }


    @QAFTestStep(description = "{page}: I verify {submenu} dashboard with screenshot")
    public static void iVerifyDashboard(String page, String submenu){
        iWaitForSeconds("1");
        iTakeScreenshot();
    }


    @QAFTestStep(description = "{page}: Under {menu}, I verify {submenu} present")
    public static void iVerifySubMenuPresent(String page, String menu,String submenu) throws Exception {
        iMouseoverOn(patternLoc.text(page ,menu));
        iWaitForSeconds("1");
        iVerifyElementPresent(patternLoc.text(page,submenu));
        iTakeScreenshot();
        iWaitForSeconds("1");
    } */

}
