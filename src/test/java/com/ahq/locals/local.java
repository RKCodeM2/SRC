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

import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;


public class local {
    public static void inputSelect(String page, String fieldName, String selectValue) throws Exception{
        BrowserGlobal.iClickOn(loc.get(page,"input",fieldName));
        BrowserGlobal.iWaitForMilliseconds("500");
        BrowserGlobal.iInputInTo(selectValue, loc.get(page,"input",fieldName));
        BrowserGlobal.iPressReturnOrEnterKey();
    }

    public static void inputTextarea(String page, String fieldName, String value) throws Exception{
        getBundle().setProperty("loc.lhub.textarea.id", fieldName);
        BrowserGlobal.iInputInTo(value,"loc.lhub.comm.textarea.general");
    }
    public static void input(String page, String fieldName, String value) throws Exception{
        getBundle().setProperty("loc.lhub.input.id", fieldName);
        BrowserGlobal.iInputInTo(value,"loc.lhub.comm.input.general");
    }
    public static void clickRadioButton(String page, String fieldName, String radioValue) throws Exception{
        BrowserGlobal.iClickOn("xpath=//div[@id='"+fieldName+"']//label//span[contains(text(),'"+radioValue.trim()+"')]");
    }

    public static void clickButton(String buttonName) throws Exception{
        getBundle().setProperty("loc.lhub.button.name", buttonName.trim());
        getBundle().setProperty("loc.lhub.button.instance", "1");
        BrowserGlobal.iClickOn("loc.lhub.comm.button.general");
    }
    public static void clickButtonInstance(String buttonName,String buttonInstance) throws Exception{
        getBundle().setProperty("loc.lhub.button.name", buttonName.trim());
        getBundle().setProperty("loc.lhub.button.instance", buttonInstance);
//        BrowserGlobal.iMouseoverOn("loc.lhub.comm.button.general");
        BrowserGlobal.iWaitForSeconds("1");
        BrowserGlobal.iClickOn("loc.lhub.comm.button.general");
    }
    public static void addSupportDocuments(String page, String documentNames) throws Exception{
         String[] docNames =  documentNames.split(",");
        for (String dName: docNames) {
            local.clickButton("Add");
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
    public static void clickSwitchButton(String page, String clickStatus, String instance) throws Exception{
        if (!instance.equals("1")) {
            BrowserGlobal.iWaitForMilliseconds("5000");
        }
        if (clickStatus.equalsIgnoreCase("Yes")) {
            BrowserGlobal.iClickOn("xpath=(//span[@class='ant-switch-inner'])["+instance+"]");
        }
//        BrowserGlobal.iClickOn("xpath=(//span[@class='ant-switch-inner'])["+instance+"]"); // Clicking on
    }

    public static void inputFormula(String page, String instance, String text) throws Exception{
        BrowserGlobal.iClickOn("xpath=(//span[@class='ant-switch-inner'])["+instance+"]"); // Clicking on
    }

}
