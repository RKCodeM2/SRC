package com.ahq.pages.stan;

import com.ahq.addons.patternLoc;
import com.ahq.globals.BrowserGlobal;
import com.ahq.globals.web;
import com.qmetry.qaf.automation.step.QAFTestStep;

import static com.ahq.globals.BrowserGlobal.*;

public class stanTourismStatistics {
    @QAFTestStep(description = "stanTourismStatistics: I Click on {0} Statistics Link")
    public void clickOnStatisticslink(String link) throws Exception {
        web.clickLink(link);
    }

    /* @Author Winson */
    @QAFTestStep(description = "{page}: I navigate to {menu} and click {submenu}")
    public static void iNavigateToSubMenuAndClick(String page, String menu,String submenu) throws Exception {
        iClickOn(patternLoc.text(page,menu));
        iWaitUntilElementVisible(patternLoc.text(page,submenu));
        iClickOn(patternLoc.text(page,submenu));
        iWaitForPageToLoad();
    }

    /* @Author Winson */
    @QAFTestStep(description = "{page}: {admin} inputs {user} email {email} into {searchfield}")
    public static void iSearchEmail(String page,String admin, String user, String email,String searchfield) throws Exception {
        iScrollToAnElement(patternLoc.input(page,searchfield));
        BrowserGlobal.iInputSearch(email,searchfield);
        iWaitForPageToLoad();
    }

    /* @Author Winson */
    @QAFTestStep(description = "{page}: I verify {email} in {header}")
    public static void iVerifyEmail(String page, String email, String header) throws Exception {
        iScrollToAnElement(patternLoc.header(page,header));
        iVerifyElementPresent(patternLoc.text(page,email));
        iWaitForPageToLoad();
        iTakeScreenshot();
    }


}
