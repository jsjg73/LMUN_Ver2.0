package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controller.MapController;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@WebMvcTest(controllers = MapController.class)
public class MapControllerTest {
	
	@Autowired
	private MockMvc mvc;
	@Test
	public void searchRealPath()throws Exception{
		List<RealPathOriginDestinationVO> list = new ArrayList<RealPathOriginDestinationVO>();
		RealPathOriginDestinationVO vo = new RealPathOriginDestinationVO();
		vo.setEx("127.0456082");
		vo.setEy("37.6887815");
		vo.setSx("126.8605471");
		vo.setSy("37.5528989");
		list.add(vo);
		String content = new ObjectMapper().writeValueAsString(list);
		mvc.perform(post("/drawing")
				.content(content)
				.contentType(MediaType.APPLICATION_JSON)
		        .accept(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk())
//		        .andExpect(matcher)
	}
}
