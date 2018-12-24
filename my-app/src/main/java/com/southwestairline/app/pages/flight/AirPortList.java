package com.southwestairline.app.pages.flight;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import com.southwestairline.app.pages.BasePage;

public class AirPortList extends BasePage{
	public AirPortList (WebDriver driver) {
        super(driver);
	}
	
	// Select name from the departing airport list
    public void selectNameFromDepartingList(String name) throws Exception
    {
    	List<WebElement> allOptions = driver.findElements(By.id("air-city-departure-menu-item"));

    	for (WebElement e: allOptions) {
    		if (e.getText().contains(name))
    			e.click();
    	}
    }
    
	
	// Select name from the arrival airport list
    public void selectNameFromArrivalList(String name) throws Exception
    {
    	List<WebElement> allOptions = driver.findElements(By.id("air-city-arrival-menu-item"));

    	for (WebElement e: allOptions) {
    		if (e.getText().contains(name))
    			e.click();
    	}
    }
}




