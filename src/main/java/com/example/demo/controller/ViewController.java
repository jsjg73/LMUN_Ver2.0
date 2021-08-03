package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.Place;
import com.example.demo.mapSvc.MapService;

@Controller
public class ViewController {
	private MapService mapServices;
	
	public ViewController(MapService mapServices) {
		super();
		this.mapServices = mapServices;
	}

	@GetMapping("/mainService")
	public String mainServivePg(@ModelAttribute Place place, Model model) {
		
		List<Place> recommendPlace = mapServices.recommendPlaces(place.getPlaces());
		
		model.addAttribute("startPlaces", place.getPlaces());
		model.addAttribute("recommends", recommendPlace);
		return "mainService.html";
	}
}
