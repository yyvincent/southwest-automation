package com.southwestairline.app.pages.flight;

public class Flight {
	
/*	public Flight(String flightNumber, String stop, String departureTime, String arrivalTime, String duration,
			String durationTime, String businessPrice, String anytimePrice, String getAwayPrice) {
		super();
		this.flightNumber = flightNumber;
		this.stop = stop;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.duration = duration;
		this.durationTime = durationTime;
		this.businessPrice = businessPrice;
		this.anytimePrice = anytimePrice;
		this.getAwayPrice = getAwayPrice;
	}*/
	
	public Flight() {
		
	}
	
	
	private String flightNumber;
	private String stop;
	private String departureTime;
	private String arrivalTime;
	private String duration;
	private String durationTime;
	private String businessPrice;
	private String anytimePrice;
	private String getAwayPrice;
	private String departingCity;
	
	
	public String getDepartingCity() {
		return departingCity;
	}
	public void setDepartingCity(String departingCity) {
		this.departingCity = departingCity;
	}
	public String getArrivalCity() {
		return arrivalCity;
	}
	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}


	private String arrivalCity;

	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getStop() {
		return stop;
	}
	public void setStop(String stop) {
		this.stop = stop;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getDurationTime() {
		return durationTime;
	}
	public void setDurationTime(String durationTime) {
		this.durationTime = durationTime;
	}
	public String getBusinessPrice() {
		return businessPrice;
	}
	public void setBusinessPrice(String businessPrice) {
		this.businessPrice = businessPrice;
	}
	public String getAnytimePrice() {
		return anytimePrice;
	}
	public void setAnytimePrice(String anytimePrice) {
		this.anytimePrice = anytimePrice;
	}
	public String getGetAwayPrice() {
		return getAwayPrice;
	}
	public void setGetAwayPrice(String getAwayPrice) {
		this.getAwayPrice = getAwayPrice;
	}
	

}
