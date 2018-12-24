package com.southwestairline.app;

import static org.junit.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.southwestairline.app.pages.flight.AirPortList;
import com.southwestairline.app.pages.flight.FlightPage;

import io.github.pramcharan.wd.binary.downloader.WebDriverBinaryDownloader;
import io.github.pramcharan.wd.binary.downloader.enums.BrowserType;

public class FlightPageTest {
	private static final Logger LOG = LogManager.getLogger(FlightPageTest.class);

    private WebDriver driver;
    private FlightPage flightPage;
    

    @Before
    public void setup() {
    	WebDriverBinaryDownloader.create().downloadLatestBinaryAndConfigure(BrowserType.FIREFOX);
        driver = new FirefoxDriver();
        flightPage = new FlightPage(driver);
        driver.manage().window().maximize();
        driver.get("https://www.southwest.com/");
    }

    
    @After
    public void cleanup() {
        if(null != driver) {
            driver.quit();
        }
    }

    // Assert and verify the airpot is selected using airport code 
    @Test
    public void testDepartingCityUsingAirPortCode() throws Exception {
    	flightPage.setDepartCity("SFO");
    	assertEquals("SFO",
    			flightPage.getDepartCity());
    	
    }
    
 
    // Assert and verify the airpot is selected using airport code 
    @Test
    public void testArrivalCityUsingAirportCode() throws Exception {
    	flightPage.setArrivalCity("SFO");
    	assertEquals(
    	        "SFO",flightPage.getArrivalCity());
    	
    }
    
    // Assert and verify the departing and arrival airports are different
    @Test
    public void testDifferentDepartArrivalCities() throws Exception {
	  	flightPage.setDepartCity("SFO");
	  	assertEquals("SFO",
  			flightPage.getDepartCity());
	  	
	  	flightPage.setArrivalCity("IAD");
    	assertEquals(
    	        "IAD",flightPage.getArrivalCity());
    	
    }
    
    
    // Assert and verify the departing and arrival airports are same
    // An invalid route message " Invalid route with departure airport" will appear
    @Test
    public void testSameDepartArrivalCities() throws Exception {
	  	flightPage.setDepartCity("SFO");
	  	assertEquals("SFO",
  			flightPage.getDepartCity());
	  	
	  	flightPage.setArrivalCity("SFO"); 
	  	flightPage.waitForElementToAppear(flightPage.getInvalidRouteMesage());
	  	assertEquals("Invalid route with departure airport",
	  			flightPage.getInvalidRouteMesage().getText());
    	   	
    }

    
    // Assert and verify the departing and arrival airports are different with proper dates
    @Test
    public void testDifferentDepartArrivalCitiesWithProperDates() throws Exception {
	  	flightPage.setDepartCity("SFO");
	  	flightPage.waitForElementToAppear(flightPage.getDepartAirportList());
	  	driver.switchTo();
	  	AirPortList airPortList = new AirPortList(driver);
	  	airPortList.selectNameFromDepartingList("SFO");
	  	assertEquals("SFO",
  			flightPage.getDepartCity());
	  	
	  	flightPage.setArrivalCity("IAD");
    	flightPage.waitForElementToAppear(flightPage.getArrivalAirportList());
    	driver.switchTo();
    	airPortList.selectNameFromDepartingList("IAD");
    	assertEquals(
    	        "IAD",flightPage.getArrivalCity());
    	
    	// select a depart date from Calendar
    	flightPage.setDepartDate("01/10");
    	System.out.println("return date is " + flightPage.getDepartDate());
    	assertEquals(
    	        "01/10",flightPage.getDepartDate());
    	
    	// select a rerturn date from Calendar
    	flightPage.setReturnDate("01/15");
    	System.out.println("return date is " + flightPage.getReturnDate());
    	assertEquals(
    	        "01/15",flightPage.getReturnDate());  
    	
    }
      


}
