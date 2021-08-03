package com.example.demo.mapSvc;

import java.util.List;

import com.example.demo.dto.Place;

public interface MapService {

	public List<Place> recommendPlaces(List<Place> places);
}
