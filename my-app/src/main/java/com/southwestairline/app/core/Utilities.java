package com.southwestairline.app.core;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Utilities {

	// Check input date with "MM/dd" is valid
	public static boolean checkDateValid(String inputDate) {
		DateFormat df = new SimpleDateFormat("MM/dd"); 
	
		try {
		   df.parse(inputDate);
		} catch (ParseException e) {
		    e.printStackTrace();
		    return false;
		}
		
		return true;
	}
	
	// Check input date with "MM/dd" is valid
	public static boolean checkDateValidAndGreaterThanToday(String inputDate) {
		DateFormat df = new SimpleDateFormat("MM/dd"); 
		
		try {
			Date testDate = df.parse(inputDate);
			String formatted = df.format(new Date());
			Date today = df.parse(formatted);
			if (testDate.equals(today) || testDate.after(today))
				return true;
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
			
			
		return true;
		}
	 
}
