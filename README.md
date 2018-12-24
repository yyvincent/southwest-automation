# southwest-automation

This framework is used to test southwest airline flight reservation. It uses Page Object Model. In the main directory there is a page package which contains HomePage, Hotel page, Car Page and so on. For the flight tests, there is a flight package which contains all the classes related to flight reservation. All page classes extend the BasePage class.

Requirement to build and run the tests:

1. Install maven 3.5.4
2. Install Java 1.8

Clone and Download the repository.
1. Do a maven clean install
2. On your Java IDE, run the class JunitTest

Test cases documentation:

HomePageTest: This is the home page test.
1. Assert and verify that the browser is on the correct URL
2. Make sure the “Flights” tab is selected by default.

FlightPageTest: This is the flight search page test.
1. In the Depart Airport field, type in an airport code such as SFO, wait for the airport drop down list displayed, select SFO, make sure SFO is the final depart airport.
2. In the Arrive Airport field, type in an airport code such as SFO, wait for the airport drop down list displayed, select SFO, make sure SFO is the arrive airport.
3. Test different airport codes for the depart airport and the arrive airport from the airport drop down list.
4. Test same airport code for the depart airport and the arrive airport, an error message is displayed "Invalid route with departure airport".
5. Test different airport codes for the depart airport and the arrive airport from the airport drop down list, use proper departure date and arrive date, the selection is valid, no error message is displayed.

FlightSearchResultsPageTest: This is the flight search result page test.
1. On the flight search result page, all the information such as departure airport, return airport, departure date, return date is based on the input from the Flight Search Page.
2. Test if nonstop is selected for both departure and returned flights in the flight search page, only nonstop flights will be displayed in the search result page.

TripandPriceDetailsPageTest: This is the flight reservation summary detail page test.
1. Based on the selected flight numbers for departing and return flights, the duration and the price for each are verified and compared with the selection from the flight search result page. The total price is equal to the sum of the departing flight and and the return flight,



