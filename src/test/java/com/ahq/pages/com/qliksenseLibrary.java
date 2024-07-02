package com.ahq.pages.com;

import com.ahq.addons.patternLoc;
import com.ahq.globals.BrowserGlobal;
import com.ahq.globals.web;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.qmetry.qaf.automation.util.Validator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ahq.globals.BrowserGlobal.*;
import static com.ahq.globals.web.*;

import static com.ahq.utils.utilsFilesystem.*;
import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;

public class qliksenseLibrary {
    @QAFTestStep(description = "I click on sheet {0}")
    public static void iClickOnTheSheet(String field) throws Exception {
        web.clickLink(patternLoc.sheet("STAN", field));
    }

    @QAFTestStep(description = "I click on dropdown {0}")
    public static void iClickOnDropdown(String dropdownLabel) throws Exception {
        clickOnDropdown(dropdownLabel, "STAN");
    }
    public static void clickOnDropdown(String option,String page) throws Exception {
        BrowserGlobal.iWaitUntilElementPresent(patternLoc.dropdown(page,option));
        BrowserGlobal.iScrollToAnElement(patternLoc.dropdown(page,option));
        BrowserGlobal.iClickOn(patternLoc.dropdown(page,option));
    }

    @QAFTestStep(description = "I get the data of the KPI with {0} label")
    public static String igetKpi(String data_label) throws Exception {
        WebElement element = appUtills.iGetWebElement(patternLoc.dataLabel("STAN", data_label));
        String data = element.getText();
        return data;
    }

    @QAFTestStep(description = "I click on {0} sheet")
    public static void iClickOnSheet(String field) throws Exception {
        BrowserGlobal.iClickOn(patternLoc.sheet("Stan",field));
        BrowserGlobal.iWaitForPageToLoad();
    }

    @QAFTestStep(description = "I select filters {filters} and click {confirm}")
    public static void clickListOfFilter(String filter,String confirm) throws Exception {
        iWaitForPageToLoad();
        // Parse the filter string into a map
        Map<String, String> filterMap = parseFilterString(filter);

        // Loop through the key-value pairs and apply the filters
        for (Map.Entry<String, String> entry : filterMap.entrySet()) {
            String label = entry.getKey();
            String filterValue = entry.getValue();

            iClickOnFilterName(label,filterValue,confirm);
        }
    }

    private static Map<String, String> parseFilterString(String filter) {
        // Example filter string: "[Year:2024,Month:Aug]"
        filter = filter.replace("[", "").replace("]", "").replace("\"", "");
        String[] keyValuePairs = filter.split(",");

        // Create a map to store the key-value pairs
        Map<String, String> filterMap = new HashMap<>();

        // Loop through the pairs and split by colon to get keys and values
        for (String pair : keyValuePairs) {
            String[] entry = pair.split(":");
            if (entry.length == 2) {
                String key = entry[0].trim();
                String value = entry[1].trim();
                filterMap.put(key, value);
            }
        }

        return filterMap;
    }

    @QAFTestStep(description = "I click on {label} filter and select {filter} and click {confirm}")
    public static void iClickOnFilterName(String label,String filter, String confirm) throws Exception {
        iWaitForPageToLoad();
        iClickOn(patternLoc.card("Stan",label));
        iWaitUntilElementPresent(patternLoc.text("Stan",filter));
        iClickOn(patternLoc.text("Stan",filter));
        iClickOn(patternLoc.button("Stan",confirm));
        iTakeScreenshot();
        iWaitForPageToLoad();
    }

    @QAFTestStep(description = "I verify {filters} filters selected")
    public static void verifyFilterList(String[] filterList) throws Exception {
        for (String filter:filterList){
            verifySheetFilter(filter);
        }
    }

    @QAFTestStep(description = "I verify {filters} filter is selected")
    public static void verifySheetFilter(String filter) throws Exception {
        iWaitForPageToLoad();
        iVerifyElementPresent(patternLoc.text("STAN",filter));
    }

    @QAFTestStep(description = "I click on {label} and verify {filter} selected by default and {confirm}")
    public static void verifyYearsheetFilterselectedbydefault(String label,String filter, String confirm) throws Exception {
        iWaitForPageToLoad();
        iClickOn(patternLoc.card("Stan",label));
        iWaitUntilElementPresent(patternLoc.text("Stan",filter));
        iTakeScreenshot();
        iClickOn(patternLoc.button("Stan",confirm));
    }

    @QAFTestStep(description = "I right click on {chart} and navigate {menu} to {subMenu} to {option} and verify and click link text {linkText}")
    public static void downloadChart(String chart, String menu, String submenu, String option, String linkText) throws Exception {
        rightClickGraphTab(chart);
        clickContextMenuOption(menu);
        clickContextMenuOption(submenu);
        clickContextMenuOption(option);
        iWaitForSeconds("3");
        iAssertTextPresentInPage(linkText);
        clickLink(linkText);
        iWaitForSeconds("3");
        Robot robot = new Robot();
        robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
        robot.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
        iWaitForSeconds("10");
        iTakeScreenshot();
    }

    @QAFTestStep(description = "I verify latest file downloaded in {filepath} matches {KPI} KPI")
    public static void verifyExcelMatch(String filepath, String kpi) throws Exception {
        String filename = getLatestDownloadedFileName(filepath);

        HashMap<String,String> hashMap = new HashMap(appUtills.readExcelFile(filepath+filename,0));
        String val = hashMap.get("Contribution");

        Validator.verifyTrue(val.equals(kpi),"Webpage KPI: " + kpi + " does not match excel: " + val,"Webpage KPI: " + kpi + " matches excel: " + val);

    }

    @QAFTestStep(description = "I mouseover to {label} right click to select {downloadas} and select {data}")
    public static void iDownloadfile(String label, String downloadas, String data) throws Exception {
        iMouseoverOn(patternLoc.text("Stan",label));
        BrowserGlobal.iRightClickOn(patternLoc.text("Stan",label));
        web.waitInSecs("5");
        iTakeScreenshot();
        iClickOn(patternLoc.text("Stan",downloadas));
        iTakeScreenshot();
        iClickOn(patternLoc.text("Stan",data));
        web.waitInSecs("10");
        iTakeScreenshot();

    }

    @QAFTestStep(description = "I verify {Export} popup window displayed with message {message}")
    public static void iVerifydownloadpopdisplayed(String label, String message) throws Exception {
        iVerifyElementPresent (patternLoc.text("Stan",label));
        iVerifyElementPresent (patternLoc.text("Stan",message));
    }

    @QAFTestStep(description = "{page}: I click on {Export} after download complete click {Close}")
    public static void iclickonfilelink(String page,String link,String button) throws Exception {
        iClickOn (patternLoc.link(page,link));
        stanLibrary.iSaveThePdfFile();
        web.waitInSecs("5");
        iTakeScreenshot();
        iClickOn(patternLoc.button("Stan",button));

    }

}
