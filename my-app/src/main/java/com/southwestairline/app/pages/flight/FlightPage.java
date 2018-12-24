package com.southwestairline.app.pages.flight;

import java.time.Month;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.southwestairline.app.core.Utilities;
import com.southwestairline.app.pages.BasePage;

/* This class is used for the flight search page */
public class FlightPage extends BasePage {
	  
	public FlightPage(WebDriver driver) {
	        super(driver);
	}

	// roundtrip flag, by default it's on
	boolean roundtrip = true;
	
	// departure city 
	@FindBy(id = "air-city-departure")
	private WebElement departCityElement;

	// arrival city
	@FindBy(id = "air-city-arrival")
	private WebElement arrivalCityElement;
	
	// depart date 
	@FindBy(id = "air-date-departure")
	private WebElement departDateElement;
	
	// return date 
	@FindBy(id = "air-date-return")
	private WebElement returnDateElement;
	
	// number of passangers 
	@FindBy(id = "air-pax-count-adults")
	private WebElement numberOfPassengersElement;
	
	// number of seniors 
	@FindBy(id = "air-pax-count-seniors")
	private WebElement numberOfSeniorsElement;
	
	// search button 
	@FindBy(id = "jb-booking-form-submit-button")
	private WebElement searchButton;
	
	// promote code 
	@FindBy(id = "air-promo-code")
	private WebElement promoteCodeElement;
	
	// round trip
	@FindBy(id = "trip-type-round-trip")
	private WebElement roundtripRadioButton;
	
	// one way  
	@FindBy(id = "trip-type-one-way")
	private WebElement oneWayRadiobutton;
	
	// departing city menu 
	@FindBy(xpath = "//*[@id=\"air-city-departure-menu\"]") 
	private WebElement airportListDeparting;
	
	// arrival city menu 
	@FindBy(id = "air-city-arrival") 
	private WebElement airportListArrival;
	
	
	// Calendar
	@FindBy(xpath= "//div[@id='calendar-descendant']") 
	private WebElement Calendar;
	
	
	// Error message for airport selection: Invalid route with departure airport
	@FindBy(id = "arrival-status")
	private WebElement invalidRoute;
	
	// get the departure date
	public String getDepartDate() {
		return departDateElement.getAttribute("value");
	}

	// Check the input depart date is valid and select it from the Calendar
	public void setDepartDate(String departDate) {
		departDateElement.click();
		departDateElement.sendKeys(departDate);
		boolean validdate = Utilities.checkDateValidAndGreaterThanToday(departDate);
		if (validdate) {
			String selectMonth  = Month.of(Integer.parseInt(departDate.split("/")[0])).toString().toLowerCase();
			Calendar.findElement(By.id("calendar-" + selectMonth + "-" + departDate.split("/")[1])).click();
		}
	}

	// get the return date
	public String getReturnDate() {
		return returnDateElement.getAttribute("value");
	}

	// Check the input return date is valid and select it from the Calendar
	public void setReturnDate(String returnDate) {
		returnDateElement.click();
		returnDateElement.sendKeys(returnDate);
		boolean validdate = Utilities.checkDateValidAndGreaterThanToday(returnDate);
		if (validdate) {
			String selectMonth  = Month.of(Integer.parseInt(returnDate.split("/")[0])).toString().toLowerCase();
			Calendar.findElement(By.id("calendar-" + selectMonth + "-" + returnDate.split("/")[1])).click();
		}
	}
	
	// get the depart city
	public String getDepartCity() {
		return departCityElement.getAttribute("value");
	}

	// select the depart city from a drop down list
	public void setDepartCity(String departCity) throws Exception {
		departCityElement.click();
		departCityElement.sendKeys(departCity);
		
		waitForElementToAppear(airportListDeparting);
	  	driver.switchTo();
	  	List<WebElement> airPorts = airportListDeparting.findElements(By.id("air-city-departure-menu-item"));
	  	for (WebElement e: airPorts) {
	  	    if (e.getText().contains(departCity))
	  	    		e.click();
	  	    		break;
	  	    }
	}

	// get the arrival city
	public String getArrivalCity() {
		return arrivalCityElement.getAttribute("value");
	}

	// select the arrival city from a drop down list
	public void setArrivalCity(String arrivalCity) throws Exception {
		arrivalCityElement.click();
		arrivalCityElement.sendKeys(arrivalCity);
		
		waitForElementToAppear(airportListArrival);
	  	driver.switchTo();
	  	
	  	List<WebElement> airPorts = airportListArrival.findElements(By.id("air-city-arrival-menu-item"));
	  	for (WebElement e: airPorts) {
	  	    if (e.getText().contains(arrivalCity))
	  	    	e.click();
	  	    	break;
	  	    }
	}

	
	// get invalid route error message
	public WebElement getInvalidRouteMesage() {
		return invalidRoute;
	}


	public FlightSearchResultsPage goToSearchResultPage() {
		searchButton.click();
		return new FlightSearchResultsPage(driver);
	}

	public String getPromoteCode() {
		return promoteCodeElement.getText();
	}

	public void setPromoteCode(String promoteCode) {
		promoteCodeElement.sendKeys(promoteCode);
	}

	public void setRoundtripRadioButton() {
		roundtripRadioButton.click();
		this.roundtrip = true;
	}


	public void setOneWayRadiobutton() {
		oneWayRadiobutton.click();
		this.roundtrip = false;
	}

	public WebElement getDepartAirportList() {
		return airportListDeparting;
	}
	
	public WebElement getArrivalAirportList() {
		return airportListArrival;
	}

	public AirPortList getAllDepartAirportsList() {
		airportListDeparting.click();
		return new AirPortList(driver);
	}
	
}
