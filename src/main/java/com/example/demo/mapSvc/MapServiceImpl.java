package com.example.demo.mapSvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Place;
import com.example.demo.kakao.KakaoMapServiceImpl;

@Service
public class MapServiceImpl implements MapService {

	@Autowired
	private KakaoMapServiceImpl kms;

	// 중심 좌표 구하기.
	// 출발지 좌표 값의 평균.
	public Place getCenter(List<Place> placeList) {
		Place p = new Place();
		float n = placeList.size();
		float sumX = 0;
		float sumY = 0;
		for (int i = 0; i < n; i++) {
			sumX += Float.parseFloat(placeList.get(i).getX());
			sumY += Float.parseFloat(placeList.get(i).getY());
		}
		p.setX(Float.toString(sumX / n));
		p.setY(Float.toString(sumY / n));
		p.setName("중심");
		return p;
	}

	
	@Override
	public List<Place> recommendPlaces(List<Place> places) {
		Place center = getCenter(places);
		
		return kms.recommendPlaces(center);
	}
	
	
}

