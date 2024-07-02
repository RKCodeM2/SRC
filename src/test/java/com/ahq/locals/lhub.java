/*******************************************************************************
 * Copyright (c) 2023 Access HQ (NCS)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package com.ahq.locals;

import com.ahq.globals.BrowserGlobal;
import com.ahq.addons.loc;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import org.openqa.selenium.WebElement;

import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;


public class lhub {
    public static void addSupportDocuments(String page, String documentNames) throws Exception{
        String[] docNames =  documentNames.split(",");
        for (String dName: docNames) {
            BrowserGlobal.iClickOn(loc.pattern(page,"button","Add",null));
//            lhub.clickButton("Add");
//            BrowserGlobal.iClickOn("xpath=//button[@type='button']//span[text()='Add']");
            BrowserGlobal.iClickOn("xpath=//input[@data-testid='search-input']");
            BrowserGlobal.iPressControlOrCommandAByOs();
            BrowserGlobal.iInputInTo(dName,"xpath=//input[@data-testid='search-input']");
            BrowserGlobal.iWaitForMilliseconds("500");
            BrowserGlobal.iPressReturnOrEnterKey();
            BrowserGlobal.iWaitForMilliseconds("1000");
            BrowserGlobal.iClickOn("xpath=//tr[@class='ant-table-row ant-table-row-level-0']//input[@class='ant-checkbox-input']");
            BrowserGlobal.iClickOn("xpath=//button[@type='button']//span[text()='OK']");
            BrowserGlobal.iWaitForMilliseconds("2000");
        }
    }
    public static void clickSwitchButton(String page, String switchName, String clickStatus, String instance) throws Exception{
        if (clickStatus.equalsIgnoreCase("Yes")) {
//            BrowserGlobal.iWaitUntilElementNotPresentWithTimeout("xpath=//span[text()='Oops! Something went wrong...']","20");
            BrowserGlobal.iMouseoverOn(loc.pattern(page,"button.switch",switchName,instance));
            BrowserGlobal.iClickOn(loc.pattern(page,"button.switch",switchName,instance));
        }
    }
        public static void clickValidateButtonInstance(String buttonInstance) throws Exception{
//        BrowserGlobal.iWaitUntilElementEnabled(loc.patternLoc("FundingCompCreate","button","Validate",buttonInstance));
        BrowserGlobal.iMouseoverOn(loc.pattern("FundingCompCreate","button","Validate",buttonInstance));
        BrowserGlobal.iClickOn(loc.pattern("FundingCompCreate","button","Validate",buttonInstance));
    }


}
