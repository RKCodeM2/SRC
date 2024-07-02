package com.ahq.globals;

import com.ahq.addons.patternLoc;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;


public class web {
    /**
     * @param url [Open browser with URL]
     */
    @QAFTestStep(description = "Open url {0}")
    public static void openBrowser(String url) throws Exception {
        BrowserGlobal.iOpenWebBrowser(url);
    }

    /**
     * @param url [Open browser with URL]
     */
    @QAFTestStep(description = "Open url {0} And Maximize")
    public static void openMaximizedBrowser(String url) throws Exception {
        BrowserGlobal.iOpenWebBrowserAndMaximize(url);
    }

//    Verify "Dashboard" page is displayed with title "Home Dashboard"
    /**
     * @param page [Page name]
     * @param title [Part Title of the page]
     */
    @QAFTestStep(description = "Verify {0} page is displayed with title {1}")
    public static void verifyPageWithPartialTitle(String page, String title) throws Exception {
        BrowserGlobal.iWaitForPageToLoad();
        getBundle().setProperty("auto.page.name",page);
        BrowserGlobal.iAssertTitlePartialText(title);
    }

    /**
     * @param page [Page name]
     * @param header_text [Header text to be verified]
     */
    @QAFTestStep(description = "Verify {0} page is displayed with header text {1}")
    public static void verifyPageWithHeader(String page, String header_text) throws Exception {
        BrowserGlobal.iWaitForPageToLoad();
        BrowserGlobal.iWaitUntilElementPresent(patternLoc.header(getPageName(),header_text));
        BrowserGlobal.iScrollToAnElement(patternLoc.header(getPageName(),header_text));
        BrowserGlobal.iWaitUntilElementVisible(patternLoc.header(getPageName(),header_text));
        BrowserGlobal.iAssertElementText(patternLoc.header(page,header_text),header_text);
    }
    /**
     * @param page [Page name]
     * @param header_text [Header text to be verified]
     */
    @QAFTestStep(description = "Verify {0} page is displayed with Text {1}")
    public static void verifyPageText(String page, String header_text) throws Exception {
        BrowserGlobal.iWaitForPageToLoad();
        BrowserGlobal.iWaitUntilElementPresent(patternLoc.header(getPageName(),header_text));
        BrowserGlobal.iScrollToAnElement(patternLoc.header(getPageName(),header_text));
        BrowserGlobal.iWaitUntilElementVisible(patternLoc.header(getPageName(),header_text));
        BrowserGlobal.iAssertElementText(patternLoc.header(page,header_text),header_text);
    }
    /**
     * @param page [Page name]
     * @param header_text [Header text to be verified]
     */
    @QAFTestStep(description = "Verify {0} page is displayed with Partial Text {1}")
    public static void verifyPartialText(String page, String header_text) throws Exception {
        BrowserGlobal.iWaitForPageToLoad();
        BrowserGlobal.iWaitUntilElementPresentWithTimeout(patternLoc.link(getPageName(),header_text), "10");
        BrowserGlobal.iScrollToAnElement(patternLoc.link(getPageName(),header_text));
        BrowserGlobal.iWaitUntilElementVisible(patternLoc.link(getPageName(),header_text));
        String msgSuccess = BrowserGlobal.iGetText(patternLoc.link(getPageName(),header_text));
        msgSuccess.contains(header_text);
    }

    /**
     *
     * @throws Exception
     */
    @QAFTestStep(description = "I wait for page Load")
    public static void waitForPageLoad() throws Exception {
        BrowserGlobal.iWaitForPageToLoad();
    }

    /**
     *
     * @param tabNumber [Tab number to locate]
     *
     */
    @QAFTestStep(description = "I swift to the tab {0}")
    public static void moveTab(String tabNumber) throws Exception {
        BrowserGlobal.iSwitchWindowByIndex(tabNumber);
    }



    /**
     * @param text [text to fill]
     * @param field [Field name]
     */
    @QAFTestStep(description = "Input {0} into {1}")
    public static void input(String text,String field) throws Exception {
        BrowserGlobal.iWaitUntilElementPresentWithTimeout(patternLoc.input(getPageName(),field),"10");
        BrowserGlobal.iScrollToAnElement(patternLoc.input(getPageName(),field));
        BrowserGlobal.iWaitUntilElementVisible(patternLoc.input(getPageName(),field));
        BrowserGlobal.iInputInTo(text, patternLoc.input(getPageName(),field));
    }
    /**
     * @param radio_Text [Text to be selected in Radio button]
     * @param field [Field name]
     */
 /*   @QAFTestStep(description = "Choose Field:{0} Value:{1}")
    public static void radioButton(String radio_Text, String field) throws Exception {
        BrowserGlobal.iWaitUntilElementPresentWithTimeout(patternLoc.radio(getPageName(),field,radio_Text),"10");
        BrowserGlobal.iWaitUntilElementPresent(patternLoc.radio(getPageName(),field,radio_Text));
        BrowserGlobal.iScrollToAnElement(patternLoc.radio(getPageName(),field,radio_Text));
        BrowserGlobal.iClickOn(patternLoc.radio(getPageName(),field,radio_Text));
    }    */

    /**
     *
     * @param field [Field name]
     *
     */
    @QAFTestStep(description = "Choose Field:{0} Value:{1}")
    public static void tabOut( String field) throws Exception {
        BrowserGlobal.iClickAndTabInToThenEnter(patternLoc.input(getPageName(),field));
    }

    /**
     *
     * @param field [Field name]
     *
     */
/*    @QAFTestStep(description = "tab Out and Enter on Field {0}")
    public static void tabOnceAndEnter( String field) throws Exception {
        BrowserGlobal.iTabInToThenEnter(patternLoc.input(getPageName(),field));
    }  */

    /**
     * @param text [text to fill]
     * @param field [Field name]
     */
    @QAFTestStep(description = "Input {0} into {1} without label")
    public static void inputWithoutLabel(String text,String field) throws Exception {
        BrowserGlobal.iWaitUntilElementPresent(patternLoc.input(getPageName(),field, false));
        BrowserGlobal.iScrollToAnElement(patternLoc.input(getPageName(),field, false));
        BrowserGlobal.iWaitUntilElementVisible(patternLoc.input(getPageName(),field, false));
        BrowserGlobal.iInputInTo(text, patternLoc.input(getPageName(),field, false));
    }
    /**
     * @param field [Field name]
     */
    @QAFTestStep(description = "Click button {0}")
    public static void clickButton(String field) throws Exception {
        try {
            BrowserGlobal.iWaitUntilElementVisibleWithTimeout(patternLoc.button(getPageName(),field),"10");
            BrowserGlobal.iScrollToAnElement(patternLoc.button(getPageName(), field));
            BrowserGlobal.iWaitUntilElementEnabled(patternLoc.button(getPageName(), field));
            BrowserGlobal.iClickOn(patternLoc.button(getPageName(), field));
        }catch (Exception e){
            BrowserGlobal.iClickOn(patternLoc.button(getPageName(), field));
        }
    }

    /**
     * @param field [Field name]
     */
 /*   @QAFTestStep(description = "Click button {0}")
    public static void clickModalButton(String field) throws Exception {
        BrowserGlobal.iWaitUntilElementVisibleWithTimeout(patternLoc.modalButton(getPageName(),field),"10");
        BrowserGlobal.iScrollToAnElement(patternLoc.modalButton(getPageName(),field));
        BrowserGlobal.iWaitUntilElementEnabled(patternLoc.modalButton(getPageName(),field));
        BrowserGlobal.iClickOn(patternLoc.modalButton(getPageName(),field));
    }   */

    /**
     * @param field [Field name]
     */
    @QAFTestStep(description = "Click Input field {0}")
    public static void clickInput(String field) throws Exception {
        BrowserGlobal.iClickOn(patternLoc.input(getPageName(),field));
    }



    /**
     * @param field [Field name]
     */
    @QAFTestStep(description = "Double click on link {0}")
    public static void doubleClickButton(String field) throws Exception {
        BrowserGlobal.iScrollToAnElement(patternLoc.link(getPageName(),field));
        BrowserGlobal.iWaitUntilElementVisible(patternLoc.link(getPageName(),field));
        BrowserGlobal.iWaitUntilElementEnabled(patternLoc.link(getPageName(),field));
        BrowserGlobal.iDoubleClickOn(patternLoc.link(getPageName(),field));
    }

    /**
     * @param field [Field name]
     */
    @QAFTestStep(description = "Click link {0}")
    public static void clickLink(String field) throws Exception {
       // BrowserGlobal.iScrollToAnElement(patternLoc.link(getPageName(),field));
        BrowserGlobal.iWaitUntilElementPresent(patternLoc.link(getPageName(),field));
        BrowserGlobal.iWaitUntilElementEnabled(patternLoc.link(getPageName(),field));
        BrowserGlobal.iClickOn(patternLoc.link(getPageName(),field));
    }

    /**
     *
     * @param waitTime [Time to Wait]
     *
     */
    @QAFTestStep(description = "I wait for {0} Secs")
    public static void waitInSecs(String waitTime) throws Exception {
        BrowserGlobal.iWaitForSeconds(waitTime);
    }

    /**
     *
     *
     */
    @QAFTestStep(description = "And I take screeshot")
    public static void takeScreenShot() throws Exception {
        BrowserGlobal.iTakeScreenshot();
    }

    /**
     *
     * @param waitTime [Wait time in Milli Secs]
     *
     */
    @QAFTestStep(description = "I wait for {0} MilliSecs")
    public static void waitInMilliSecs(String waitTime) throws Exception {
        BrowserGlobal.iWaitForMilliseconds(waitTime);
    }

    /**
     *
     * @param frame- [Frame Name]
     *
     */
    @QAFTestStep(description="Moving into the frame {0}")
    public static void moveToFrame(String frame) throws Exception {
        BrowserGlobal.iWaitForPageToLoad();
        BrowserGlobal.iSwitchToIFrameByIdOrName(frame);
    }

    /**
     * @param field [Field name]
     */
    @QAFTestStep(description = "Move to Subheader {0}")
    public static void clickSubHeader(String field) throws Exception {
        BrowserGlobal.iScrollToAnElement(patternLoc.subHeader(getPageName(),field));
        BrowserGlobal.iWaitUntilElementVisible(patternLoc.subHeader(getPageName(),field));
        BrowserGlobal.iWaitUntilElementEnabled(patternLoc.subHeader(getPageName(),field));
        BrowserGlobal.iClickOn(patternLoc.subHeader(getPageName(),field));
    }

    /**
     * @param dropdown_Text [Text to be selected in dropdown]
     * @param field [Field name]
     */
    @QAFTestStep(description = "Select Field:{0} Value:{1}")
    public static void select(String dropdown_Text, String field) throws Exception {
        BrowserGlobal.iWaitUntilElementPresent(patternLoc.select(getPageName(), field));
        BrowserGlobal.iScrollToAnElement(patternLoc.select(getPageName(), field));
        BrowserGlobal.iSelectDropdownWithText(patternLoc.select(getPageName(), field), dropdown_Text);
        BrowserGlobal.iPressKey("Enter");
    }
    /**
     *
     * @param field [Field name]
     */
    @QAFTestStep(description = "Click select field with value Value:{1}")
    public static void clickSelect(String field) throws Exception {
        BrowserGlobal.iWaitUntilElementPresent(patternLoc.select(getPageName(),field));
        BrowserGlobal.iScrollToAnElement(patternLoc.select(getPageName(),field));
        BrowserGlobal.iClickOn(patternLoc.select(getPageName(),field));
    }

    /**
     *
     * @param field [Field name]
     */
    @QAFTestStep(description = "I check field with value:{1}")
    public static void checkBox(String field) throws Exception {
        BrowserGlobal.iWaitUntilElementPresent(patternLoc.input(getPageName(),field));
        BrowserGlobal.iScrollToAnElement(patternLoc.input(getPageName(),field));
        BrowserGlobal.iClickOn(patternLoc.input(getPageName(),field));
    }
    /**
     * @param field [Field name]
     *              Author - Bharat
     */
    @QAFTestStep(description = "Click Graph link {0}")
    public static void clickGraphLink(String field) throws Exception {
        //BrowserGlobal.iSwitchWindowByIndex("0");

        BrowserGlobal.iScrollToAnElement(patternLoc.link(getPageName(),field));
        BrowserGlobal.iWaitUntilElementPresent(patternLoc.graphlink(getPageName(),field));
        BrowserGlobal.iClickOn(patternLoc.graphlink(getPageName(),field));
        BrowserGlobal.iTakeScreenshot();
    }

    @QAFTestStep(description = "Mouse on {0}")
    public static void mouseOverTopMenu(String field) throws Exception {
        BrowserGlobal.iScrollToAnElement(patternLoc.text(getPageName(),field));
        BrowserGlobal.iMouseoverOn(patternLoc.text(getPageName(),field));
        BrowserGlobal.iTakeScreenshot();
    }

    /**
     * @param field [Field name] with help of pattern
     *              Author - Bharat
     */
    @QAFTestStep(description = "verify element is present")
    public static void verifyElementPresent(String page,String field) throws Exception {
        web.waitForPageLoad();
        getBundle().setProperty("auto.page.name",page);
        web.waitInSecs("5");
        BrowserGlobal.iVerifyElementPresent(patternLoc.text(page,field));
    }

    @QAFTestStep(description = "I verify header name is present")
    public static void verifyHeaderNamePresent(String page,String field) throws Exception {
        web.waitForPageLoad();
        getBundle().setProperty("auto.page.name",page);
        web.waitInSecs("5");
        BrowserGlobal.iVerifyElementPresent(patternLoc.header(page,field));
    }


    @QAFTestStep(description = "I verify three sheets are present")
    public static void verifythreeSheetsPresent(String page,String sheet1,String sheet2,String sheet3) throws Exception {
        getBundle().setProperty("auto.page.name",page);
        BrowserGlobal.iVerifyElementPresent(patternLoc.text(page,sheet1));
        BrowserGlobal.iVerifyElementPresent(patternLoc.text(page,sheet2));
        BrowserGlobal.iVerifyElementPresent(patternLoc.text(page,sheet3));
    }

    @QAFTestStep(description = "I verify one sheet is present")
    public static void verifyoneSheetsPresent(String page,String sheet1) throws Exception {
        getBundle().setProperty("auto.page.name", page);
        BrowserGlobal.iVerifyElementPresent(patternLoc.text(page, sheet1));
    }


    @QAFTestStep(description = "I verify glossary is present")
    public static void verifyGlossaryPresent(String page,String field) throws Exception {
        getBundle().setProperty("auto.page.name",page);
        BrowserGlobal.iVerifyElementPresent(patternLoc.text(page,field));
    }

    @QAFTestStep(description = "I verify adhocreport is present")
    public static void verifyAdhocreportPresent(String page,String field) throws Exception {
        getBundle().setProperty("auto.page.name",page);
        BrowserGlobal.iVerifyElementPresent(patternLoc.text(page,field));
    }
    @QAFTestStep(description = "I log out from the system")
    public static void clicklogout(String dropdownlink ,String fieldname) throws Exception {
        BrowserGlobal.iScrollToAnElement(patternLoc.text(getPageName(),dropdownlink));
        BrowserGlobal.iWaitUntilElementPresent(patternLoc.text(getPageName(),fieldname));
        BrowserGlobal.iClickOn(patternLoc.button(getPageName(),dropdownlink));
        BrowserGlobal.iWaitUntilElementPresent(patternLoc.button(getPageName(),fieldname));
        BrowserGlobal.iClickOn(patternLoc.button(getPageName(),fieldname));
    }
    @QAFTestStep(description = "Hover over button {0}")
    public static void hoverOverButton(String field) throws Exception {
        try {
            BrowserGlobal.iScrollToAnElement(patternLoc.text(getPageName(),field));
            BrowserGlobal.iWaitUntilElementVisibleWithTimeout(patternLoc.button(getPageName(),field),"10");
            BrowserGlobal.iScrollToAnElement(patternLoc.button(getPageName(), field));
            BrowserGlobal.iWaitUntilElementEnabled(patternLoc.button(getPageName(), field));
            BrowserGlobal.iMouseoverOn(patternLoc.button(getPageName(), field));
        }catch (Exception e){
            BrowserGlobal.iMouseoverOn(patternLoc.button(getPageName(), field));
        }
    }

    @QAFTestStep(description = "Click Navbar Submenu {0}")
    public static void clickOnNavbarSubmenu(String submenuName) throws Exception {
        String submenu = patternLoc.submenu(getPageName(),submenuName);
        BrowserGlobal.iVerifyElementPresent(submenu);
        BrowserGlobal.iScrollToAnElement(submenu);
        BrowserGlobal.iClickOn(submenu);
    }

    @QAFTestStep(description = "Click Navbar {0}")
    public static void clickOnNavbar(String navbarName) throws Exception {
        String navbarMenu = patternLoc.navbar(getPageName(),navbarName);
        BrowserGlobal.iVerifyElementPresent(navbarMenu);
        BrowserGlobal.iScrollToAnElement(navbarMenu);
        BrowserGlobal.iClickOn(navbarMenu);
    }

    @QAFTestStep(description = "Hover over Navbar {0}")
    public static void hoverOverNavbar(String navbarName) throws Exception {
        String navbarMenu = patternLoc.navbar(getPageName(),navbarName);
        BrowserGlobal.iVerifyElementPresent(navbarMenu);
        BrowserGlobal.iScrollToAnElement(navbarMenu);
        BrowserGlobal.iMouseoverOn(navbarMenu);
    }

    @QAFTestStep(description = "qlikDashboard: I wait for Qlik Dashboard to load")
    public static void iWaitForQlikToLoad() throws Exception {
        String qlikLoading = patternLoc.loadingModal("STAN","qs-pong qv-block-ui");
        BrowserGlobal.iWaitUntilElementPresent(qlikLoading);
        BrowserGlobal.iWaitUntilElementNotPresent(qlikLoading);
        BrowserGlobal.iWaitForSeconds("1");
    }

    @QAFTestStep(description = "Click card {0}")
    public void clickCard(String cardName) throws Exception {
        String card = patternLoc.card(getPageName(),cardName);
        BrowserGlobal.iVerifyElementPresent(card);
        BrowserGlobal.iScrollToAnElement(card);
        BrowserGlobal.iClickOn(card);
    }

    @QAFTestStep(description = "Click accordion {0}")
    public static void clickAccordion(String accordionName) throws Exception {
        String accordion = patternLoc.accordion(getPageName(),accordionName);
        BrowserGlobal.iVerifyElementPresent(accordion);
        BrowserGlobal.iScrollToAnElement(accordion);
        BrowserGlobal.iClickOn(accordion);
    }

    @QAFTestStep(description = "I verify error is present in page {0}")
    public static boolean iVerifyErrorIsPresentInPage(String errorText) throws Exception {
        String errorLocator = patternLoc.error(getPageName(),errorText);
        boolean status = BrowserGlobal.iVerifyElementPresent(errorLocator);
        Validator.verifyTrue(status, "Text not found! '" + errorText + "'", "Text found! '" + errorText + "'");
        Reporter.logWithScreenShot("");
        return status;
    }

    @QAFTestStep(description = "I verify accordion {0} is present")
    public static void iVerifyAccordionIsPresent(String accordionName) throws Exception {
        String accordion = patternLoc.accordion(getPageName(),accordionName);
        BrowserGlobal.iVerifyElementPresent(accordion);
    }

    @QAFTestStep(description = "I verify accordion {0} is present with search match {1}")
    public static void iVerifyAccordionIsPresentWithSearchMatch(String accordionName, String searchMatch) throws Exception {
        String accordion = patternLoc.accordion(getPageName(),accordionName);
        String search = patternLoc.text(getPageName(),searchMatch);
        BrowserGlobal.iVerifyElementPresent(accordion);
        BrowserGlobal.iVerifyElementPresent(search);
    }

    @QAFTestStep(description = "I verify accordion text is {0}")
    public static void verifyAccordionText(String accordionName) throws Exception {
        String accordion = patternLoc.accordion(getPageName(),accordionName);
        BrowserGlobal.iVerifyElementText(accordion, accordionName);
    }

    @QAFTestStep(description = "Click dropdown {0}")
    public static void clickDropdown(String dropdownName) throws Exception {
        String dropdown = patternLoc.dropdown(getPageName(),dropdownName);
        BrowserGlobal.iVerifyElementPresent(dropdown);
        BrowserGlobal.iScrollToAnElement(dropdown);
        BrowserGlobal.iClickOn(dropdown);
    }

    @QAFTestStep(description = "Click dropdown option {0}")
    public static void clickDropdownOption(String dropdownOptionName) throws Exception {
        String dropdownOption = patternLoc.dropdown(getPageName(),dropdownOptionName);
        BrowserGlobal.iVerifyElementPresent(dropdownOption);
        BrowserGlobal.iScrollToAnElement(dropdownOption);
        BrowserGlobal.iClickOn(dropdownOption);
    }
    @QAFTestStep(description = "Click sheet {0}")
    public static void clickSheet(String sheetName) throws Exception {
        String sheet = patternLoc.sheet(getPageName(),sheetName);
        BrowserGlobal.iWaitUntilElementPresent(sheet);
        BrowserGlobal.iScrollToAnElement(sheet);
        BrowserGlobal.iClickOn(sheet);
    }

    @QAFTestStep(description = "Click graph tab {0}")
    public static void clickGraphTab(String graphTabTitle) throws Exception {
        String graphTab = patternLoc.graphTab(getPageName(),graphTabTitle);
        BrowserGlobal.iWaitUntilElementPresent(graphTab);
        BrowserGlobal.iScrollToAnElement(graphTab);
        BrowserGlobal.iClickOn(graphTab);
    }

    @QAFTestStep(description = "Right click graph tab {0}")
    public static void rightClickGraphTab(String graphTabTitle) throws Exception {
        String graphTab = patternLoc.graphTab(getPageName(),graphTabTitle);
        BrowserGlobal.iWaitUntilElementPresent(graphTab);
        BrowserGlobal.iScrollToAnElement(graphTab);
        BrowserGlobal.iRightClickOn(graphTab);
    }

    @QAFTestStep(description = "Click context menu option {0}")
    public static void clickContextMenuOption(String graphTabTitle) throws Exception {
        String sheet = patternLoc.contextMenuOption(getPageName(),graphTabTitle);
        BrowserGlobal.iWaitUntilElementPresent(sheet);
        BrowserGlobal.iScrollToAnElement(sheet);
        BrowserGlobal.iClickOn(sheet);
    }

    @QAFTestStep(description = "I verify sheets are present")
    public static void verifySheetsPresent(String page,String title,String[]sheetnameinUI) throws Exception {
        verifyPageWithPartialTitle(page,title);
        for (String sheetName : sheetnameinUI) {
            boolean sheetNameStatus = false;
            List<QAFWebElement> list = new WebDriverTestBase().getDriver().findElements(patternLoc.link(page, sheetName));

            for (QAFWebElement element : list) {
                if (element.getText().equals(sheetName)) {
                    sheetNameStatus = true;
                    break;
                }
            }
            if (sheetNameStatus) {
                Validator.verifyTrue(true, "Sheet found! '" + sheetName + "'", "Sheet found! '" + sheetName + "'");
            } else {
                Validator.verifyFalse(true, "Sheet not found! '" + sheetName + "'", "Sheet not found! '" + sheetName + "'");
            }
        }
    }

    /**
     *
     * @return [Page Name]
     */
    public static String getPageName() {
        return getBundle().getProperty("auto.page.name").toString();
    }


}