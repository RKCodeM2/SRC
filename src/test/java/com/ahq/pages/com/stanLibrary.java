package com.ahq.pages.com;

import com.ahq.addons.patternLoc;
import com.ahq.globals.BrowserGlobal;
import com.ahq.globals.web;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.qmetry.qaf.automation.util.Validator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.awt.*;
import java.awt.event.KeyEvent;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.ahq.globals.BrowserGlobal.*;
import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;

public class stanLibrary {
    @QAFTestStep(description = "LoginPage: I open Stan url {env.url} login as userrole {Usertype} with UUID {AdminUUID}")
    public static void openStanURL(String url, String usertype,String uuid) throws Exception
    {
        BrowserGlobal.iOpenWebBrowserAndMaximize(url + uuid);
        waitForStanPageToLoad();
    }

    @QAFTestStep(description = "Stan: I wait for page to load")
    public static void waitForStanPageToLoad() throws Exception
    {
        BrowserGlobal.iVerifyElementAttributeValue(patternLoc.loadingModal("STAN", "loadingModal"), "style", "display: none; flex-direction: column; justify-content: center; align-items: center; position: fixed; z-index: 1000; top: 0px; left: 0px; height: 100%; width: 100%; background: no-repeat rgba(0, 0, 0, 0.5); opacity: 0.0984771;");
    }

    @QAFTestStep(description = "{page}: I verify user login successfully for:{title} with username {username}")
    public static void iVerifyAfterLogin(String page,String title,String username) throws Exception {
        web.verifyPageWithPartialTitle(page, title);
        web.verifyElementPresent(page,username);
    }

    @QAFTestStep(description = "{0}: I verify dashboard page title as {1}")
    public static void checkSTBTitle(String page, String title) throws Exception {
        web.waitInSecs("5");
        web.verifyPageWithPartialTitle(page, title);
        web.takeScreenShot();
    }

    @QAFTestStep(description = "{page}: I verify {0} present in {1}")
    public static void iVerifyTextPresent(String page, String text, String field) throws Exception {
        iScrollToAnElement(patternLoc.text(page,field));
        iVerifyElementPresent(patternLoc.text(page, text));
        iTakeScreenshot();
    }

    @QAFTestStep(description = "{0}: I verify header title as {1}")
    public static void checkPageTitle(String page, String title) throws Exception {
        web.waitInSecs("5");
        web.verifyHeaderNamePresent(page,title);
    }
    @QAFTestStep(description = "{0}: I switch to another tab with index {1}")
    public static void switchToTab(String page, String index) throws Exception {
        web.waitInSecs("5");
        BrowserGlobal.iSwitchWindowByIndex(index);
    }
    @QAFTestStep(description = "{0}: I close Qlik Dashboard and switch to home page")
    public static void switchBackToHomePage(String page) throws Exception {
        BrowserGlobal.iCloseCurrentWindowOrTab();
        BrowserGlobal.iSwitchWindowByIndex("0");
    }

    @QAFTestStep(description ="{0}: I click on {1} footer link and verify navigated to {2} page")
    public static void verifyFooterLink(String page, String link, String title) throws Exception {
        web.clickLink(link);
        web.waitForPageLoad();
        web.verifyPageWithPartialTitle(page, title);
        web.takeScreenShot();
    }
    @QAFTestStep(description = "I close Stan application")
    public void closeStanApplication(){
        BrowserGlobal.iCloseWebBrowser();
    }

    @QAFTestStep(description =  "{0}: I go to {profileTab} and {logout} from the system")
    public void logoutFromStan(String page,String profiletab,String logout) throws Exception {
        //web.mouseOverTopMenu(profiletab);
        web.clicklogout(profiletab,logout);

    }

    @QAFTestStep(description =  "{LogoutPage} : I verify user logout and message displayed as {Message}")
    public void logoutMessage(String page,String message) throws Exception {
        web.verifyElementPresent(page,message);
    }

    @QAFTestStep(description = "{page}: I click on {field} action")
    public static void iClickAction(String page, String field) throws Exception
    {   iScrollToAnElement(patternLoc.text(page,field));
        web.waitInSecs("5");
        iClickOn(patternLoc.text(page,field));
        iWaitForPageToLoad();
    }

    @QAFTestStep(description = "{page}: {admin} inputs {user} email {email} into {searchfield}")
    public static void iSearchEmail(String page,String admin, String user, String email,String searchfield) throws Exception {
        iScrollToAnElement(patternLoc.input(page,searchfield));
        BrowserGlobal.iInputSearch(email,searchfield);
        iWaitForPageToLoad();
    }

    @QAFTestStep(description = "{page}: I verify {email} in {header}")
    public static void iVerifyEmail(String page, String email, String header) throws Exception {
        iScrollToAnElement(patternLoc.header(page,header));
        iVerifyElementPresent(patternLoc.text(page,email));
        iWaitForPageToLoad();
    }

    @QAFTestStep(description = "{page}: I navigate to {menu} and click {submenu}")
    public static void iNavigateToSubMenuAndClick(String page, String menu,String submenu) throws Exception {
        iClickOn(patternLoc.text(page,menu));
        iWaitUntilElementVisible(patternLoc.text(page,submenu));
        iClickOn(patternLoc.text(page,submenu));
        iWaitForPageToLoad();
    }
    @QAFTestStep(description = "{title}: Verify {usersubmenu} sub-menus in {menu} menu")
    public static void subMenuShouldSee(String title, String[] usersubmenu, String menu) throws Exception {
        // Find the list of menu elements
        List<QAFWebElement> list = new WebDriverTestBase().getDriver().findElements(patternLoc.link(title, menu));
        boolean listStatus = !list.isEmpty();

        if (listStatus){
            // mouseover on the menu
            iMouseoverOn(patternLoc.text("Stan", title));

            // Verify each submenu item
            for (String submenuItem : usersubmenu) {
                boolean submenuStatus = false;

                for (QAFWebElement element : list) {
                    if (element.getText().equals(submenuItem)) {
                        submenuStatus = true;
                        break;
                    }
                }
                Validator.verifyTrue(submenuStatus, "Sub-menu not found! '" + submenuItem + "'", "Sub-menu found! '" + submenuItem + "'");
            }
        } else{
            for (String submenuItem : usersubmenu) {
                Validator.verifyFalse(false, "Sub-menu found! '" + submenuItem + "'", "Sub-menu not found! '" + submenuItem + "'");
            }
        }
    }

    @QAFTestStep(description = "{title}: Verify {usersubmenu} sub-menus not in {menu} menu")
    public static void subMenuShouldNotSee(String title, String[] usersubmenu, String menu) throws Exception {
        // Find the list of menu elements
        List<QAFWebElement> list = new WebDriverTestBase().getDriver().findElements(patternLoc.link(title, menu));
        boolean listStatus = !list.isEmpty();

        if (listStatus){
            // mouseover on the menu
            web.waitInSecs("3");
            iMouseoverOn(patternLoc.text("Stan", title));

            // Verify each submenu item
            for (String submenuItem : usersubmenu) {
                boolean submenuStatus = false;
                for (QAFWebElement element : list) {
                    if (element.getText().equals(submenuItem)) {
                        submenuStatus = true;
                        break;
                    }
                    Validator.verifyFalse(submenuStatus, "Sub-menu found! '" + submenuItem + "'", "Sub-menu not found! '" + submenuItem + "'");
                }
            }
        } else{
            for (String submenuItem : usersubmenu) {
                Validator.verifyFalse(false, "Sub-menu found! '" + submenuItem + "'", "Sub-menu not found! '" + submenuItem + "'");
            }
        }
    }

    public static void iScrollToTopOfWindow() throws Exception {
        new WebDriverTestBase().getDriver().executeScript("window.scrollTo(document.body.scrollHeight, 0)");
        web.waitInSecs("3");
    }

    public static void iScrollToMiddleOfWindow() throws Exception {
        new WebDriverTestBase().getDriver().executeScript("window.scrollTo(0, document.body.scrollHeight / 2)");
        web.waitInSecs("3");
    }

    @QAFTestStep(description = "I save the PDF file")
    public static void iSaveThePdfFile() throws Exception {
        iWaitForPageToLoad();
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_S);
        robot.keyRelease(KeyEvent.VK_S);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        iWaitForSeconds("2");
        robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
        robot.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
        iWaitForSeconds("2");
    }

    @QAFTestStep(description =  "{0}: click link {1} and switch to browser tab {2} and save the PDF file")
    public void iClickLinkSwitchTabAndSavePDF(String page,String link,String tab) throws Exception {
        web.clickLink(link);
        BrowserGlobal.iSwitchBrowsertabToIndex(tab);
        waitForStanPageToLoad();
        iSaveThePdfFile();
    }

    @QAFTestStep(description =  "{0}: close current tab and switch tab to {1} and click link {2}")
    public void closeCurrentTabSwitchTabAndClickLink(String page,String tab,String link) throws Exception {
        BrowserGlobal.iCloseCurrentWindowOrTab();
        BrowserGlobal.iSwitchBrowsertabToIndex(tab);
        web.clickLink(link);
    }

}

