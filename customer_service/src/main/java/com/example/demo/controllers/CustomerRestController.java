package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Customer;
import com.example.demo.services.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class CustomerRestController {

	// private CustomerRepository customerRepository;

	private CustomerService customerService;

	// Injection des dependances par les Constructeurs
	public CustomerRestController(CustomerService customerService) {
		this.customerService = customerService;
	}


	@PostMapping("/customers")

	public Customer addCustomer(@RequestBody Customer customer) {
		return customerService.saveCustomer(customer);
	}
	

	@GetMapping("/customers")
	public List<Customer> customerList() {
		log.info("Return all the customers");
		List<Customer> lcustomers = customerService.findAllCustomers();
		int len = lcustomers.size();
		log.info("{} customers are founded ", len);
		return lcustomers;
	}

	@GetMapping("/customers/{id}")
	public Optional<Customer> customerById(@PathVariable Long id) {
		log.info("find the customert with this id {}", id);
		Optional<Customer> customer = customerService.findCustomerById(id);
		log.info("customer founded successfully ");
		return customer;
	}
	
	

	@GetMapping("/customers/firstname:/{firstname}")
	public Optional<Customer> customerByFirstName(@PathVariable String firstname) {
		log.info("find the customert with this firstname {}", firstname);
		Optional<Customer>  customer = customerService.findCustomerByFirstName(firstname);
		 if (customer.isPresent()) {
		log.info("customer founded successfully ");
		return customer;
		 }
		 else {
			 throw new ResourceNotFoundException("Customer not found with firstname");
		 }
	}
 
	
	
	@GetMapping("/customers/email:/{email}")
	public Optional<Customer> customerByEmail(@PathVariable String email) {
		
		log.info("find the customert with this email {}", email);
		Optional<Customer>  customer = customerService.findCustomerByEmail(email);
		  if (customer.isPresent()) {
		log.info("customer found successfully ");
		return customer;}
		  else {
		        throw new ResourceNotFoundException("Customer not found with email: " + email);
		    }
	}
 
	
	
	
    @PutMapping("/customers/{id}") 
    public Optional< Customer> updateCustomer(@PathVariable("id") long customerId,
                                                   @RequestBody Customer customer){
        return customerService.findCustomerById(customerId)
        		
                .map(savedCustomer -> {
                	savedCustomer.setFirstName(customer.getFirstName());
                	savedCustomer.setLastName(customer.getLastName());
                	savedCustomer.setEmail(customer.getEmail());

                	Customer updatedCustomer = customerService.updateCustomer(savedCustomer);
                    return (updatedCustomer);
                });      
    }

    
    
    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        log.info("Deleting the customer with id: {}", id);

        Optional<Customer> customerToDelete = customerService.findCustomerById(id);

        if (customerToDelete.isPresent()) {
            customerService.deleteCustomer(id);
            log.info("Customer with id {} deleted successfully", id);
            
        } else {
        	  throw new ResourceNotFoundException("Customer does not exist withthis id " + id);
          
        }
    }
}