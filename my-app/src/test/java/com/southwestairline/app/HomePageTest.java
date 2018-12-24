package com.southwestairline.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;

import org.junit.Before;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.southwestairline.app.pages.HomePage;

import io.github.pramcharan.wd.binary.downloader.WebDriverBinaryDownloader;
import io.github.pramcharan.wd.binary.downloader.enums.BrowserType;

public class HomePageTest {
	private static final Logger LOG = LogManager.getLogger(HomePageTest.class);
    private WebDriver driver;
    private HomePage homePage;

    @Before
    public void setup() {
    	WebDriverBinaryDownloader.create().downloadLatestBinaryAndConfigure(BrowserType.FIREFOX);

        driver = new FirefoxDriver();
        homePage = new HomePage(driver);
        driver.manage().window().maximize();
        driver.get("https://www.southwest.com/");
    }

    @After
    public void cleanup() {
        if(null != driver) {
            driver.quit();
        }
    }


    //  Assert and verify that the browser is on the correct URL 
    @Test
    public void testCurrentUrl() {
    	assertEquals(
    	        driver.getCurrentUrl(),
    	        "https://www.southwest.com/");
   
      
    }
    
    //  On the HomePage, make sure the “Flights” tab is selected. 
    @Test
    public void testDefaultTag() {
    	assertTrue(homePage.defaultTag());
      
    }
    
    
}