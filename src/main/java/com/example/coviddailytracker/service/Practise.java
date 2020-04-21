package com.example.coviddailytracker.service;


import java.io.IOException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.example.coviddailytracker.model.States;


import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;



public class Practise {

	public static void main(String[] args) throws IOException, InterruptedException, ParseException {
		// TODO Auto-generated method stub
		
		String DATA_URL="https://api.rootnet.in/covid19-in/stats/latest";
		
		HttpClient client = HttpClient.newHttpClient();
		
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(DATA_URL))
				.build();
				
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		JSONParser parser = new JSONParser(); 
		JSONObject json = (JSONObject) parser.parse(response.body());
		JSONObject employeeObject = (JSONObject) json.get("data");
		JSONObject states1 = (JSONObject) employeeObject.get("summary");
		System.out.println(states1.get("total"));
		
		JSONArray states = (JSONArray) employeeObject.get("regional");
		
		
		List<States> allStatesData = new ArrayList<>();
		for (Object object : states) {
			States st = new States();
			JSONObject j = (JSONObject) object;
			//System.out.println(j.get("loc").toString());
			
			  st.setName(j.get("loc").toString());
			  st.setDeaths(Integer.parseInt(j.get("deaths").toString()));
			  st.setDisChargers(Integer.parseInt(j.get("discharged").toString()));
			  st.setTotalConfirmed(Integer.parseInt(j.get("totalConfirmed").toString()));
			  allStatesData.add(st);
			 
			
		}
		
		
		
//		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
//		List<Locations> country_data = new ArrayList<>();
//		List<Locations> countryWise = new ArrayList<>();
//		Set<String> country = new TreeSet<>();
//		List<String> con = new ArrayList<>();
//		List<Integer> totCase = new ArrayList<>();
//		//System.out.println(records.toString().length());
//		for (CSVRecord record : records) {
//		    Locations locations =new Locations();
//		    locations.setCountry(record.get("Country/Region"));
//		    locations.setState(record.get("Province/State"));
//		    locations.setTotalCases(Integer.parseInt(record.get(record.size()-1)));
//		    //int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
//		    //locations.setDiffFromPrevDay(Integer.parseInt(record.get(record.size()-1))-prevDayCases);
//		    //stats.add(locations);
//		    //System.out.println(record.get("Country/Region")+" "+Integer.parseInt(record.get(record.size()-1)));
//		    country_data.add(locations);
//		    country.add(locations.getCountry());
//		}
		
		
	}
}
