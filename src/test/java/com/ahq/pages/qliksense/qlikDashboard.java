package com.ahq.pages.qliksense;

import com.ahq.addons.patternLoc;
import com.ahq.globals.BrowserGlobal;
import com.ahq.globals.web;
import com.qmetry.qaf.automation.step.QAFTestStep;

import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;

public class qlikDashboard {

    @QAFTestStep(description = "Temp: I switch tab to {0} and verify dashboard page title as {1}")
    public static void tempVerifyFunction(String tabIndex, String tabName) throws Exception {
        BrowserGlobal.iSwitchBrowsertabToIndex(tabIndex);
        BrowserGlobal.iWaitForSeconds("5");
        web.verifyPageWithPartialTitle("Stan", tabName);
        BrowserGlobal.iTakeScreenshot();
        BrowserGlobal.iCloseCurrentWindowOrTab();
        BrowserGlobal.iSwitchBrowsertabToIndex("1");
    }

    @QAFTestStep(description = "qlikDashboard: I switch tab to {0} and wait for Qlik Dashboard to load")
    public void iGoToAnotherTabAndWaitForQlikToLoad(String tabIndex) throws Exception {
        BrowserGlobal.iSwitchBrowsertabToIndex(tabIndex);

    }

    @QAFTestStep(description = "qlikDashboard: I verify that this is {0} dashboard")
    public void iVerifyDashboardName(String dashboardName) throws Exception {
        String dashboard = patternLoc.text("STAN",dashboardName);
        BrowserGlobal.iWaitUntilElementPresent(patternLoc.text("STAN", "app-title"));
        BrowserGlobal.iVerifyElementPresent(dashboard);
        BrowserGlobal.iCloseCurrentWindowOrTab();
        BrowserGlobal.iSwitchBrowsertabToIndex("1");
    }



    @QAFTestStep(description = "I close Qlik Dashboard")
    public void closeQlikDashboard(){

        BrowserGlobal.iCloseCurrentWindowOrTab();
    }
    private static String getPageName() {
        return getBundle().getProperty("auto.page.name").toString();
    }




    @QAFTestStep(description = "{0}: I verify {1} {2} dashboard open in another window {title}")
    public static void checkDashboard(String page,String name,String type, String title) throws Exception {
        BrowserGlobal.iSwitchWindowByIndex("1");
        web.iWaitForQlikToLoad();
        web.verifyPageWithPartialTitle(page, title);
    }


    @QAFTestStep(description = "{0}: I verify dashboard open in another window {title}")
    public static void checkTIHPerformancePublicDashboard(String page, String title) throws Exception {
        BrowserGlobal.iSwitchWindowByIndex("1");
        web.iWaitForQlikToLoad();
        web.verifyPageWithPartialTitle(page, title);

    }

    /**
     * : I switch to new dashboard for travel agent tab and verify title, sheet names
     * @param page [Current page name]
     * @param title [Page title]
     * @param sheets [Names of sheet]
     * @param dashboardname [Title of dashboard]
     */
    @QAFTestStep(description = "{page}: I verify dashboard for {dashboardname} open in another window {title} and verify {sheets} is present")
    public static void checkTravelAgentsDashboard(String page,String dashboardname, String title,String[] sheets) throws Exception {
        BrowserGlobal.iSwitchWindowByIndex("1");
        web.iWaitForQlikToLoad();
        web.verifySheetsPresent(page,title,sheets);;
    }



}
