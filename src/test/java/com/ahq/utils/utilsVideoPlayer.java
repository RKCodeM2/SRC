package com.ahq.utils;

import com.ahq.addons.patternLoc;
import com.ahq.pages.com.stanLibrary;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.util.Validator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class utilsVideoPlayer {

    @QAFTestStep(description = "I assert that the video can be played in the video player")
    public static void iAssertThatVideoCanBePlayedInTheVideoPlayer() throws Exception {
        stanLibrary.waitForStanPageToLoad();
        WebDriver driver = new WebDriverTestBase().getDriver();

        try {
            // Locate the video element
            WebElement videoElement = driver.findElement(By.xpath("//video"));
            System.out.println("Video player found");

            // Click on the video element to play the video
            Actions actions = new Actions(driver);
            actions.moveToElement(videoElement).click().perform();

            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Get the current time of the video
            Long initialTime = (Long) js.executeScript("return arguments[0].currentTime;", videoElement);
            System.out.println("Initial time: " + initialTime);

            // Wait for 5 seconds
            Thread.sleep(5000);

            // Get the current time of the video again
            Double currentTime = (Double) js.executeScript("return arguments[0].currentTime;", videoElement);
            System.out.println("Current time: " + currentTime);

            // Verify if the video is playing by comparing the two times
            if (currentTime > initialTime) {
                System.out.println("The video is playing.");
            } else {
                System.out.println("The video is not playing.");
            }
            Validator.assertTrue(currentTime > initialTime, "The video is not playing.", "The video is playing.");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong + " + e.getMessage());
        }
    }
}
