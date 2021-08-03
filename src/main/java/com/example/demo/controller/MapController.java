package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.RealPathOriginDestinationVO;
import com.example.demo.mapSvc.MapService;
import com.mycom.navigation.bus.navi.DijkstraNavigator;
import com.mycom.navigation.bus.navi.Navigator;
import com.mycom.navigation.infra.Infrastructure;

@RestController
@RequestMapping("/map")
public class MapController {

	private static final Logger log = LoggerFactory.getLogger(MapController.class);
	private MapService mapServices;
	
	public MapController(MapService mapServices) {
		super();
		this.mapServices = mapServices;
	}

	@PostMapping("/drawing")
	public List<Double[][]> searchRealPath(@RequestBody List<RealPathOriginDestinationVO> vos) {
		
		//navigation에 경로 탐색 요청
		List<Double[][]> drawings = mapServices.searchRealPath(vos);
		return drawings;
	}

}
