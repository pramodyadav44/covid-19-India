package com.example.coviddailytracker.model;


public class Locations {
	
	private String state;
	
	private String country;
	
	private int totalCases;
	
	private int latestTotalCases;
	 
	public int getLatestTotalCases() {
		return latestTotalCases;
	}

	public void setLatestTotalCases(int latestTotalCases) {
		this.latestTotalCases = latestTotalCases;
	}

	public int getDiffFromPrevDay() {
		return diffFromPrevDay;
	}

	public void setDiffFromPrevDay(int diffFromPrevDay) {
		this.diffFromPrevDay = diffFromPrevDay;
	}

	private int diffFromPrevDay;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getTotalCases() {
		return totalCases;
	}

	public void setTotalCases(int totalCases) {
		this.totalCases = totalCases;
	}

	@Override
	public String toString() {
		return "Locations [state=" + state + ", country=" + country + ", totalCases=" + totalCases
				+ ", latestTotalCases=" + latestTotalCases + ", diffFromPrevDay=" + diffFromPrevDay + "]";
	}

	
	
	
	

}
