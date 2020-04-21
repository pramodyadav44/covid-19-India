package com.example.coviddailytracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.coviddailytracker.model.Locations;
import com.example.coviddailytracker.model.States;
import com.example.coviddailytracker.service.CovidDataServices;



@Controller
public class ApiController {
	
	@Autowired
	CovidDataServices covidDataServices;
	

	@GetMapping("/")
	public String home(Model model ) {
		List<States> allSates = covidDataServices.getAllStatesData();
		 List<Locations> allStats = covidDataServices.getStats();
	        int totalReportedCases = covidDataServices.getTotalcases();
	        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
	        model.addAttribute("totalcases", totalReportedCases);
	        model.addAttribute("data1", allSates);
	        model.addAttribute("totalNewCases", totalNewCases);
		//model.addAttribute("data1",covidDataServices.getStats());
		return "home";
	}
	
	@GetMapping("/map")
	public String map(Model model) {
		return "map";
	}
}
