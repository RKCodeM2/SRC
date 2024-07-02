package com.ahq.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


public class pipelineSmokeTest {
    
    @Test
    public void testGoogleSearch() {
        WebDriver driver = new ChromeDriver();
        System.out.println("Navigating to Google...");
        driver.get("https://www.google.com");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("QMetry");
        System.out.println("Searching for 'QMetry'...");
        searchBox.submit();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Search complete. Closing the browser...");
        driver.quit();
    }
}
