package com.ahq.pages.stan;

import com.ahq.addons.patternLoc;
import com.ahq.globals.BrowserGlobal;
import com.ahq.globals.web;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.qmetry.qaf.automation.util.Validator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ahq.globals.BrowserGlobal.iPressKey;
import static com.qmetry.qaf.automation.step.CommonStep.click;
import static com.qmetry.qaf.automation.step.CommonStep.sendKeys;

public class stanHelpCentre {
    @QAFTestStep(description =  "stanHelpCentre: I verify that {0} is shown")
    public static void iVerifyContentInAccordion(String[] listOfContents) throws Exception {
        BrowserGlobal.iWaitForMilliseconds("500");
        List<QAFWebElement> elements = new WebDriverTestBase().getDriver().findElements(patternLoc.link("STAN", "sub-menu"));

        // Create a HashMap to store the expected contents
        Map<String, Boolean> expectedContentMap = new HashMap<>();
        for (String content : listOfContents) {
            expectedContentMap.put(content.trim(), Boolean.TRUE);
        }

        // Print the expected contents map
        System.out.println("Expected contents map before matching: " + expectedContentMap);

        // Print all elements' inner text
        System.out.println("Elements' inner text:");
        for (QAFWebElement element : elements) {
            String innerText = (String) new WebDriverTestBase().getDriver().executeScript("return arguments[0].textContent.trim();", element);
            System.out.println(innerText);
        }

        // Loop through the elements and match with expected contents
        for (QAFWebElement element : elements) {
            String innerText = (String) new WebDriverTestBase().getDriver().executeScript("return arguments[0].textContent.trim();", element);
            innerText = innerText.replaceAll("\\s+", " "); // Normalize whitespace
            boolean matched = false;
            for (String expectedContent : expectedContentMap.keySet()) {
                if (innerText.contains(expectedContent)) {
                    expectedContentMap.remove(expectedContent);
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                System.out.println("Unexpected text found: " + innerText);
                Validator.verifyTrue(false,
                        "Unexpected text found: " + innerText,
                        "Unexpected text found: " + innerText);
                return;
            }
        }

        // Print the expected contents map after matching
        System.out.println("Expected contents map after matching: " + expectedContentMap);
        System.out.println("Size of list: " + expectedContentMap.size());
        System.out.println("List is empty: " + expectedContentMap.isEmpty());



        // Check if all expected contents were found
        Validator.verifyTrue(expectedContentMap.isEmpty(),
                "Text match verification failed. Missing expected contents: " + expectedContentMap.keySet(),
                "All expected text matches verified successfully.");
    }

    @QAFTestStep(description =  "stanHelpCentre: I close accordion {0} and open {1}")
    public static void iCloseAndOpenAnotherAccordion(String accordionToClose, String accordionToOpen) throws Exception {
        web.clickAccordion(accordionToClose);
        web.clickAccordion(accordionToOpen);
    }
    private static List<WebElement> getVideoElements(String sectionName) throws Exception {
        QAFWebElement videoSection = new WebDriverTestBase().getDriver().findElement(patternLoc.header("STAN", sectionName));

        // Navigate to its immediate sibling ul element
        QAFWebElement videoList = (QAFWebElement) videoSection.findElement(By.xpath("following-sibling::ul[1]"));

        return videoList.findElements(By.xpath("li/a"));
    }

    @QAFTestStep(description = "stanHelpCentre: I verify that {0} section has {1} videos")
    public static void iVerifyVideosInSection(String sectionName, String[] videoNames) throws Exception {
        List<WebElement> videos = getVideoElements(sectionName);

        System.out.println("Videos found under the section " + sectionName + ":");
        for (WebElement video : videos) {
            String videoTitle = video.getText().trim();
            System.out.println(videoTitle);
        }

        for (String expectedVideoName : videoNames) {
            boolean videoFound = false;
            for (WebElement video : videos) {
                String videoTitle = video.getText().trim();
                System.out.println("Checking if " + videoTitle + " is equals " + expectedVideoName + " : " + videoTitle.equals(expectedVideoName));
                if (videoTitle.equals(expectedVideoName)) {
                    videoFound = true;
                    break;
                }
            }
            Validator.verifyTrue(videoFound,
                    "Video not found: " + expectedVideoName,
                    "Video found: " + expectedVideoName);
        }

        // Verify the total number of videos
        Validator.verifyTrue(videos.size() == videoNames.length,
                "Expected number of videos: " + videoNames.length + " but found: " + videos.size(),
                "All expected videos are present in the section: " + sectionName);
    }

    @QAFTestStep(description = "stanHelpCentre: I click on video named {0} in {1} section")
    public static void iClickOnVideoInSection(String videoName, String sectionName) throws Exception {
        List<WebElement> videos = getVideoElements(sectionName);

        System.out.println("Videos found under the section " + sectionName + ":");
        for (WebElement video : videos) {
            String videoTitle = video.getText().trim();
            System.out.println(videoTitle);
        }

        boolean videoFound = false;
        for (WebElement video : videos) {
            String videoTitle = video.getText().trim();
            if (videoTitle.equals(videoName)) {
                video.click();
                videoFound = true;
                System.out.println("Clicked on video: " + videoTitle);
                break;
            }
        }

        Validator.verifyTrue(videoFound,
                "Video not found: " + videoName,
                "Video clicked successfully: " + videoName);
    }

    @QAFTestStep(description = "stanHelpCentre: I open accordion {0} and click on link {1}")
    public static void iOpenAccordionAndClickOnLink(String accordionName, String linkName) throws Exception {
        BrowserGlobal.iScrollToTheTopOfThePage();
        web.clickAccordion(accordionName);
        web.clickLink(linkName);
    }

    @QAFTestStep(description = "stanHelpCentre: I search for {0} using the search box with ID {1}")
    public static void iClickOnSearchBoxAndFillValue(String value, String searchBoxId) throws Exception {
        String loc = patternLoc.input("STAN",searchBoxId);
        WebElement searchBox = new WebDriverTestBase().getDriver().findElement(By.id(searchBoxId));

//        WebElement searchBox = new WebDriverTestBase().getDriver().findElement(By.xpath(loc));
        click(loc);
        searchBox.clear();
        sendKeys(value, loc);
        iPressKey("ENTER");
    }

    @QAFTestStep(description = "stanHelpCentre: I assert that there are search results after executing a search")
    public static void iAssertThatThereAreSearchResultsAfterSearching() throws Exception {
        String loc = patternLoc.div("STAN", "search-form");
        BrowserGlobal.iAssertElementCssClassName(loc, "form-group flex-grow-1 mb-0");
    }

    @QAFTestStep(description = "stanHelpCentre: I assert that there are no search results after executing a search")
    public static void iAssertThatThereAreNoSearchResultsAfterSearching() throws Exception {
        String loc = patternLoc.div("STAN", "search-form");
        BrowserGlobal.iAssertElementCssClassName(loc, "form-group flex-grow-1 mb-0 has-error");
    }

    @QAFTestStep(description = "I verify accordion {0} after searching for {1}")
    public static void iVerifyAccordionIsPresentWithSearchMatch(String accordionName, String searchTerm) throws Exception {
        String remainingAccordionName = accordionName.replace(searchTerm, "");

        String searchLocator = patternLoc.accordion("STAN",remainingAccordionName);
        BrowserGlobal.iVerifyElementPresent(searchLocator);

        String searchTermLocator = patternLoc.text("STAN", searchTerm);
        BrowserGlobal.iVerifyElementPresent(searchTermLocator);
    }

    @QAFTestStep(description =  "stanHelpCentre: I verify list of headers {0} in page")
    public static void iVerifyHeadersInPage(String[] listOfHeaders) throws Exception {
        for (String header : listOfHeaders) {
            System.out.println("Header: " + header);
            List<QAFWebElement> headerElements = new WebDriverTestBase().getDriver().findElements(patternLoc.header("STAN", header));
            System.out.println("headerElements" + headerElements);
            if (!headerElements.isEmpty()) {
                new WebDriverTestBase().getDriver().executeScript("arguments[0].scrollIntoView(true);", headerElements.get(0));
            }
            Validator.verifyFalse(headerElements.isEmpty(),
                    "Header not found " + header,
                    "Header found: " + header);
        }

    }

}
