package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoverySeviceApplication {
//	static final Logger logger = LoggerFactory.getLogger(DiscoverySeviceApplication.class);

	public static void main(String[] args) {
				
		//logger.info("Before Starting DiscoverySeviceApplication");
		SpringApplication.run(DiscoverySeviceApplication.class, args);
		//logger.debug("Starting my DiscoverySeviceApplication in debug with {} arguments", args.length);
		//logger.info("Starting my DiscoverySeviceApplication with {} arguments.", args.length);
	
	}
	
}
