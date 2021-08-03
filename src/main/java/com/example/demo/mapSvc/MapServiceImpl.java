package com.example.demo.mapSvc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.RealPathOriginDestinationVO;
import com.example.demo.dto.Place;
import com.example.demo.kakao.KakaoMapServiceImpl;
import com.mycom.navigation.bus.navi.DijkstraNavigator;
import com.mycom.navigation.bus.navi.Navigator;
import com.mycom.navigation.infra.Infrastructure;

@Service
public class MapServiceImpl implements MapService {

	private KakaoMapServiceImpl kms;
	private Infrastructure infra;
	
	
	public MapServiceImpl(KakaoMapServiceImpl kms, Infrastructure infra) {
		super();
		this.kms = kms;
		this.infra = infra;
	}


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


	@Override
	public List<Double[][]> searchRealPath(List<RealPathOriginDestinationVO> vos) {
		List<Double[][]> realPaths = new ArrayList<Double[][]>();
		Double[][] paths = new Double[vos.size()][];
		Navigator navi = new DijkstraNavigator(infra);
		
		for(RealPathOriginDestinationVO vo : vos) {
			
			List<Double[]> list = navi.navigate(
					Double.parseDouble(vo.getSx()),
					Double.parseDouble(vo.getSy()),
					Double.parseDouble(vo.getEx()),
					Double.parseDouble(vo.getEy())
					);
			realPaths.add( list.toArray(new Double[0][0]));
		}
		return realPaths;
	}
	
	
}

