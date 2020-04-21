package com.example.coviddailytracker.service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.coviddailytracker.model.Locations;
import com.example.coviddailytracker.model.States;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;




@Service
public class CovidDataServices {
	
	private static String DATA_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	
	private static String INDIA_URL = "https://api.rootnet.in/covid19-in/stats/latest";
	
	private int totalcases;
	
	private List<Locations> stats = new ArrayList<>();
	
	List<States> allStatesData = new ArrayList<>();
	
	public List<States> getAllStatesData() {
		return allStatesData;
	}

	public List<Locations> getStats() {
		return stats;
	}

	@PostConstruct
	@Scheduled(cron="* * 1 * * *")
	public void fetchAllData() throws IOException, InterruptedException {
		
		HttpClient client = HttpClient.newHttpClient();
		
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(DATA_URL))
				.build();
				
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		System.out.println(response.body());
		
		StringReader reader = new StringReader(response.body());
		
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
		for (CSVRecord record : records) {
		    Locations locations =new Locations();
		    locations.setCountry(record.get("Country/Region"));
		    locations.setState(record.get("Province/State"));
		    locations.setTotalCases(Integer.parseInt(record.get(record.size()-1)));
		    int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
		    locations.setDiffFromPrevDay(Integer.parseInt(record.get(record.size()-1))-prevDayCases);
		    stats.add(locations);
		    System.out.println(locations);
		    
		}
		
		
				
	}
	
	@PostConstruct
	@Scheduled(cron="* * 1 * * *")
	public void fetchIndianData() throws IOException, InterruptedException, ParseException {
		
		HttpClient client = HttpClient.newHttpClient();
		
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(INDIA_URL))
				.build();
		
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		JSONParser parser = new JSONParser(); 
		JSONObject json = (JSONObject) parser.parse(response.body());
		JSONObject employeeObject = (JSONObject) json.get("data");
		JSONObject states1 = (JSONObject) employeeObject.get("summary");
		totalcases = Integer.parseInt(states1.get("total").toString());
		
		JSONArray states = (JSONArray) employeeObject.get("regional");
		for (Object object : states) {
			States st = new States();
			JSONObject j = (JSONObject) object;
			System.out.println(j.get("loc").toString());
			
			  st.setName(j.get("loc").toString());
			  st.setDeaths(Integer.parseInt(j.get("deaths").toString()));
			  st.setDisChargers(Integer.parseInt(j.get("discharged").toString()));
			  st.setTotalConfirmed(Integer.parseInt(j.get("totalConfirmed").toString()));
			  allStatesData.add(st);
			 
		}
		
		
		
	}

	public int getTotalcases() {
		return totalcases;
	}
}
