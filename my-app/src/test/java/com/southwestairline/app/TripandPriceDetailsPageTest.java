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

import com.southwestairline.app.pages.flight.AirPortList;
import com.southwestairline.app.pages.flight.Flight;
import com.southwestairline.app.pages.flight.FlightPage;
import com.southwestairline.app.pages.flight.FlightSearchResultsPage;
import com.southwestairline.app.pages.flight.TripandPriceDetailsPage;

import io.github.pramcharan.wd.binary.downloader.WebDriverBinaryDownloader;
import io.github.pramcharan.wd.binary.downloader.enums.BrowserType;

/* This class is to verify trip summary informaton */
public class TripandPriceDetailsPageTest {
	private static final Logger LOG = LogManager.getLogger(TripandPriceDetailsPageTest.class);	

    private WebDriver driver;
    private FlightPage flightPage;
    private FlightSearchResultsPage flightSearchResultPage;
    private TripandPriceDetailsPage tripandPriceDetailsPage;
    
    @Before
    public void setup() throws Exception {
    	WebDriverBinaryDownloader.create().downloadLatestBinaryAndConfigure(BrowserType.FIREFOX);
        driver = new FirefoxDriver();
        flightPage = new FlightPage(driver);
        driver.manage().window().maximize();
        driver.get("https://www.southwest.com/");
        flightPage.setDepartCity("SFO");
	  	flightPage.waitForElementToAppear(flightPage.getDepartAirportList());
	  	driver.switchTo();
	  	AirPortList airPortList = new AirPortList(driver);
	  	airPortList.selectNameFromDepartingList("SFO");
	  	assertEquals("SFO",
  			flightPage.getDepartCity());
	  	
	  	flightPage.setArrivalCity("DAL");
    	flightPage.waitForElementToAppear(flightPage.getArrivalAirportList());
    	driver.switchTo();
    	airPortList.selectNameFromDepartingList("DAL");
    	assertEquals(
    	        "DAL",flightPage.getArrivalCity());
    	
    	// select a depart date from Calendar
    	flightPage.setDepartDate("01/10");
    	assertEquals(
    	        "01/10",flightPage.getDepartDate());
    	
    	// select a rerturn date from Calendar
    	flightPage.setReturnDate("01/15");
    	assertEquals(
    	        "01/15",flightPage.getReturnDate());  
    	
    	flightSearchResultPage = flightPage.goToSearchResultPage();
    	flightSearchResultPage.waitForElementToAppear(flightSearchResultPage.getTripSummaryHeader());
    	
    	flightSearchResultPage.waitForElementToAppear(flightSearchResultPage.getNonstopButtonForDeparture());
    	flightSearchResultPage.selectNonstopForDeparture();
    
    	flightSearchResultPage.waitForElementToAppear(flightSearchResultPage.getNonstopButtonForReturn());
    	flightSearchResultPage.selectNonstopForReturn();
    	
    	
    	tripandPriceDetailsPage = flightSearchResultPage.goToTripandPriceDetailsPage();
   		
    	
    }

    @After
    public void cleanup() {
        if(null != driver) {
            driver.quit();
        }
    }
    
    // assert and verify trip details information
    @Test
    public void verifyTripDetailInformation() throws Exception {   
    	// verify depart flight informaton
    	String departFlightNumber = tripandPriceDetailsPage.getDepartFlightNumber();
    	Flight departFlight = flightSearchResultPage.getAllDepartureFlights().get(departFlightNumber);
    	assertEquals(departFlight.getGetAwayPrice(), tripandPriceDetailsPage.getDepartingFightPrice());
    	assertEquals(departFlight.getDurationTime(), tripandPriceDetailsPage.getDepartingFightDuration());
    	
    	
    	// verify return flight information
    	String returnFlightNumber = tripandPriceDetailsPage.getReturnFlightNumber();
    	Flight returnFlight = flightSearchResultPage.getAllReturnFlights().get(returnFlightNumber);
    	assertEquals(returnFlight.getGetAwayPrice(), tripandPriceDetailsPage.getReturnFightPrice());
    	assertEquals(returnFlight.getDurationTime(), tripandPriceDetailsPage.getReturnFightDuration());
    	
    	int sumPrice = Integer.parseInt(departFlight.getGetAwayPrice()) +Integer.parseInt(returnFlight.getGetAwayPrice());
    	// assert total price is correct.
    	assertTrue (sumPrice  == Integer.parseInt(tripandPriceDetailsPage.getTotalPrice()));
    }
 
    
}
