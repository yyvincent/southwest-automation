package com.southwestairline.app.pages.flight;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.southwestairline.app.pages.BasePage;

/* This is the trip summary page */
public class TripandPriceDetailsPage extends BasePage {
	
	public TripandPriceDetailsPage(WebDriver driver)  {
		super(driver);
	}
	
	// this is the total price
	@FindBy(xpath = "//*[@id=\"Drawer_358\"]/div/div/span/div/div[3]/div/div[2]/span/span/span[2]/span[2]")
	private WebElement totalPrice; 
	
	// this is the departing flight number
	@FindBy(xpath = "//*[@id=\"Drawer_200\"]/div/div/span/div/div[1]/section/div[1]/div/div[1]/div[4]/span[2]")
	private WebElement departingFlightNumber; 

	// this is the departing flight duration
	@FindBy(xpath = "//*[@id=\"Drawer_358\"]/div/div/span/div/div[1]/section/div[1]/div/div[3]/div[4]/span[2]")
	private WebElement departingFlightDuration;
	
	// this is the departing flight price
	@FindBy(xpath = "//*[@id=\"Drawer_610\"]/div/div/span/div/div[1]/div/div/div/div/span/span[2]/span[2]")
	private WebElement departingFlightPrice;
	
	// this is the return flight number
	@FindBy(xpath = "//*[@id=\"Drawer_358\"]/div/div/span/div/div[2]/section/div[1]/div/div[1]/div[4]/span[2]")
	private WebElement returnFlightNumber; 
		
	// this is the return flight duration  
	@FindBy(xpath = "//*[@id=\"Drawer_358\"]/div/div/span/div/div[2]/section/div[1]/div/div[3]/div[4]/span[2]")
	private WebElement returnFlightDuration;
	
	// this is the return flight price
	@FindBy(xpath = "//*[@id=\"Drawer_610\"]/div/div/span/div/div[2]/div/div/div/div/span/span[2]/span[2]")
	private WebElement returnFlightPrice; 

	// get total price
	public String getTotalPrice() {
		return totalPrice.getText();
	}
	
	// get departing flight numbert
	public String getDepartFlightNumber() {
		return departingFlightNumber.getText();
	}
	
	// get departing flight duration
	public String getDepartingFightDuration() {
		return departingFlightDuration.getText();
	}
	
	// get return flight numbert
	public String getReturnFlightNumber() {
		return returnFlightNumber.getText();
	}
	
	// get return flight duration
	public String getReturnFightDuration() {
		return returnFlightDuration.getText();
	}
	
	// get departing flight price
	public String getDepartingFightPrice() {
		return departingFlightPrice.getText();
	}

	// get return flight price
	public String getReturnFightPrice() {
		return returnFlightPrice.getText();
	}
}
