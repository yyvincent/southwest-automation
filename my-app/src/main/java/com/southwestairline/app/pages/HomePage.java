package com.southwestairline.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends BasePage {
	public HomePage(WebDriver driver) {
        super(driver);      
	}
	
	
	/* flight tab */
	@FindBy(id = "booking-form--flight-tab")
	private WebElement flightTab;
	
    /* hotel tab */ 
    @FindBy(id = "booking-form--hotel-tab")
	private WebElement hotelTab;
    
    /* car tab */ 
    @FindBy(id = "booking-form--car-tab")
	private WebElement carTab;
	
	/*public FlightPage gotoFlightPage() {
		flightTab.click();
		return new FlightPage(driver);
	}*/
	
	public  Boolean defaultTag() {
		waitForElementToAppear(flightTab);
		if (flightTab.isSelected())
			return true;
		else 
			return false;
	}
	
	public HotelPage gotoHotelPage() {
		hotelTab.click();
		return new HotelPage(driver);
	}
	
	public CarPage gotoCarPage() {
		hotelTab.click();
		return new CarPage(driver);
	}
}
