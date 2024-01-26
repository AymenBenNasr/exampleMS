package com.example.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.example.demo.config.GlobalConfig;
import com.example.demo.entities.Customer;
import com.example.demo.repositories.CustomerRepository;

@SpringBootApplication
@EnableConfigurationProperties({ GlobalConfig.class })
public class CustomerServiceApplication {

	// static final Logger logger =
	// LoggerFactory.getLogger(CustomerServiceApplication.class);

	public static void main(String[] args) {

		// logger.info("Before Starting Customer service");
		SpringApplication.run(CustomerServiceApplication.class, args);
		// logger.info("Customer service is starting");
		// logger.debug("Starting my application in debug with {} arguments",
		// args.length);
		// logger.info("Starting my application with {} arguments.", args.length);

	}

	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
		return args -> {

			List<Customer> customerList = List.of(
					Customer.builder().email("hassan@gmail.com").firstName("Hassan").lastName("Elhoumi")

							.build(),
					Customer.builder().email("Mohamed@gmail.com").firstName("Mohamed").lastName("Elhannaoui").build(),

					Customer.builder().email("Ali@gmail.com").firstName("Ali").lastName("Ezzine").build()

			);
			customerRepository.saveAll(customerList);
		};
	}

}