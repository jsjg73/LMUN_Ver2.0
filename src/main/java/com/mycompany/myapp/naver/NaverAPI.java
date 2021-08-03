
/*
 curl "https://naveropenapi.apigw.ntruss.com/map-direction/v1/driving?start=127.1058342,37.359708&goal=129.075986,35.179470&option=trafast" \
	-H "X-NCP-APIGW-API-KEY-ID: {?��?��리�??��?�� ?���? ?�� 발급받�? client id �?}" \
	-H "X-NCP-APIGW-API-KEY: {?��?��리�??��?�� ?���? ?�� 발급받�? client secret�?}" -v
 	
 * */
package com.mycompany.myapp.naver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
public class NaverAPI{
	private String URL = "https://naveropenapi.apigw.ntruss.com/map-direction/v1/driving";
	private String ID = "butimsrvsd";
	private String KEY = "lLOnI1zEJbNwFdLeTvlvtoSzbEBYS6aTHV0d733c";

	public void getPath(NPath path) {
		HttpURLConnection conn = null;
		StringBuilder sb = null;
		
		String start = concatcomma(path.getSx(), path.getSy());
		String goal = concatcomma(path.getEx(), path.getEy());
		
		try {
			String final_request_url = new StringBuffer()
					.append(URL).append("?")
					.append("start=").append(start).append("&")
					.append("goal=").append(goal)
					.append("&option=traavoidcaronly")
					.toString();
			URL url = new URL(final_request_url);
			
			conn = (HttpURLConnection) url.openConnection();
			// Request ?��?�� ?��?��
			conn.setRequestMethod("GET");
			// ?�� ?��?��
			conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", ID);
			conn.setRequestProperty("X-NCP-APIGW-API-KEY", KEY);
			
			// 보내�? 결과�? 받기
			// ?��?�� ?��?�� ?��?�� 코드.
			int responseCode = conn.getResponseCode();
			if (responseCode == 400) {
				path.setStCode(400);
				path.setStMsg("naver api connection :: 400 error");
			} else if (responseCode == 401) {
				path.setStCode(401);
				path.setStMsg("401:: Wrong X-Auth-Token Header");
			} else if (responseCode == 500) {
				path.setStCode(500);
				path.setStMsg("500::server error");
			} else { // ?���? ?�� ?��?�� JSON ?��?��?��받기
				path.setStCode(200);
				path.setStMsg("OK");
				sb = new StringBuilder();
				
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
				
				String line = "";
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				br.close();
				path.setRawPath(sb.toString());
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String concatcomma(String x, String y) {
		String str = new StringBuilder()
				.append(x)
				.append(",")
				.append(y)
				.toString();
		return str;
	}
}
