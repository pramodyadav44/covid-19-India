package com.example.coviddailytracker.model;

public class States {
	
	private String name;
	
	private int deaths;
	
	private int disChargers;
	
	private int totalConfirmed;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public int getDisChargers() {
		return disChargers;
	}

	public void setDisChargers(int disChargers) {
		this.disChargers = disChargers;
	}

	public int getTotalConfirmed() {
		return totalConfirmed;
	}

	public void setTotalConfirmed(int totalConfirmed) {
		this.totalConfirmed = totalConfirmed;
	}
	
	

}
