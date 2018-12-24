package com.southwestairline.app.pages.flight;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.southwestairline.app.pages.BasePage;

/* This class is used for the flight search result page */
public class FlightSearchResultsPage extends BasePage {
	public FlightSearchResultsPage(WebDriver driver) {
        super(driver);   
	}
	
	@FindBy(xpath = "//*[@id=\"air-booking-product-0\"]/div[4]/div[1]/div[1]/div/div/div/span/div/div/fieldset/label/button/span/div/div[2]")
	private WebElement nonStopButtonForDepartureTrip;
	

	@FindBy(xpath = "//*[@id=\"air-booking-product-1\"]/div[4]/div[1]/div[1]/div/div/div/span/div/div/fieldset/label[1]/button/span/div/div[2]")
	private WebElement nonStopButtonForReturnTrip;  
	
	// depart flight
	@FindBy(xpath = "//*[@id=\"heading-9\"]/span/span[2]")
	private WebElement departFlightAirport;

	// return flight
	@FindBy(xpath = "//*[@id=\"air-booking-product-1\"]/div[1]")
	private WebElement returnFlightAirport;
	
	//header summary
	@FindBy(css = ".header-product-summary")
	private WebElement tripSummaryHeader;
	

	// departure date
	@FindBy(xpath = "//*[@id=\"air-booking-product-0\"]/div[3]/div[1]/ul/li[3]/div/span")
	private WebElement departureDate;
	
	// return date
	@FindBy(xpath = "//*[@id=\"air-booking-product-1\"]/div[3]/div[1]/ul/li[3]/div/span")
	private WebElement returnDate;
	
	// All departure flights
	@FindBy(xpath = "//*[@id=\"air-booking-product-0\"]/div[5]/span/ul")
	private WebElement departureFlightSection;
	
	// All return flights
	@FindBy(xpath = "//*[@id=\"air-booking-product-1\"]/div[5]/span/ul")
	private WebElement returnFlightSection;
	
	// Select a departing flight
	@FindBy(xpath = "//*[@id=\"air-booking-fares-1-1\"]/div[3]/button")
	private WebElement selectedDepartureFlight;
	
	// Select a return flight
	@FindBy(xpath = "//*[@id=\"air-booking-fares-1-1\"]/div[3]/button")
	private WebElement selectedReturnFlight;  
	
	// Continue button
	@FindBy(xpath = "//*[@id=\"air-booking-product-2\"]")
	private WebElement continueButton;
		
		
	// departure and destination airports
	public String getDepartureAirports() {
		return departFlightAirport.getText();
	}
	
	// get trip summary header
	public WebElement getTripSummaryHeader() {
		return tripSummaryHeader;
	}
	
	public String getDepartureDate () {
		return departureDate.getText();		
	}
	
	public String getReturnDate () {
		return returnDate.getText();		
	}
	
	// select nonstop for departure flights
	public void selectNonstopForDeparture() {
		if (!nonStopButtonForDepartureTrip.isSelected()) {
			nonStopButtonForDepartureTrip.click();
		}
	}
	
	public WebElement getNonstopButtonForDeparture() {
		return nonStopButtonForDepartureTrip;
	}
	
	// select nonstop for return flights
	public void selectNonstopForReturn() {
		if (!nonStopButtonForReturnTrip.isSelected()) {
			nonStopButtonForReturnTrip.click();
	
		}
	}
	
	public WebElement getNonstopButtonForReturn() {
		return nonStopButtonForReturnTrip;
	}
		
	// get all departure flights
	public Map<String,Flight>  getAllDepartureFlights() {
		Map<String,Flight>  allFlights = new  HashMap<String,Flight> ();
		waitForElementToAppear(departureFlightSection);
		List<WebElement> flights = departureFlightSection.findElements(By.tagName("li"));
		if (!flights.isEmpty()) {
		    for (WebElement flight:flights) {
		    	String flightInformation = flight.getText();
		    	String[] flightInformationElements = flightInformation.split("\n");
		    	for (int j = 0; j < flightInformationElements.length; j++) {
		    		System.out.println(flightInformationElements[j]);
		    	}
		    	Flight newFlight = new Flight();
		    	String flightNumber = flightInformationElements[0].trim();
		    	newFlight.setFlightNumber(flightNumber);
		    	newFlight.setStop(flightInformationElements[1].trim());
		    	newFlight.setDepartureTime(flightInformationElements[2].trim());
		    	newFlight.setArrivalTime(flightInformationElements[3].trim());
		    	newFlight.setDuration(flightInformationElements[4].trim());
		    	newFlight.setDurationTime(flightInformationElements[5].trim());
		    	newFlight.setBusinessPrice(flightInformationElements[6].trim());
		    	newFlight.setAnytimePrice(flightInformationElements[7].trim());
		    	newFlight.setGetAwayPrice(flightInformationElements[8].trim());
		    	if (!allFlights.containsKey(flightNumber))
		    		allFlights.put(flightNumber , newFlight);
		    }
		}
		return allFlights;
	}
	
	// get all return flights
	public Map<String,Flight>  getAllReturnFlights() {
		Map<String,Flight>  allFlights = new  HashMap<String,Flight> ();
		waitForElementToAppear(returnFlightSection);
		List<WebElement> flights = returnFlightSection.findElements(By.tagName("li"));
		if (!flights.isEmpty()) {
		    for (WebElement flight:flights) {
		    	String flightInformation = flight.getText();
		    	String[] flightInformationElements = flightInformation.split("\n");
		    	for (int j = 0; j < flightInformationElements.length; j++) {
		    		System.out.println(flightInformationElements[j]);
		    	}
		    	Flight newFlight = new Flight();
		    	String flightNumber = flightInformationElements[0].trim();
		    	newFlight.setFlightNumber(flightNumber);
		    	newFlight.setStop(flightInformationElements[1].trim());
		    	newFlight.setDepartureTime(flightInformationElements[2].trim());
		    	newFlight.setArrivalTime(flightInformationElements[3].trim());
		    	newFlight.setDuration(flightInformationElements[4].trim());
		    	newFlight.setDurationTime(flightInformationElements[5].trim());
		    	newFlight.setBusinessPrice(flightInformationElements[6].trim());
		    	newFlight.setAnytimePrice(flightInformationElements[7].trim());
		    	newFlight.setGetAwayPrice(flightInformationElements[8].trim());
		    	if (!allFlights.containsKey(flightNumber))
		    		allFlights.put(flightNumber , newFlight);
		    }
		}
		return allFlights;
	}
	
	// Check if all departure Flights are non stop
	public boolean allDepartureFlightsNonstop() {
		Map<String,Flight>  allflights = getAllDepartureFlights();
		for (String flightNumber:allflights.keySet() ) {
			Flight flight = allflights.get(flightNumber);
			if (!flight.getStop().equalsIgnoreCase("nonstop"))
				return false;			
		}
		
		return true;
	}
		
	// Check if all departure Flights are non stop
	public boolean allReturnFlightsNonstop() {
		Map<String,Flight>  allflights = getAllReturnFlights();
		for (String flightNumber:allflights.keySet() ) {
			Flight flight = allflights.get(flightNumber);
			if (!flight.getStop().equalsIgnoreCase("nonstop"))
				return false;			
		}
			
			return true;
	}
	
	// select departing and return flights, go to trip plan detail page
	public TripandPriceDetailsPage goToTripandPriceDetailsPage() {
		selectedDepartureFlight.click();
		selectedReturnFlight.click();
		continueButton.click();
		return new TripandPriceDetailsPage(driver);
	}
	
	
	
}