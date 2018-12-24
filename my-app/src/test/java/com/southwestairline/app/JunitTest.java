package com.southwestairline.app;

import org.junit.runner.RunWith;		
import org.junit.runners.Suite;		

@RunWith(Suite.class)				
@Suite.SuiteClasses({
  HomePageTest.class,
  FlightPageTest.class,
  FlightSearchResultsPageTest.class, 
  TripandPriceDetailsPageTest.class
})		

public class JunitTest {				
			// This class remains empty, it is used only as a holder for the above annotations		
}
