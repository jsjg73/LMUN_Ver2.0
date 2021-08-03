package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mycom.navigation.bus.factory.BusInfra;
import com.mycom.navigation.bus.factory.BusInfraFactory;
import com.mycom.navigation.bus.reader.BusInfraReader;
import com.mycom.navigation.bus.reader.BusInfraTxtReader;

@Configuration
public class InfraConfiguration {
	
	@Bean
	public BusInfra businfra(BusInfraFactory factory) {
		BusInfraReader bifReader = new BusInfraTxtReader();
		BusInfra bif =  new BusInfraFactory(bifReader).createOnlyOnce();
		return bif;
	}
}
