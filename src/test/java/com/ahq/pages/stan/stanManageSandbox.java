package com.ahq.pages.stan;

import com.ahq.addons.patternLoc;
import com.ahq.globals.BrowserGlobal;
import com.ahq.globals.web;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.qmetry.qaf.automation.util.Reporter;
import com.qmetry.qaf.automation.util.Validator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.ahq.pages.stan.stanDataDomain;
import com.ahq.pages.com.stanLibrary;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static com.ahq.globals.BrowserGlobal.*;
import static com.ahq.pages.com.stanLibrary.iClickAction;

public class stanManageSandbox {

    @QAFTestStep(description = "{page}: I select {dropdown} dropdown with value {value}")
    public static void iSelectSandBoxAccess(String page, String dropdown, String value) throws Exception {
        iSelectDropdownWithValue(patternLoc.dropdown(page, dropdown), value);
        iWaitForPageToLoad();
    }

    @QAFTestStep(description = "{page}: I {select/reselect} Start Date:{startDate} on {startDateDropdown} and End Date:{endDate} on {endDateDropdown}")
    public static void iSelectDate(String page, String action, String startDate, String startDateDropdown, String endDate, String endDateDropdown) throws Exception {

        stanLibrary.iScrollToMiddleOfWindow();
        Robot robot = new Robot();

        // Handle the action logic with robot keys
        if (action.equals("select")) {
            if (startDate.equals("ValidDate") && endDate.equals("ValidDate")) {
                robot.keyPress(KeyEvent.VK_HOME);
                robot.keyRelease(KeyEvent.VK_HOME);
                iClickOn(patternLoc.input(page,startDateDropdown));
                // Start date: RIGHT, LEFT, ENTER
                robot.keyPress(KeyEvent.VK_RIGHT);
                robot.keyRelease(KeyEvent.VK_RIGHT);
                robot.keyPress(KeyEvent.VK_LEFT);
                robot.keyRelease(KeyEvent.VK_LEFT);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);

                iWaitForMilliseconds("500");
                iClickOn(patternLoc.input(page,endDateDropdown));
                // End date: RIGHT, ENTER
                robot.keyPress(KeyEvent.VK_RIGHT);
                robot.keyRelease(KeyEvent.VK_RIGHT);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
            } else if (startDate.equals("ValidDate") && endDate.equals("InvalidDate")) {

                robot.keyPress(KeyEvent.VK_HOME);
                robot.keyRelease(KeyEvent.VK_HOME);
                iClickOn(patternLoc.input(page,startDateDropdown));
                // Start date: RIGHT, ENTER
                robot.keyPress(KeyEvent.VK_RIGHT);
                robot.keyRelease(KeyEvent.VK_RIGHT);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);

                iWaitForMilliseconds("500");
                iClickOn(patternLoc.input(page,endDateDropdown));
                // End date: RIGHT, LEFT, ENTER
                robot.keyPress(KeyEvent.VK_RIGHT);
                robot.keyRelease(KeyEvent.VK_RIGHT);
                robot.keyPress(KeyEvent.VK_LEFT);
                robot.keyRelease(KeyEvent.VK_LEFT);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
            }
        } else if (action.equals("reselect")) {
            if (startDate.equals("ValidDate") && endDate.equals("ValidDate")) {
                robot.keyPress(KeyEvent.VK_HOME);
                robot.keyRelease(KeyEvent.VK_HOME);

                iClickOn(patternLoc.input(page,startDateDropdown));
                // Start date: LEFT, ENTER
                robot.keyPress(KeyEvent.VK_LEFT);
                robot.keyRelease(KeyEvent.VK_LEFT);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);

                iWaitForMilliseconds("500");
                iClickOn(patternLoc.input(page,endDateDropdown));
                // End date: RIGHT, ENTER
                robot.keyPress(KeyEvent.VK_RIGHT);
                robot.keyRelease(KeyEvent.VK_RIGHT);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
            }
        }
        stanLibrary.iScrollToMiddleOfWindow();
        BrowserGlobal.iTakeScreenshot();
    }

    //Verify (Single Sandbox Access) (Tested)
//    @QAFTestStep(description = "{page}: I verify text {0} present")
//    public static void iVerifyText(String page, String text) throws Exception {
//        LocalDate startLocalDate;
//        LocalDate endLocalDate;
//
//        // Determine the dates based on the new conditions
//        if (text.contains("StartDate") && text.contains("EndDate")) {
//            if (text.contains("StartDate to EndDate")) {
//                startLocalDate = LocalDate.now();
//                endLocalDate = LocalDate.now().plusDays(1);
//            } else {
//                throw new IllegalArgumentException("Invalid date format in text: " + text);
//            }
//
//            // Format the dates for verification in uppercase
//            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
//            String formattedStartDate = startLocalDate.format(outputFormatter).toUpperCase();
//            String formattedEndDate = endLocalDate.format(outputFormatter).toUpperCase();
//
//            // Replace "StartDate" and "EndDate" in the text with actual dates
//            text = text.replace("StartDate", formattedStartDate)
//                    .replace("EndDate", formattedEndDate);
//        }
//
//        // Perform verification
//        iScrollToAnElement(patternLoc.text(page, text));
//        boolean textPresent = iVerifyElementPresent(patternLoc.text(page, text));
//
//        // Use Validator to verify and print the results
//        Validator.verifyTrue(textPresent,
//                "Text not found! '" + text + "' in field: '" + page + "'",
//                "Text found! '" + text + "' in field: '" + page + "'");
//
//        iTakeScreenshot();
//    }


    //Verify (one or more Sandbox Access) (Not Tested)
    @QAFTestStep(description = "{myProfile}: I verify text {0} present")
    public static void iVerifySandboxText(String page, String text) throws Exception {
        LocalDate startLocalDate;
        LocalDate endLocalDate;
        String[] sandboxes;

        // Check if the text contains "StartDate" and "EndDate"
        if (text.contains("StartDate") && text.contains("EndDate")) {
            if (text.contains("StartDate to EndDate")) {
                startLocalDate = LocalDate.now();
                endLocalDate = LocalDate.now().plusDays(1);
            } else {
                throw new IllegalArgumentException("Invalid date format in text: " + text);
            }

            // Format the dates for verification in uppercase
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
            String formattedStartDate = startLocalDate.format(outputFormatter).toUpperCase();
            String formattedEndDate = endLocalDate.format(outputFormatter).toUpperCase();

            // Replace "StartDate" and "EndDate" in the text with actual dates
            text = text.replace("StartDate", formattedStartDate)
                    .replace("EndDate", formattedEndDate);


            // Handle multiple sandboxes
            if (text.contains(",")) {
                sandboxes = text.split(",");
                List<String> formattedSandboxes = Arrays.stream(sandboxes)
                        .map(s -> s.trim().replaceAll("StartDate", formattedStartDate).replaceAll("EndDate", formattedEndDate))
                        .collect(Collectors.toList());
                text = String.join(", ", formattedSandboxes);
            }
        }
        // Construct the full expected text with correct spaces
        String expectedText = text;

        boolean textPresent = iVerifyElementPresent(patternLoc.text(page, expectedText));

        // Use Validator to verify and print the results
        Validator.verifyTrue(textPresent,
                "Text not found! '" + expectedText + "' in page: '" + page + "'",
                "Text found! '" + expectedText + "' in page: '" + page + "'");

        iTakeScreenshot();
    }

    @QAFTestStep(description = "{0}: I verify headers title as {1}")
    public static void checkMultipleTitle(String page, String[] titles) throws Exception {
        iWaitForPageToLoad();
        for (String title : titles) {
            iScrollToAnElement(patternLoc.header(page, title));
            boolean titlePresent = iVerifyElementPresent(patternLoc.header(page, title));
            Validator.verifyTrue(titlePresent,
                    "Header title not found! '" + title + "' on page: '" + page + "'",
                    "Header title found! '" + title + "' on page: '" + page + "'");
        }
        iTakeScreenshot();
    }

    @QAFTestStep(description = "{page}: I click on {linkText} link and verify file downloaded as {expectedFileName}")
    public static void iClickAndVerifyFileDownload(String page, String linkText, String expectedFileName) throws Exception {
        // Click on the link to start the download
        web.clickLink(linkText);
        iWaitForSeconds("5");

        // Simulate pressing "ENTER" to handle the Save As dialog
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String downloadDirectory = System.getProperty("user.home") + "/Downloads/";
        // Construct the expected file path
        String expectedFilePath = Paths.get(downloadDirectory, expectedFileName).toString();
        // Wait for the file to be downloaded
        File file = new File(expectedFilePath);
        int waited = 0;
        while (!file.exists() && waited < 60 * 1000) {
            Thread.sleep(1000);
            waited += 1000;
        }

        // Verify that the file exists
        File downloadedFile = new File(expectedFilePath);
        boolean fileExists = downloadedFile.exists();
        Validator.verifyTrue(fileExists,
                "File not downloaded! Expected file: '" + expectedFilePath + "'",
                "File downloaded successfully: '" + expectedFilePath + "'");
    }

    //Temp use because result is not expected is not the same
    @QAFTestStep(description = "{0}: I switch to another tab with index {1} and verify with screenshot")
    public static void switchToTab(String page, String index) throws Exception {
        web.waitInSecs("5");
        iSwitchWindowByIndex(index);
        iWaitForPageToLoad();
        iTakeScreenshot();
        iCloseCurrentWindowOrTab();
        int tabIndex = Integer.parseInt(index) - 1; // Parse the index to integer and decrement by 1
        iSwitchWindowByIndex(String.valueOf(tabIndex));
    }

    @QAFTestStep(description = "{page}: Click on {deleteName} button of {sandboxAccess}, verify popup title is {title} and click on {confirmDelete} button")
    public static void clickDeleteButtonOfSandbox(String page, String deleteName, String[] sandboxAccessList, String title, String confirmDelete) throws Exception {
        for (String sandboxAccess : sandboxAccessList) {
            // Scroll to the sandbox access element
            iScrollToAnElement(patternLoc.text(page, page));
            web.waitInSecs("3");

            // Find the delete button for the specific sandbox access
            QAFWebElement delButton = new WebDriverTestBase().getDriver().findElement(patternLoc.button(page, deleteName));

            // Click the delete button
            delButton.click();
            iWaitForPageToLoad();

            // Wait for the popup and verify the title
            web.waitInSecs("3");
            iVerifyElementPresent(patternLoc.text(page, title));
            web.takeScreenShot();

            // Click the confirm delete button
            web.waitInSecs("3");
            iClickAction(page, confirmDelete);
        }
    }

    @QAFTestStep(description = "{page}: Click on {field} button of {sandboxAccess}")
    public static void iClickSandboxEdit(String page, String field, String sandboxAccess) throws Exception {
        iScrollToAnElement(patternLoc.text(page, page));
        web.waitInSecs("3");
        QAFWebElement locator = new WebDriverTestBase().getDriver().findElement(patternLoc.button(page, field));
        locator.click();
        iWaitForPageToLoad();
        iTakeScreenshot();
    }

    @QAFTestStep(description = "{page}: I verify text {text} present in label {labelName}")
    public static void iVerifySandboxEditValue(String page, String text, String labelName) throws Exception {
        LocalDate startLocalDate = LocalDate.now();
        LocalDate endLocalDate = startLocalDate.plusDays(1);

        // Define the date formatter
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

        // Check if the text contains "StartDate" or "EndDate" and replace accordingly
        if (text.contains("StartDate") || text.contains("EndDate")) {
            // Replace "StartDate" in the text with the actual date
            if (text.contains("StartDate")) {
                String formattedStartDate = startLocalDate.format(outputFormatter);
                text = text.replace("StartDate", formattedStartDate);
            }

            // Replace "EndDate" in the text with the actual date
            if (text.contains("EndDate")) {
                String formattedEndDate = endLocalDate.format(outputFormatter);
                text = text.replace("EndDate", formattedEndDate);
            }
        }

        // Construct the full expected text with correct spaces
        String expectedText = text;

        // Scroll to the element containing the label name
        iScrollToAnElement(patternLoc.text(page, labelName));

        // Verify the text is present
        boolean textPresent = iVerifyElementPresent(patternLoc.text(page, expectedText));

        // Use Validator to verify and print the results
        Validator.verifyTrue(textPresent,
                "Text not found! '" + expectedText + "' in label: '" + labelName + "' on page: '" + page + "'",
                "Text found! '" + expectedText + "' in label: '" + labelName + "' on page: '" + page + "'");

        iTakeScreenshot();
    }

}
