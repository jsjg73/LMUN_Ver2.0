package com.example.demo.kakao;

import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Place;

@Service
public class KakaoMapServiceImpl {
	@Autowired
	private KakaoAPI K_api;
	@Autowired
	private kakaoAPIParser par;

	public List<Place> recommendPlaces(Place center) {
		KakaoCategorySearch kkoCategorySearch = new KakaoCategorySearch
				.Builder("SW8", center.getX(), center.getY(), 2000)
				.page(1)
				.size(5)
				.build();
		
		// 1. url 만들기
		URL url = kkoCategorySearch.getURL();
		
		// 2. api 요청
		String data = K_api.getStationCoord(url);
		
		// 3. parsing
		List<Place> recommendPlaces = par.getPlaceInfo(data);
		
		recommendPlaces.add(0, center);
		return recommendPlaces;
	}
}
