package com.ahq.pages.stan;

import com.ahq.addons.patternLoc;
import com.ahq.globals.BrowserGlobal;
import com.ahq.globals.web;
import com.qmetry.qaf.automation.step.QAFTestStep;

import static com.ahq.globals.BrowserGlobal.*;

public class stanManageSTBUsers {

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
        iTakeScreenshot();
    }

    // Cancel Button for Data Domain Access
    @QAFTestStep(description = "{page}: I select {field} dropdown with value {value} and click {modal} cancel button")
    public static void iSelectDataDomainAccessToCancel(String page,String field, String value,String modal) throws Exception {
        iSelectDropdownWithValue(patternLoc.dropdown(page,field),value);
        iClickOn(patternLoc.button(page,modal));
        iWaitForPageToLoad();
    }

    // Cancel Button for Data Domain Access
    @QAFTestStep(description = "{page}: I click on {modal} cancel button")
    public static void cancelButton(String page, String modal) throws Exception
    {   iScrollToAnElement(patternLoc.button(page,modal));
        web.waitInSecs("5");
        iClickOn(patternLoc.button(page,modal));
        iWaitForPageToLoad();
    }

}
