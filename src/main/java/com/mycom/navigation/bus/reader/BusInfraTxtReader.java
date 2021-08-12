package com.mycom.navigation.bus.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.map.MultiKeyMap;

/*
 * BusInfra 구축하기 위해 서울시 버스 노선 및 정류장 정보 텍스트 파일 읽기.
 * 
 * txt파일 의 요구 형식
 * 	각 행의 정보 및 순서 : 노선ID, 노선명, 순번, NODE_ID, ARS-ID, 정류소명, X좌표, Y좌표
 *  구분자  : \t
 *   
 * */
public class BusInfraTxtReader implements BusInfraReader{
	private InputStream stations;
	private InputStream edges;
	
	public BusInfraTxtReader(InputStream inputStream, InputStream inputStream2) {
		super();
		this.stations = inputStream;
		this.edges = inputStream2;
	}
	public List<String[]> readExternalBusData() {
		List<String[]> busStopByRoute = new ArrayList<String[]>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(stations));
			String line = null;
			while((line = br.readLine())!= null) { // EoF
				String[] infs = line.split("\t");
				busStopByRoute.add(infs);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return busStopByRoute;
	}
	public MultiKeyMap<String, List<Double[]>> loadRealPath() {
		 MultiKeyMap<String, List<Double[]>> map = new MultiKeyMap<String, List<Double[]>>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(edges));
			String line = null;
			while((line = br.readLine())!= null) { // EoF
				String[] infs = line.split("\t");
				List<Double[]> realPath = converter(infs[2]);
				map.put(infs[0], infs[1], realPath);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	private List<Double[]> converter(String input) {
		String[] arr = input.replaceAll("[\\[\\]]", "").split(",");
		List<Double[]> list = new ArrayList<Double[]>();
		for(int i=0; i<arr.length; i+=2) {
			Double[] doubles = {Double.parseDouble(arr[i]), Double.parseDouble(arr[i+1])};
			list.add(doubles);
		}
		return list;
	}
}
