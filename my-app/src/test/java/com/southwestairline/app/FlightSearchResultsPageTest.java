package com.southwestairline.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.southwestairline.app.pages.flight.AirPortList;
import com.southwestairline.app.pages.flight.FlightPage;
import com.southwestairline.app.pages.flight.FlightSearchResultsPage;

import io.github.pramcharan.wd.binary.downloader.WebDriverBinaryDownloader;
import io.github.pramcharan.wd.binary.downloader.enums.BrowserType;

/* This class is used for the flight search result page test */
public class  FlightSearchResultsPageTest {
	private static final Logger LOG = LogManager.getLogger(FlightSearchResultsPageTest.class);	

    private WebDriver driver;
    private FlightPage flightPage;
    

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
    }

    @After
    public void cleanup() {
        if(null != driver) {
            driver.quit();
        }
    }

    // assert and verify the airpot is selected using airport code 
    @Test
    public void testFightSearchResult() throws Exception {   
    	String bookedDepartCity = flightPage.getDepartCity();
    	LOG.error("departure city is " + bookedDepartCity);
    	String bookedDestinationCity = flightPage.getArrivalCity();
    	LOG.error("arrive city is " + flightPage.getArrivalCity());
    	String bookedDepartDate = flightPage.getDepartDate().trim();
    	LOG.error("departure date is " + flightPage.getDepartDate());
    	String bookedReturnDate = flightPage.getReturnDate().trim();
    	LOG.error("return date is " + flightPage.getReturnDate());
   	
    	FlightSearchResultsPage flightSearchResultPage = flightPage.goToSearchResultPage();
    	flightSearchResultPage.waitForElementToAppear(flightSearchResultPage.getTripSummaryHeader());
    
    	String departureAirportInformation = flightSearchResultPage.getDepartureAirports();
    	LOG.error("break111 " + departureAirportInformation);
    	String departureAirport = departureAirportInformation.substring(0,3);
    	LOG.error("departureAirport is " + departureAirport);
    	String departureDestinationAirport = departureAirportInformation.substring(3,departureAirportInformation.length());
    	LOG.error("departureDestinationAirport is " + departureDestinationAirport);
    	
    	// verify departure airport between FlightSearch Page and FlightSearchResultPage
    	assertEquals(
    			bookedDepartCity,departureAirport);
    	
    	// verify destination airport between FlightSearch Page and FlightSearchResultPage
    	assertEquals(
    			bookedDestinationCity,departureDestinationAirport);
    	
    	String departureDate = flightSearchResultPage.getDepartureDate();
     	int index = departureDate.indexOf("\n");
     	String departureDateUpdate = departureDate.substring(index+1);
    	
    	String newDepartureDate = getNewFormatDate(departureDateUpdate);
		
    	// verfiy departure date
    	assertEquals(
    			bookedDepartDate,newDepartureDate);
    	
    }
    
    // assert and verify only non stop departing and return flights are selected. 
    @Test
    public void testNonStopFlights() throws Exception {   
    	String bookedDepartCity = flightPage.getDepartCity();
    	LOG.error("departure city is " + bookedDepartCity);
    	String bookedDestinationCity = flightPage.getArrivalCity();
    	LOG.error("arrive city is " + bookedDestinationCity);
    	String bookedDepartDate = flightPage.getDepartDate().trim();
    	LOG.error("departure date is " + bookedDepartDate);
    	String bookedReturnDate = flightPage.getReturnDate().trim();
    	LOG.error("return date is " + bookedReturnDate);
   	
    	FlightSearchResultsPage flightSearchResultPage = flightPage.goToSearchResultPage();
    	flightSearchResultPage.waitForElementToAppear(flightSearchResultPage.getTripSummaryHeader());
    	
    	flightSearchResultPage.waitForElementToAppear(flightSearchResultPage.getNonstopButtonForDeparture());
    	flightSearchResultPage.selectNonstopForDeparture();
    
    	flightSearchResultPage.waitForElementToAppear(flightSearchResultPage.getNonstopButtonForReturn());
    	flightSearchResultPage.selectNonstopForReturn();
    	
    	
    	// assert all departing flights are non stop
   		assertTrue(flightSearchResultPage.allDepartureFlightsNonstop());
   		
   		// assert all return flights are non stop
   		assertTrue(flightSearchResultPage.allReturnFlightsNonstop());	
    	
    }
 
    // transform a input date to MM/dd format
    private String getNewFormatDate(String inputDate) throws ParseException {
    	DateFormat olddf = new SimpleDateFormat("MMM dd"); 
    	Date olddate = olddf.parse(inputDate);
    	
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(olddate );
    	int month = cal.get(Calendar.MONTH) + 1;
    	int day = cal.get(Calendar.DATE);
    	
    	if (month<10)
    		return "0" + month + "/" + day;
    	
		return month + "/" + day;
    }



}
