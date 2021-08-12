package com.example.demo.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.mycom.navigation.bus.factory.BusInfra;
import com.mycom.navigation.bus.factory.BusInfraFactory;
import com.mycom.navigation.bus.reader.BusInfraReader;
import com.mycom.navigation.bus.reader.BusInfraTxtReader;
import com.mycom.navigation.infra.Infrastructure;

@Configuration
public class InfraConfiguration {
	
	@Value("classpath:bus/Stations.txt")
	Resource busStationResource;

	@Value("classpath:bus/EdgeData.txt")
	Resource busEdgeResource;
	
	@Bean
	public Infrastructure businfra() throws IOException{
		BusInfraReader bifReader = new BusInfraTxtReader(
				busStationResource.getInputStream(),
				busEdgeResource.getInputStream()
				);
		return new BusInfraFactory(bifReader).getInfraInstance();
	}
}
