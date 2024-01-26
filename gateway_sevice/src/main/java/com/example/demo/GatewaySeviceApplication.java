package com.example.demo;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class GatewaySeviceApplication {

	//static final Logger logger = LoggerFactory.getLogger(GatewaySeviceApplication.class);

	public static void main(String[] args) {
				
		//logger.info("Before Starting GatewaySeviceApplication");
		SpringApplication.run(GatewaySeviceApplication.class, args);
		//logger.debug("Starting my GatewaySeviceApplication in debug with {} arguments", args.length);
		//logger.info("Starting my GatewaySeviceApplication with {} arguments.", args.length);
	
	}
	
	@Bean
	DiscoveryClientRouteDefinitionLocator locator(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp){
		//logger.info("Activating the dynamic load balancing");
		return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
	}

}