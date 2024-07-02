package com.ahq.addons;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.text.CaseUtils;

import java.util.ArrayList;


import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;

public class d365Loc {
    private static String locator;
    private static String locVal;
    private static String d365Platform = getBundle().getPropertyValue("d365.platform").equalsIgnoreCase("d365.platform") ? "crm" : getBundle().getPropertyValue("d365.platform").toLowerCase();
    private static String d365PlatformVersion = getBundle().getPropertyValue("d365.platform.version").equalsIgnoreCase("d365.platform.version") ? null : "v"+getBundle().getPropertyValue("d365.platform.version").toLowerCase();

    private static String locGeneratedField = "";
    private static ArrayList<String> locGeneratedVal = new ArrayList<String>();
    private static String filedLocation = "";
    private static String fieldInstanceTagStart = "";
    private static String fieldInstanceTagEnd = "";
    private static String fieldNameFinal = "";
    private static String fieldInstance = "";
    private static String fieldAdditionalVal_1 = "";
    private static String fieldAdditionalVal_2 = "";
    private static String fieldAdditionalVal_3 = "";
    public static String loc(String page, String fieldType, String fieldName) throws Exception{

//        Boolean loclocCheck()
//        fieldName = fieldName.trim();
//        String d365Platform = getBundle().getPropertyValue("d365Loc.platform").equalsIgnoreCase("d365Loc.platform") ? "crm" : getBundle().getPropertyValue("d365Loc.platform").toLowerCase();
//        String d365PlatformVersion = getBundle().getPropertyValue("d365Loc.platform.version").equalsIgnoreCase("d365Loc.platform.version") ? null : "v"+getBundle().getPropertyValue("d365Loc.platform.version").toLowerCase();
//        locator = "loc.d365Loc." + d365Platform + "." + CaseUtils.toCamelCase(page.replaceAll("[^a-zA-Z0-9]", " "), false, ' ') + "." + CaseUtils.toCamelCase(fieldType.replaceAll("d365_", "").replaceAll("[^a-zA-Z0-9]", " "), false, ' ').trim() + "." + CaseUtils.toCamelCase(fieldName.replaceAll("[^a-zA-Z0-9]", " "), false, ' ').trim();
//        String locVal = getBundle().getPropertyValue(locator);
//        ArrayList<String> locGeneratedVal = new ArrayList<String>();
//        String[] locGeneratedVal;
//        String locGeneratedField = "";
//        if (d365Platform.equalsIgnoreCase("CRM")) {
//            if (locator.equals(locVal) || locVal.length() < 5) {
        if (locCheck(page, "temp",fieldType, fieldName)) {
//            System.out.println("====> "+ locator);
//            System.out.println("====> "+ locVal);
//                locator = "auto." + locator;
                switch (fieldType.trim().toUpperCase()) {
                    case ("LINK"):
                    case ("TAB"): {
                        locGeneratedField = "LINK/TAB Field: " + fieldName;
                        switch (d365PlatformVersion) {
                            case ("v9.1"):
                            case ("v9.2"):
                            default: {
                                locGeneratedVal.add("xpath=//a[@aria-label='" + fieldName.trim() + "']");
                                locGeneratedVal.add("xpath=//a[@title='" + fieldName.trim() + "']");
                                locGeneratedVal.add("xpath=//li[@aria-label='" + fieldName.trim() + "']");
                                break;
                            }
                        }
                        break;
                    }
                    case ("SYSTEM_VIEW"):
                    case ("HEADER"): {
                        locGeneratedField = "HEADER/SYSTEM_VIEW Field: " + fieldName;
                        switch (d365PlatformVersion) {
                            case ("v9.1"):
                            case ("v9.2"):
                                locGeneratedVal.add("xpath=//h1[@title='" + fieldName.trim() + "']");
                                locGeneratedVal.add("xpath=//button[contains(@data-id,'ViewSelector')]/descendant::span[text()='" + fieldName.trim() + "']");
                            default: {
                                locGeneratedVal.add("xpath=//h1[@aria-label='" + fieldName.trim() + "']");
                                locGeneratedVal.add("xpath=//div[@aria-label='" + fieldName.trim() + "']");
                                locGeneratedVal.add("xpath=//h1[contains(@title,'" + fieldName.trim() + "')]");
                                locGeneratedVal.add("xpath=//div[contains(@aria-label,'" + fieldName.trim() + "')]");
                                break;
                            }
                        }
                        break;
                    }
                    case ("SYSTEM_VIEW_LIST"): {
                        locGeneratedField = "SYSTEM_VIEW_LIST Field: " + fieldName;
                        switch (d365PlatformVersion) {
                            case ("v9.1"):
                                locGeneratedVal.add("xpath=//ul[@aria-label='Select a view.'][@role='listbox']//descendant::li[@aria-label='" + fieldName.trim() + "']");
                            case ("v9.2"):
                                locGeneratedVal.add("xpath=//div[@aria-label='View Options'][@role='dialog']//descendant::label[text()='" + fieldName.trim() + "']");
                            default:
                                break;
                        }
                        break;
                    }
                    case ("INPUT_TEXT"): {
                        locGeneratedField = "INPUT_TEXT Field: " + fieldName;
                        switch (d365PlatformVersion) {
                            case ("v9.1"):
                            case ("v9.2"):
                            default: {
                                locGeneratedVal.add("xpath=//input[@aria-label='" + fieldName.trim() + "']");
                                break;
                            }
                        }
                        break;
                    }
                    case ("INPUT_DATE"): {
                        locGeneratedField = "INPUT_DATE Field: " + fieldName;
                        switch (d365PlatformVersion) {
                            case ("v9.1"):
                            case ("v9.2"):
                            default: {
                                locGeneratedVal.add("xpath=//input[@aria-label='Date of "+fieldName.trim()+"']");
                                locGeneratedVal.add("xpath=//input[@aria-label='"+fieldName.trim()+"']");
                                break;
                            }
                        }
                            break;
                    }

                    case ("INPUT_TIME"): {
                        locGeneratedField = "INPUT_TIME Field: " + fieldName;
                        switch (d365PlatformVersion) {
                            case ("v9.1"):
                            case ("v9.2"):
                            default: {
                                locGeneratedVal.add("xpath=//input[@aria-label='Time of "+fieldName.trim()+"']");
                                locGeneratedVal.add("xpath=//input[@aria-label='"+fieldName.trim()+"']");
                                break;
                            }
                        }
                        break;
                    }//                    getBundle().setProperty(locator,"{\"locator\":[\"\",\"xpath=//input[@aria-label='"+fieldName.trim()+"']\"],\"desc\":\""+fieldName+" field\"}");
                    case ("INPUT_LOOKUP"): {
                        locGeneratedField = "INPUT_LOOKUP Field: " + fieldName;
                        switch (d365PlatformVersion) {
                            case ("v9.1"):
                            case ("v9.2"):
                            default: {
                                locGeneratedVal.add("xpath=//input[@aria-label='"+fieldName.trim()+", Lookup']");
                                locGeneratedVal.add("xpath=//input[@aria-label='"+fieldName.trim()+"']");
                                break;
                            }
                        }
                        break;
                    }
                    case ("INPUT_LOOKUP_VALUE"): {
                        locGeneratedField = "INPUT_LOOKUP_VALUE (Exiting Value) Field: " + fieldName;
                        String[] lookupfieldSplit = fieldName.trim().split(":");
                        String[] lookupfieldNameSplit = lookupfieldSplit[0].trim().replaceAll(" ","_").split("_");
                        switch (d365PlatformVersion) {
                            case ("v9.1"):
                            case ("v9.2"):
                            default: {
                                locGeneratedVal.add("xpath=//div[contains(@data-id,'fieldControl-LookupResultsDropdown')][@title='"+lookupfieldSplit[1].trim()+"'][contains(@id,'"+lookupfieldNameSplit[0].trim().toLowerCase()+"')]");
                                break;
                            }
                        }
                        break;
                    }
                    case ("INPUT_TABLE_FILTER"): {
                        locGeneratedField = "INPUT_TABLE_FILTER Field: " + fieldName;
                        switch (d365PlatformVersion) {
                            case ("v9.1"):
                            case ("v9.2"):
                            default: {
                                locGeneratedVal.add("xpath=(//input[@placeholder='Filter by keyword'])["+fieldName.trim()+"]");
                                locGeneratedVal.add("xpath=(//*[contains(@data-id,'quickFind_text')])["+fieldName.trim()+"]");
                                break;
                            }
                        }
                        break;
                    }
                    case ("BUTTON"): {
                        locGeneratedField = "BUTTON Field: " + fieldName;
                        switch (d365PlatformVersion) {
                            case ("v9.1"):
                            case ("v9.2"):
                            default: {
                                locGeneratedVal.add("xpath=//button[@aria-label='"+fieldName.trim()+"']");
                                locGeneratedVal.add("xpath=//button[@title='"+fieldName.trim()+"']");
                                locGeneratedVal.add("xpath=//button[contains(@title,'"+fieldName.trim()+"')]");
                                break;
                            }
                        }
                        break;
                    }

                    case ("BUTTON_POPUP"): {
                        locGeneratedField = "BUTTON_POPUP Field: " + fieldName;
                        switch (d365PlatformVersion) {
                            case ("v9.1"):
                            case ("v9.2"):
                            default: {
                                locGeneratedVal.add("xpath=//div[contains(@id,'dialogView')]/descendant::button[@aria-label='"+fieldName.trim()+"']");
                                locGeneratedVal.add("xpath=//div[contains(@id,'modalDialogView')]/descendant::button[@aria-label='"+fieldName.trim()+"']");
                                locGeneratedVal.add("xpath=//div[contains(@id,'dialogView')]/descendant::span[text()='"+fieldName.trim()+"']");
                                locGeneratedVal.add("xpath=//div[contains(@id,'modalDialogView')]/descendant::span[text()='"+fieldName.trim()+"']");
                                break;
                            }
                        }
                        break;
                    }
                    case ("BUTTON_SHOW_HIDE"): {
                        locGeneratedField = "BUTTON_SHOW_HIDE Field: " + fieldName;
                        switch (d365PlatformVersion) {
                            case ("v9.1"):
                            case ("v9.2"):
                            default: {
                                locGeneratedVal.add("xpath=//i[@title='Show/Hide "+fieldName.trim()+"']");
                                locGeneratedVal.add("xpath=//i[contains(@title='Show/Hide "+fieldName.trim()+"')]");
                                break;
                            }
                        }
                        break;
                    }
                    case ("BUTTON_TABLE_HEADER_EDIT_COLUMN"): {
                        locGeneratedField = "BUTTON_TABLE_HEADER_EDIT_COLUMN Field: " + fieldName;
                        String[] headerElementSplit = fieldName.trim().split("::");
                        switch (d365PlatformVersion) {
                            case ("v9.1"):
                            case ("v9.2"):
                            default: {
                                locGeneratedVal.add("xpath=(//div[@role='listitem'])["+headerElementSplit[1]+"][@aria-label='"+headerElementSplit[0]+"']/descendant::span[text()='"+headerElementSplit[0]+"']");
                                break;
                            }
                        }
                        break;
                    }
                    case ("SELECT"): {
                        locGeneratedField = "SELECT (Dropdown) Field: " + fieldName;
                        switch (d365PlatformVersion) {
                            case ("v9.1"):
                            case ("v9.2"):
                            default: {
                                locGeneratedVal.add("xpath=//select[@aria-label='"+fieldName.trim()+"']");
                                break;
                            }
                        }
                        break;
                    }
                    case ("HEADER_CONTROL_LIST"): {
                        locGeneratedField = "HEADER_CONTROL_LIST Field: " + fieldName;
                        switch (d365PlatformVersion) {
                            case ("v9.1"):
                            case ("v9.2"):
                            default: {
                                locGeneratedVal.add("xpath=//div[contains(@id ,'headerControlsList')]//descendant::div[text()='"+fieldName.trim()+"']");
                                break;
                            }
                        }
                        break;
                    }
                    case ("ERROR_TEXT"): {
                        locGeneratedField = "ERROR_TEXT section: " + fieldName;
                        switch (d365PlatformVersion) {
                            case ("v9.1"):
                            case ("v9.2"):
                            default: {
                                locGeneratedVal.add("xpath=//span[contains(@id,'error-message')][contains(text(),'"+fieldName.trim()+"')]");
                                break;
                            }
                        }
                        break;
                    }
                    case ("TABLE_HEADER_COLUMN"): {
                        locGeneratedField = "TABLE_HEADER_COLUMN Field: " + fieldName;
                        String[] headerElementSplit = fieldName.trim().split("::");
                        switch (d365PlatformVersion) {
                            case ("v9.1"):
                            case ("v9.2"):
                            default: {
                                locGeneratedVal.add("xpath=((//div[@class='ag-header-container'])[1]/descendant::div[@aria-colindex='"+headerElementSplit[1]+"']/descendant::div[text()='"+headerElementSplit[0]+"'])[1]");
                                locGeneratedVal.add("xpath=//div[@aria-rowindex='1'][@aria-label='Header']/descendant::div[@aria-colindex='"+headerElementSplit[1]+"'][@title='"+headerElementSplit[0]+"']");
                                break;
                            }
                        }
                        break;
                    }
                    case ("TABLE_HEADER_DROPDOWN"): {
                        locGeneratedField = "TABLE_HEADER_DROPDOWN Field: " + fieldName;
                        switch (d365PlatformVersion) {
                            case ("v9.1"):
                            case ("v9.2"):
                            default: {
                                locGeneratedVal.add("xpath=//div[contains(@class,'ms-ContextualMenu-container')]/descendant::button[@aria-label='"+fieldName+"']");
                                break;
                            }
                        }
                        break;
                    }
                    case ("TABLE_HEADER_COLUMN_SORT_UP"): {
                        locGeneratedField = "TABLE_HEADER_COLUMN_SORT_UP Field: Icon";
                        switch (d365PlatformVersion) {
                            case ("v9.1"):
                            case ("v9.2"):
                            default: {
                                locGeneratedVal.add("xpath=//div[@data-id='btnheaderselectcolumn']/following-sibling::div[@title='"+fieldName+"']/descendant::i[@data-icon-name='SortUp']");
                                break;
                            }
                        }
                        break;
                    }
                    case ("TABLE_HEADER_COLUMN_SORT_DOWN"): {
                        locGeneratedField = "TABLE_HEADER_COLUMN_SORT_DOWN Field: Icon";
                        switch (d365PlatformVersion) {
                            case ("v9.1"):
                            case ("v9.2"):
                            default: {
                                locGeneratedVal.add("xpath=//div[@data-id='btnheaderselectcolumn']/following-sibling::div[@title='"+fieldName+"']/descendant::i[@data-icon-name='SortDown']");

                                break;
                            }
                        }
                        break;
                    }
                    case ("TABLE_HEADER_COLUMN_DROPDOWN_SORT_ICON"): {
                        locGeneratedField = "TABLE_HEADER_COLUMN_DROPDOWN_SORT_ICON Field: " + fieldName;
                        switch (d365PlatformVersion) {
                            case ("v9.1"):
                            case ("v9.2"):
                            default: {
                                locGeneratedVal.add("xpath=//div[contains(@class,'ms-ContextualMenu-container')]/descendant::button[@aria-label='"+fieldName+"']/descendant::i[@data-icon-name='CheckMark']");
                                break;
                            }
                        }
                        break;
                    }
                    case ("TABLE_CELL"): {
                        locGeneratedField = "TABLE_CELL Field: " + fieldName;
                        String[] cellInfoSplit = fieldName.trim().split("::");
                        switch (d365PlatformVersion) {
                            case ("v9.1"):
                            case ("v9.2"):
                            default: {
                                locGeneratedVal.add("xpath=//div[@aria-rowindex='"+cellInfoSplit[1]+"']/descendant::div[@aria-colindex='"+cellInfoSplit[2]+"']");
                                break;
                            }
                        }
                        break;
                    }
                    case ("TABLE_CELL_VALUE"): {
                        locGeneratedField = "TABLE_CELL_VALUE Field: " + fieldName;
                        String[] cellInfoSplit = fieldName.trim().split("::");
                        switch (d365PlatformVersion) {
                            case ("v9.1"):
                            case ("v9.2"):
                            default: {
                                locGeneratedVal.add("xpath=//div[@aria-rowindex='"+cellInfoSplit[1]+"']/descendant::div[@aria-colindex='"+cellInfoSplit[2]+"']//label[@aria-label='"+cellInfoSplit[3]+"']");
                                break;
                            }
                        }
                        break;
                    }
                    case ("TABLE_OVERFLOW_MENU"): {
                        locGeneratedField = "TABLE_OVERFLOW_MENU: ";
                        switch (d365PlatformVersion) {
                            case ("v9.1"):
                            case ("v9.2"):
                            default: {
                                locGeneratedVal.add("xpath=(//button[contains(@aria-label,'More commands for')])[1]");
                                break;
                            }
                        }
                        break;
                    }
                    case ("TABLE_OVERFLOW_MENU_INSTANCE"): {
                        locGeneratedField = "TABLE_OVERFLOW_MENU_INSTANCE: " + fieldName;
                        switch (d365PlatformVersion) {
                            case ("v9.1"):
                            case ("v9.2"):
                            default: {
                                locGeneratedVal.add("xpath=(//button[contains(@aria-label,'More commands for')])["+fieldName.trim()+"]");
                                break;
                            }
                        }
                        break;
                    }
                    case ("TABLE_OVERFLOW_SUB_MENU"): {
                        locGeneratedField = "TABLE_OVERFLOW_SUB_MENU Button: " + fieldName;
                        switch (d365PlatformVersion) {
                            case ("v9.1"):
                            case ("v9.2"):
                            default: {
                                locGeneratedVal.add("xpath=//ul[@role='menu']/descendant::button[@aria-label='"+fieldName.trim()+"']");
                                break;
                            }
                        }
                        break;
                    }
                    case ("TABLE_EDIT_FILTER_PANEL_BUTTON"): {
                        locGeneratedField = "TABLE_EDIT_FILTER_PANEL_BUTTON Button: " + fieldName;
                        switch (d365PlatformVersion) {
                            case ("v9.1"):
                            case ("v9.2"):
                            default: {
                                locGeneratedVal.add("xpath=//div[contains(@class,'ms-Panel-contentInner')]/descendant::button[@title='"+fieldName.trim()+"']");
                                locGeneratedVal.add("xpath=//div[contains(@class,'ms-Panel-contentInner')]/descendant::button[@aria-label='"+fieldName.trim()+"']");
                                locGeneratedVal.add("xpath=//div[contains(@class,'ms-Panel-contentInner')]/descendant::button[text()='"+fieldName.trim()+"']");
                                break;
                            }
                        }
                        break;
                    }
                    case ("TABLE_EDIT_FILTER_PANEL_BUTTON_INSTANCE"): {
                        locGeneratedField = "TABLE_EDIT_FILTER_PANEL_BUTTON_INSTANCE Button: " + fieldName;
                        String[] fieldSplit = fieldName.trim().split(":");
                        switch (d365PlatformVersion) {
                            case ("v9.1"):
                            case ("v9.2"):
                            default: {
                                locGeneratedVal.add("xpath=(//div[contains(@class,'ms-Panel-contentInner')]/descendant::button[@title='"+fieldSplit[0].trim()+"'])["+fieldSplit[1].trim()+"]");
                                locGeneratedVal.add("xpath=(//div[contains(@class,'ms-Panel-contentInner')]/descendant::button[@aria-label='"+fieldSplit[0].trim()+"'])["+fieldSplit[1].trim()+"]");
                                break;
                            }
                        }
                        break;
                    }
                    case ("TABLE_EDIT_FILTER_PANEL_INPUT_INSTANCE"): {
                        locGeneratedField = "TABLE_EDIT_FILTER_PANEL_INPUT_INSTANCE Input: " + fieldName;
                        String[] fieldSplit = fieldName.trim().split(":");
                        switch (d365PlatformVersion) {
                            case ("v9.1"):
                            case ("v9.2"):
                            default: {
                                locGeneratedVal.add("xpath=(//div[contains(@class,'ms-Panel-contentInner')]/descendant::input[@aria-label='"+fieldSplit[0].trim()+"'])["+fieldSplit[1].trim()+"]/following-sibling::button");
                                locGeneratedVal.add("xpath=(//div[contains(@class,'ms-Panel-contentInner')]/descendant::input[@aria-label='"+fieldSplit[0].trim()+"'])["+fieldSplit[1].trim()+"]");
                                break;
                            }
                        }
                        break;
                    }
                    case ("TABLE_EDIT_FILTER_PANEL_MENU_LIST"): {
                        locGeneratedField = "TABLE_EDIT_FILTER_PANEL_MENU_LIST Menu List: " + fieldName;
                        switch (d365PlatformVersion) {
                            case ("v9.1"):
                            case ("v9.2"):
                            default: {
                                locGeneratedVal.add("xpath=//div[contains(@class,'ms-Callout-container')]/descendant::button[@name='"+fieldName.trim()+"']");
                                locGeneratedVal.add("xpath=//div[contains(@class,'ms-Callout-container')]/descendant::span[text()='"+fieldName.trim()+"']");
                                locGeneratedVal.add("xpath=//div[contains(@class,'ms-Callout-container')]/descendant::label[text()='"+fieldName.trim()+"']");
                                break;
                            }
                        }
                        break;
                    }
                    case ("SCROLL_HORIZONTAL"): {
                        locGeneratedField = "SCROLL_HORIZONTAL Bar:";
                        switch (d365PlatformVersion) {
                            case ("v9.1"):
                            case ("v9.2"):
                            default: {
                                locGeneratedVal.add("xpath=(//div[@class='ag-body-horizontal-scroll-viewport'])[1]");
                                break;
                            }
                        }
                        break;
                    }

                    //        case (""): {
//                        locGeneratedField = "XXXXXXXXXX Field: " + fieldName;
//                        switch (d365PlatformVersion) {
//                            case ("v9.1"):
//                            case ("v9.2"):
//                            default: {
//                                locGeneratedVal.add();
//                                locGeneratedVal.add();
//                                locGeneratedVal.add();
//                                locGeneratedVal.add();
//                                locGeneratedVal.add();
//                                break;
//                            }
//                        }
//                    break;
//                    }
                    default: {
                        locGeneratedVal.add("xpath=//*[@aria-label='" + fieldName.trim() + "']");
                        locGeneratedVal.add("xpath=//*[@title='" + fieldName.trim() + "']");
                        locGeneratedVal.add("xpath=//*[@placeholder='" + fieldName.trim() + "']");
                        locGeneratedVal.add("xpath=//*[@value='" + fieldName.trim() + "']");
                        locGeneratedVal.add("id=" + fieldName + "");
                        locGeneratedVal.add("id=" + fieldName.replaceAll("\\s+", "").toLowerCase());
                        locGeneratedVal.add("name=" + fieldName.replaceAll("\\s+", "").toLowerCase());
                        locGeneratedVal.add("xpath=//*[contains(@aria-label,'" + fieldName.trim() + "')]");
                        locGeneratedVal.add("xpath=//*[contains(@title,'" + fieldName.trim() + "')]");
                        locGeneratedVal.add("xpath=//*[contains(@value,'" + fieldName.trim() + "')]");
                        locGeneratedVal.add("xpath=//*[contains(@value,'" + fieldName.trim() + "')]");
                        locGeneratedVal.add("xpath=//*[contains(text(),'" + fieldName.trim() + "')]");
                    }
                }
            }
//        }


        String tempLocator = "";
        int locCounter = 0;
        for (String locGenVal : locGeneratedVal) {
            locCounter = locCounter + 1;
            tempLocator = tempLocator + ((locCounter == 1) ? "\"" + locGenVal + "\"" : ",\"" + locGenVal + "\"");
        }
        getBundle().setProperty(locator,"{\"locator\":["+tempLocator+"],\"desc\":\""+locGeneratedField+"\"}");

        // Locator Logging
        if (getBundle().getPropertyValue("d365.loc.log").trim().equalsIgnoreCase("true")) {
            System.out.println("==== AUTO GENERATED: Field Name(Given) ==> " + fieldName.trim());
            System.out.println("==== AUTO GENERATED: D365 LOCATOR ====> " + locator.replace("auto.","") + "=" + getBundle().getPropertyValue(locator));
        }
        return locator;
    }

    private static void setVariables (String argFieldLocation, String argFieldName) throws Exception{
        locator = "";
        locVal = "";
        locGeneratedField = "";
        locGeneratedVal.clear();
        fieldAdditionalVal_1="";
        fieldAdditionalVal_2="";
        fieldAdditionalVal_3="";
        String fieldLoc_main = "";
        String fieldLoc_sub = "";

        if (argFieldLocation.contains("::")){
            String[] arryFieldLocSplit = argFieldLocation.trim().split("::");
            fieldLoc_main = arryFieldLocSplit[0];
            fieldLoc_sub = arryFieldLocSplit[1];
        } else {
            fieldLoc_main = argFieldLocation;
            fieldLoc_sub = "";
        }

//        fieldInstanceTagStart = "";
//        fieldInstanceTagEnd = "";

//      Setting field location Ef: Quick Create Panel, Pop Dialog, etc.

        if (fieldLoc_main.equalsIgnoreCase("QUICK_CREATE") || fieldLoc_main.equalsIgnoreCase("QUICK CREATE")){
            switch (d365PlatformVersion) {
                case ("v9.1"):
                case ("v9.2"):
                default: { filedLocation = "//section[@data-id='quickCreateRoot']/descendant::"; break; }
            }
        }  else if (fieldLoc_main.equalsIgnoreCase("LOOKUP_RECORDS") || fieldLoc_main.equalsIgnoreCase("LOOKUP RECORDS")){
            switch (d365PlatformVersion) {
                case ("v9.1"):
                case ("v9.2"):
                default: { filedLocation = "//section[@data-id='lookupDialogRoot']/descendant::"; break; }
            }
        } else if (fieldLoc_main.equalsIgnoreCase("DIALOG_WINDOW") || fieldLoc_main.equalsIgnoreCase("DIALOG WINDOW")){
            switch (d365PlatformVersion) {
                case ("v9.1"):
                case ("v9.2"):
                default: { filedLocation = "//div[@role='dialog']/descendant::"; break; }
            }
        } else if (fieldLoc_main.equalsIgnoreCase("MAIN")){
            switch (d365PlatformVersion) {
                case ("v9.1"):
                case ("v9.2"):
                default: { filedLocation = "//div[@id='mainContent']/descendant::"; break; }
            }
        } else if (fieldLoc_main.equalsIgnoreCase("POPUP_DIALOG") || fieldLoc_main.equalsIgnoreCase("POPUP DIALOG")){
            switch (d365PlatformVersion) {
                case ("v9.1"):
                case ("v9.2"):
                default: { filedLocation = "(//div[contains(@id,'dialogView')] | //div[contains(@id,'modalDialogView')])/descendant::"; break;}
            }
        } else if (fieldLoc_main.equalsIgnoreCase("TOP_BAR")){
            switch (d365PlatformVersion) {
                case ("v9.1"): { filedLocation = "//div[@data-id='topBar']/descendant::"; break; }
                case ("v9.2"): { filedLocation = "//div[@id='topBar']/descendant::"; break; }
                default: { filedLocation = "//div[@id='topBar']/descendant::"; break; }
            }
        } else if (fieldLoc_main.equalsIgnoreCase("TOP_BAR_NOTIFICATION")){
            switch (d365PlatformVersion) {
                case ("v9.1"):
                case ("v9.2"):
                default: { filedLocation = "//div[contains(@id,'barnotificationlist')]/descendant::"; break; }
            }
        } else if (fieldLoc_main.equalsIgnoreCase("TABLE_EDIT_FILTER")){
            switch (d365PlatformVersion) {
                case ("v9.1"):
                case ("v9.2"):
                default: { filedLocation = "//div[contains(@class,'ms-Panel-contentInner')]/descendant::"; break; }
            }
        } else if (argFieldLocation.equalsIgnoreCase("NAV_LEFT")){
            switch (d365PlatformVersion) {
                case ("v9.1"): { filedLocation = "//nav[@role='navigation'][@data-id='navbar-container']/descendant::"; break; }
                case ("v9.2"): { filedLocation = "//div[@role='navigation'][@data-id='navbar-container']/descendant::"; break; }
                default: { filedLocation = "//div[@role='navigation'][@data-id='navbar-container']/descendant::"; break; }
            }
        } else if (fieldLoc_main.equalsIgnoreCase("SWITCH_APP")){
            switch (d365PlatformVersion) {
                case ("v9.1"):
                case ("v9.2"):
                default: { filedLocation = "//div[@id='AppLandingPageContentContainer']/descendant::"; break; }
            }
        }    else if (fieldLoc_main.equalsIgnoreCase("DROPDOWN_LIST")){
            switch (d365PlatformVersion) {
                case ("v9.1"):
                case ("v9.2"):
                default: { filedLocation = "//div[@aria-label='Dropdown panel']/descendant::"; break; }
            }
        } else if (fieldLoc_main.equalsIgnoreCase("DROPDOWN_MENU")){
            switch (d365PlatformVersion) {
                case ("v9.1"):
                case ("v9.2"):
                default: { filedLocation = "//ul[@role='menu']/descendant::"; break; }
            }
        }  else if (fieldLoc_main.equalsIgnoreCase("DROPDOWN_TABLE_EDIT_FILTER_MENU")){
            switch (d365PlatformVersion) {
                case ("v9.1"):
                case ("v9.2"):
                default: { filedLocation = "//div[contains(@class,'ms-Callout-container')]/descendant::"; break; }
            }
        } else if (fieldLoc_main.equalsIgnoreCase("DROPDOWN_TABLE_COLUMN")){
            switch (d365PlatformVersion) {
                case ("v9.1"):
                case ("v9.2"):
                default: { filedLocation = "//div[contains(@class,'ms-ContextualMenu-container')]/descendant::"; break; }
            }
        } else if (fieldLoc_main.equalsIgnoreCase("DROPDOWN_TAB")){
            switch (d365PlatformVersion) {
                case ("v9.1"):
                case ("v9.2"):
                default: { filedLocation = "//div[@role='menu']/descendant::"; break; }
            }
        } else if (fieldLoc_main.equalsIgnoreCase("DROPDOWN_MORE_COMMANDS") || fieldLoc_main.equalsIgnoreCase("DROPDOWN MORE COMMANDS")){
            switch (d365PlatformVersion) {
                case ("v9.1"):
                case ("v9.2"):
                default: { filedLocation = "//div[contains(@id,'OverflowButton')]/descendant::"; break; }
            }
        } else if (fieldLoc_main.equalsIgnoreCase("DROPDOWN_SYSTEM_VIEW")){
            switch (d365PlatformVersion) {
                case ("v9.1"): { filedLocation = "//ul[@aria-label='Select a view.'][@role='listbox']//descendant::"; break; }
                case ("v9.2"): { filedLocation = "//div[@aria-label='View Options'][@role='dialog']//descendant::"; break; }
                default: { filedLocation = "//div[@aria-label='View Options'][@role='dialog']//descendant::"; break; }
            }
        } else if (fieldLoc_main.equalsIgnoreCase("NONE")){
            filedLocation = "";
        } else {
            filedLocation = "//";
        }

        if (fieldLoc_sub.equalsIgnoreCase("HEADER_CONTROL_LIST")){
            switch (d365PlatformVersion) {
                case ("v9.1"):
                case ("v9.2"):
                default: { filedLocation = filedLocation + "div[contains(@id,'headerControlsList')]/descendant::"; break; }
            }
        } else if (fieldLoc_sub.equalsIgnoreCase("TABLE")){
            switch (d365PlatformVersion) {
                case ("v9.1"):
                case ("v9.2"):
                default: { filedLocation = filedLocation + "div[contains(@class,'ag-root-wrapper-body')]/descendant::"; break; }
            }
        } else if (!fieldLoc_sub.isBlank()){
            switch (d365PlatformVersion) {
                case ("v9.1"):
                case ("v9.2"):
                default: { filedLocation = filedLocation + "section[@aria-label='"+fieldLoc_sub+"']/descendant::"; break; }
            }
        }

        //div[contains(@class,'ag-root-wrapper-body')]
//         //div[@aria-label='Dropdown panel']
//      Cecking instance and additional Value
        if (argFieldName.contains("::")) {
            String[] arryFieldSplit = argFieldName.trim().split("::");
            fieldNameFinal = arryFieldSplit[0].trim();
            fieldInstanceTagStart = "(";
            fieldInstanceTagEnd = ")["+arryFieldSplit[1].trim()+"]";
            fieldInstance = arryFieldSplit[1].trim();
            if (arryFieldSplit.length > 2) {
                fieldAdditionalVal_1 = arryFieldSplit[2].trim();
            }
            if (arryFieldSplit.length > 3) {
                fieldAdditionalVal_2 = arryFieldSplit[3].trim();
            }
            if (arryFieldSplit.length > 4) {
                fieldAdditionalVal_3 = arryFieldSplit[4].trim();
            }
        } else {
            fieldNameFinal = argFieldName.trim();
            fieldInstance = "1";
            fieldInstanceTagStart = "";
            fieldInstanceTagEnd = "";
        }
    }
    private static Boolean locCheck(String page, String location, String fieldType, String argFieldName) throws Exception{
        setVariables(location,argFieldName);
//        fieldName = fieldName.trim();
        locator = "loc.d365Loc." + d365Platform + "." + CaseUtils.toCamelCase(page.replaceAll("[^a-zA-Z0-9]", " "), false, ' ') + "." + CaseUtils.toCamelCase(location.replaceAll("[^a-zA-Z0-9]", " "), false, ' ') + "." +CaseUtils.toCamelCase(fieldType.replaceAll("d365_", "").replaceAll("[^a-zA-Z0-9]", " "), false, ' ').trim() + "." + CaseUtils.toCamelCase(argFieldName.replaceAll("[^a-zA-Z0-9]", " "), false, ' ').trim();
        locVal = getBundle().getPropertyValue(locator);
        Boolean returnVal;
        if (locator.equals(locVal) || locVal.length() < 5) {
            returnVal = true;
            locator = "auto." + locator;
        } else {
            returnVal = false;
        }
        locGeneratedField = fieldType + " Field: " + argFieldName;
        return returnVal;
    }

    private static void locEntry(String argLocType,String argLocValue) throws Exception{
        String varLocValue = "";
        if (argLocType.equalsIgnoreCase("xpath")) {
            if (argLocValue.trim().charAt(0) == '/' && argLocValue.trim().charAt(1) == '/'){
                varLocValue = argLocValue.substring(2);
                varLocValue = filedLocation + varLocValue;
            } else {
                varLocValue = argLocValue;
            }
            varLocValue = varLocValue.replaceAll("<field_name>",fieldNameFinal);
            varLocValue = varLocValue.replaceAll("<field_instance>",fieldInstance);
            varLocValue = varLocValue.replaceAll("<field_additional_val_1>",fieldAdditionalVal_1);
            varLocValue = varLocValue.replaceAll("<field_additional_val_2>",fieldAdditionalVal_2);
            varLocValue = varLocValue.replaceAll("<field_additional_val_2>",fieldAdditionalVal_3);

            if (!fieldInstance.isEmpty() && NumberUtils.isParsable(fieldInstance)) {
                varLocValue = fieldInstanceTagStart + varLocValue + fieldInstanceTagEnd;
            }
            varLocValue = argLocType + "=" + varLocValue;
        } else {
            varLocValue = argLocValue;
            varLocValue = varLocValue.replaceAll("<field_name>",fieldNameFinal);
            varLocValue = varLocValue.replaceAll("<field_instance>",fieldInstance);
            varLocValue = varLocValue.replaceAll("<field_additional_val_1>",fieldAdditionalVal_1);
            varLocValue = varLocValue.replaceAll("<field_additional_val_2>",fieldAdditionalVal_2);
            varLocValue = varLocValue.replaceAll("<field_additional_val_2>",fieldAdditionalVal_3);
            varLocValue =  argLocType + "=" + varLocValue;
        }
        locGeneratedVal.add(varLocValue);
    }
    private static String locGeneration(String argFieldName) throws Exception{
        StringBuilder tempLocator = new StringBuilder();
        int locCounter = 0;
        for (String locGenVal : locGeneratedVal) {
            locCounter = locCounter + 1;
            tempLocator.append((locCounter == 1) ? "\"" + locGenVal + "\"" : ",\"" + locGenVal + "\"");
        }
        if ((tempLocator.length() > 0) && !tempLocator.toString().isBlank()){
            getBundle().setProperty(locator,"{\"locator\":["+tempLocator+"],\"desc\":\""+locGeneratedField+"\"}");
        }

        // Locator Logging
        if (getBundle().getPropertyValue("d365.loc.log").trim().equalsIgnoreCase("true")) {
            System.out.println("==== AUTO GENERATED: Field Name(Given) ==> " + argFieldName.trim());
            System.out.println("==== AUTO GENERATED: D365 LOCATOR ====> " + locator.replace("auto.","") + "=" + getBundle().getPropertyValue(locator));
        }
        return locator;
    }
    public static String link(String argPage, String argFieldLocation, String argFieldName) throws Exception{
        if (locCheck(argPage, argFieldLocation, "LINK", argFieldName)) {
            switch (d365PlatformVersion) {
                case ("v9.1"):
                case ("v9.2"):
                default: {
                    locEntry("xpath","//a[@aria-label='<field_name>']");
                    locEntry("xpath","//a[@title='<field_name>']");
                    locEntry("xpath","//li[@aria-label='<field_name>']");
                    locEntry("xpath","//li[contains(@aria-label,'<field_name>')]");
                    locEntry("xpath","//li[@title='<field_name>']");
                    locEntry("xpath","//span[text()='<field_name>']");
                    locEntry("xpath","//label[text()='<field_name>']");
                    break;
                }
            }
        }
        return locGeneration(argFieldName);
    }

    public static String tab(String argPage, String argFieldLocation, String argFieldName) throws Exception{
        if (locCheck(argPage, argFieldLocation, "TAB", argFieldName)) {
            switch (d365PlatformVersion) {
                case ("v9.1"):
                case ("v9.2"):
                default: {
                    locEntry("xpath","//ul[@role='tablist']/descendant::li[@aria-label='<field_name>']");
                    locEntry("xpath","//ul[@role='tablist']/descendant::li[contains(@aria-label,'<field_name>')]");
                    break;
                }
            }
        }
        return locGeneration(argFieldName);
    }
//    public static String systemViewHeader(String argPage, String argFieldLocation, String argFieldName) throws Exception{
//        if (locCheck(argPage, argFieldLocation, "HEADER_SYSTEM_VIEW_VERIFY", argFieldName)) {
//            switch (d365PlatformVersion) {
//                case ("v9.1"):
//                case ("v9.2"): {
//                    locEntry("xpath","//h1[@title='<field_name>']");
//                    locEntry("xpath","//button[contains(@data-id,'ViewSelector')]/descendant::span[text()='<field_name>']");
//                }
//                default: {
//                    locEntry("xpath","//h1[@aria-label='<field_name>']");
//                    locEntry("xpath","//div[@aria-label='<field_name>']");
//                    locEntry("xpath","//h1[contains(@title,'<field_name>')]");
//                    locEntry("xpath","//div[contains(@aria-label,'<field_name>')]");
//                    break;
//                }
//            }
//        }
//        return locGeneration(argFieldName);
//    }
public static String systemViewOrHeaderTitle(String argPage, String argFieldLocation, String argFieldName) throws Exception{
    if (locCheck(argPage, argFieldLocation, "HEADER_SYSTEM_VIEW_VERIFY", argFieldName)) {
        switch (d365PlatformVersion) {
            case ("v9.1"):
            case ("v9.2"): {
                locEntry("xpath", "//h1[@title='<field_name>']");
                locEntry("xpath", "//button[contains(@data-id,'ViewSelector')]/descendant::span[text()='<field_name>']");
            }
            default: {
                locEntry("xpath", "//h1[@aria-label='<field_name>']");
                locEntry("xpath", "//div[@aria-label='<field_name>']");
                locEntry("xpath", "//h1[contains(@title,'<field_name>')]");
                locEntry("xpath", "//div[contains(@aria-label,'<field_name>')]");
                break;
            }
        }
    }
    return locGeneration(argFieldName);
}
    public static String headerTitleFormPage(String argPage, String argFieldLocation, String argFieldName) throws Exception {
        if (locCheck(argPage, argFieldLocation, "HEADER_TITLE_FORM_PAGE", argFieldName)) {
            switch (d365PlatformVersion) {
                case ("v9.1"):
                case ("v9.2"):
                default: {
                    locEntry("xpath", "//h1[@data-id='header_title']");
                    locEntry("xpath", "//h1[contains(@id,'defaultDialog')]");
                    break; }
            }
        }
        return locGeneration(argFieldName);
    }


    public static String inputText(String argPage, String argFieldLocation, String argFieldName) throws Exception{
        if (locCheck(argPage, argFieldLocation, "INPUT_TEXT", argFieldName)) {
            switch (d365PlatformVersion) {
                case ("v9.1"):
                case ("v9.2"):
                default: {
                    locEntry("xpath","//input[@aria-label='<field_name>']");
                    locEntry("xpath","//input[@placeholder='<field_name>']");
                    locEntry("xpath","//input[@id='<field_name>']");
                    break;
                }
            }
        }
        return locGeneration(argFieldName);
    }
    public static String inputDate(String argPage, String argFieldLocation, String argFieldName) throws Exception{
        if (locCheck(argPage, argFieldLocation, "INPUT_DATE", argFieldName)) {
            switch (d365PlatformVersion) {
                case ("v9.1"):
                case ("v9.2"):
                default: {
                    locEntry("xpath","//input[@aria-label='Date of <field_name>']");
                    locEntry("xpath","//input[@aria-label='<field_name>']");
                    break;
                }
            }
        }
        return locGeneration(argFieldName);
    }

    public static String inputTime(String argPage, String argFieldLocation, String argFieldName) throws Exception{
        if (locCheck(argPage, argFieldLocation, "INPUT_TIME", argFieldName)) {
            switch (d365PlatformVersion) {
                case ("v9.1"):
                case ("v9.2"):
                default: {
                    locEntry("xpath","//input[@aria-label='Time of <field_name>']");
                    locEntry("xpath","//input[@aria-label='<field_name>']");
                    break;
                }
            }
        }
        return locGeneration(argFieldName);
    }

    public static String inputLookup(String argPage, String argFieldLocation, String argFieldName) throws Exception{
        if (locCheck(argPage, argFieldLocation, "INPUT_LOOKUP", argFieldName)) {
            switch (d365PlatformVersion) {
                case ("v9.1"):
                case ("v9.2"):
                default: {
                    locEntry("xpath","//input[@aria-label='<field_name>, Lookup']");
                    locEntry("xpath","//input[@aria-label='<field_name>']");
                    break;
                }
            }
        }
        return locGeneration(argFieldName);
    }

    public static String inputLookupValue(String argPage, String argFieldLocation, String argFieldName) throws Exception{
        if (locCheck(argPage, argFieldLocation, "INPUT_LOOKUP_VALUE", argFieldName)) {
            switch (d365PlatformVersion) {
                case ("v9.1"): {
                    locEntry("xpath","//div[contains(text(),'<field_name>, Lookup')]/parent::div/descendant::div[text()='<field_additional_val_1>']");
                    break;
                }
                case ("v9.2"):
                default: {
                    locEntry("xpath","//ul[@title='<field_name>']/descendant::div[contains(@data-id,'fieldControl-LookupResultsDropdown')][@title='<field_additional_val_1>']");
//                    locEntry("xpath","//div[contains(@data-id,'fieldControl-LookupResultsDropdown')][@title='<field_additional_val_1>'][contains(@id,'<field_name>')]");
//                    locEntry("xpath","//input[@aria-label='<field_name>']");
                    break;
                }
            }
        }
        return locGeneration(argFieldName);
    }
    public static String select(String argPage, String argFieldLocation, String argFieldName) throws Exception{
        if (locCheck(argPage, argFieldLocation, "SELECT_DROPDOWN", argFieldName)) {
            switch (d365PlatformVersion) {
                case ("v9.1"):
                case ("v9.2"):
                default: {
                    locEntry("xpath","//select[@aria-label='<field_name>']");
                    break;
                }
            }
        }
        return locGeneration(argFieldName);
    }
    public static String selectValue(String argPage, String argFieldLocation, String argFieldName) throws Exception{
        if (locCheck(argPage, argFieldLocation, "SELECT_DROPDOWN_VALUE", argFieldName)) {
            switch (d365PlatformVersion) {
                case ("v9.1"):
                case ("v9.2"):
                default: {
                    locEntry("xpath","//select[@aria-label='<field_name>'][@title='<field_additional_val_1>']");
                    break;
                }
            }
        }
        return locGeneration(argFieldName);
    }
    public static String button(String argPage, String argFieldLocation, String argFieldName) throws Exception{
        if (locCheck(argPage, argFieldLocation, "BUTTON", argFieldName)) {
            switch (d365PlatformVersion) {
                case ("v9.1"):
                case ("v9.2"):
                default: {
                    locEntry("xpath","//button[@aria-label='<field_name>']");
                    locEntry("xpath","//button[@title='<field_name>']");
                    locEntry("xpath","//button[@name='<field_name>']");
                    locEntry("xpath","//button[text()='<field_name>']");
                    locEntry("xpath","//button[contains(@aria-label,'<field_name>')]");
                    locEntry("xpath","//button[contains(@title,'<field_name>')]");
                    locEntry("xpath","//button/descendant::span[text()='<field_name>']");
                    locEntry("xpath","//button/descendant::label[text()='<field_name>']");
                    break;
                }
            }
        }
        return locGeneration(argFieldName);
    }
    public static String text(String argPage, String argFieldLocation, String argFieldName) throws Exception{
        if (locCheck(argPage, argFieldLocation, "TEXT", argFieldName)) {
            switch (d365PlatformVersion) {
                case ("v9.1"):
                case ("v9.2"):
                default: {
                    locEntry("xpath","//div[text()='<field_name>']");
                    locEntry("xpath","//span[text()='<field_name>']");
                    locEntry("xpath","//*[text()='<field_name>']");
                    break;
                }
            }
        }
        return locGeneration(argFieldName);
    }
    public static String fieldLock(String argPage, String argFieldLocation, String argFieldName) throws Exception{
        if (locCheck(argPage, argFieldLocation, "FIELD_LOCK", argFieldName)) {
            switch (d365PlatformVersion) {
                case ("v9.1"):
                case ("v9.2"):
                default: {
                    locEntry("xpath","//div[@aria-label='Locked <field_name>']");
                    break;
                }
            }
        }
        return locGeneration(argFieldName);
    }
    public static String tableHeader(String argPage, String argFieldLocation, String argFieldName) throws Exception{
        if (locCheck(argPage, argFieldLocation, "TABLE_HEADER", argFieldName)) {
            switch (d365PlatformVersion) {
                case ("v9.1"):
                case ("v9.2"):
                default: {
                    locEntry("xpath","((//div[@class='ag-header-container'])[1]/descendant::div[@aria-colindex='<field_additional_val_1>']/descendant::div[text()='<field_name>'])[1]");
                    locEntry("xpath","//div[@aria-rowindex='1'][@aria-label='Header']/descendant::div[@aria-colindex='<field_additional_val_1>'][@title='<field_name>']");
                    break;
                }
            }
        }
        return locGeneration(argFieldName);
    }

    public static String tableColumnSort(String argPage, String argFieldLocation, String argCellDetails) throws Exception{
        if (locCheck(argPage, argFieldLocation, "TABLE_COLUMN_SORT", argCellDetails)) {
            switch (d365PlatformVersion) {
                case ("v9.1"):
                case ("v9.2"):
                default: {
                    locEntry("xpath","//div[@aria-rowindex='1']/descendant::div[@aria-colindex='<field_additional_val_1>' and @aria-sort='<field_name>']");
                    break;
                }
            }
        }
        return locGeneration(argCellDetails);
    }

//
//                        case ("TABLE_HEADER_COLUMN_SORT_UP"): {
//        locGeneratedField = "TABLE_HEADER_COLUMN_SORT_UP Field: Icon";
//        switch (d365PlatformVersion) {
//            case ("v9.1"):
//            case ("v9.2"):
//            default: {
//                locGeneratedVal.add("xpath=//div[@data-id='btnheaderselectcolumn']/following-sibling::div[@title='"+fieldName+"']/descendant::i[@data-icon-name='SortUp']");
//                break;
//            }
//        }
//        break;
//    }
//                    case ("TABLE_HEADER_COLUMN_SORT_DOWN"): {
//        locGeneratedField = "TABLE_HEADER_COLUMN_SORT_DOWN Field: Icon";
//        switch (d365PlatformVersion) {
//            case ("v9.1"):
//            case ("v9.2"):
//            default: {
//                locGeneratedVal.add("xpath=//div[@data-id='btnheaderselectcolumn']/following-sibling::div[@title='"+fieldName+"']/descendant::i[@data-icon-name='SortDown']");
//
//                break;
//            }
//        }
//        break;
//    }


    public static String tableCell(String argPage, String argFieldLocation, String argCellDetails) throws Exception{
        if (locCheck(argPage, argFieldLocation, "TABLE_CELL_VALUE_GET", argCellDetails)) {
            switch (d365PlatformVersion) {
                case ("v9.1"):
                case ("v9.2"):
                default: {

//                    locEntry("xpath","//div[@aria-rowindex='<field_additional_val_1>']/descendant::div[@aria-colindex='<field_additional_val_2>']/descendant::div[@class='ag-header-cell-comp-wrapper']");
                    locEntry("xpath","//div[@aria-rowindex='<field_additional_val_1>']/descendant::div[@aria-colindex='<field_additional_val_2>']");
                    break;
                }
            }
        }
        return locGeneration(argCellDetails);
    }
    public static String tableCellValue(String argPage, String argFieldLocation, String argCellDetails) throws Exception{
        if (locCheck(argPage, argFieldLocation, "TABLE_CELL_VALUE_GET", argCellDetails)) {
            switch (d365PlatformVersion) {
                case ("v9.1"): {locEntry("xpath","//div[@aria-rowindex='<field_additional_val_1>']/descendant::div[@aria-colindex='<field_additional_val_2>']/label"); break;}
                case ("v9.2"):
                default: {
                    locEntry("xpath","//div[@aria-rowindex='<field_additional_val_1>']/descendant::div[@aria-colindex='<field_additional_val_2>']/descendant::div[contains(@id,'tooltip')]");
                    break;
                }
            }
        }
        return locGeneration(argCellDetails);
    }



    public static String headerControlListValue(String argPage, String argFieldLocation, String argCellDetails) throws Exception{
        if (locCheck(argPage, argFieldLocation, "HEADER_CONTROL_LIST_VALUE", argCellDetails)) {
            switch (d365PlatformVersion) {
                case ("v9.1"):
                case ("v9.2"):
                default: {
                    locEntry("xpath","(//div[contains(@id,'headerControlsList')]/descendant::div[@data-preview_orientation='column'])[<field_additional_val_1>]/div/div");
                    break;
                }
            }
        }
        return locGeneration(argCellDetails);
    }

//    public static String verifyTableCellValue(String argPage, String argFieldLocation, String argFieldName) throws Exception{
//        if (locCheck(argPage, argFieldLocation, "TABLE_HEADER", argFieldName)) {
//            switch (d365PlatformVersion) {
//                case ("v9.1"):
//                case ("v9.2"):
//                default: {
//                    locEntry("xpath","//div[@aria-rowindex='<field_additional_val_1>']/descendant::div[@aria-colindex='<field_additional_val_2>']/descendant::div[contains(@id,'tooltip')][text()='<field_additional_val_3>']");
//                    break;
//                }
//            }
//        }
//        return locGeneration(argFieldName);
//    }

    public static String scrollHorizontal(String argPage) throws Exception{
        if (locCheck(argPage, "OTHER", "SCROLL", "Scroll Horizontal")) {
            switch (d365PlatformVersion) {
                case ("v9.1"):
                case ("v9.2"):
                default: {
                    locEntry("xpath","(//div[@class='ag-body-horizontal-scroll-viewport'])[1]");
                    locEntry("xpath","(//div[contains(@style,'webkit-overflow-scrolling')])[1]");
                    break;
                }
            }
        }
        return locGeneration("Scroll Horizontal");
    }

//        case ("SCROLL_HORIZONTAL"): {
//        locGeneratedField = "SCROLL_HORIZONTAL Bar:";
//        switch (d365PlatformVersion) {
//            case ("v9.1"):
//            case ("v9.2"):
//            default: {
//                locGeneratedVal.add("xpath=(//div[@class='ag-body-horizontal-scroll-viewport'])[1]");
//                break;
//            }
//        }
//        break;
//    }

//    public static String tableEditFilterPanelButton(String page, String fieldName) throws Exception{
//        setVariables("edit_filter_panel","0");
//        fieldName = fieldName.trim();
//        if (locCheck(page, "TABLE_EDIT_FILTER_PANEL_BUTTON", fieldName)) {
//            locGeneratedField = "TABLE_EDIT_FILTER_PANEL_BUTTON Field: " + fieldName;
//            switch (d365PlatformVersion) {
//                case ("v9.1"):
//                case ("v9.2"):
//                default: {
//                    locEntry("xpath","//a[@aria-label='<field_name>']");
////                    locGeneratedVal.add("xpath=//div[contains(@class,'ms-Panel-contentInner')]/descendant::button[@title='<field_name>']");
////                    locGeneratedVal.add("xpath=//div[contains(@class,'ms-Panel-contentInner')]/descendant::button[@aria-label='<field_name>']");
//                    break;
//                }
//            }
//        }
//        return locGeneration(fieldName);
//    }

//    public static String systemViewHeader(String argPage, String argFieldLocation, String argFieldName) throws Exception{
//        clearVariables("main");
//        fieldName = fieldName.trim();
//        if (locCheck(page, "SYSTEM_VIEW_HEADER", fieldName)) {
//            locGeneratedField = "SYSTEM_VIEW_HEADER Field: " + fieldName;
//            switch (d365PlatformVersion) {
//                case ("v9.1"):
//                case ("v9.2"):
//                    locGeneratedVal.add("xpath=//h1[@title='" + fieldName.trim() + "']");
//                    locGeneratedVal.add("xpath=//button[contains(@data-id,'ViewSelector')]/descendant::span[text()='" + fieldName.trim() + "']");
//                default: {
//                    locGeneratedVal.add("xpath=//h1[@aria-label='" + fieldName.trim() + "']");
//                    locGeneratedVal.add("xpath=//div[@aria-label='" + fieldName.trim() + "']");
//                    locGeneratedVal.add("xpath=//h1[contains(@title,'" + fieldName.trim() + "')]");
//                    locGeneratedVal.add("xpath=//div[contains(@aria-label,'" + fieldName.trim() + "')]");
//                    break;
//                }
//            }
//        }
//        return locGeneration(fieldName);
//    }

}