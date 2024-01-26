package com.example.demo.clients;



import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import com.example.demo.models.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {
	
	
	
	
    @GetMapping("/customers/{id}")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getDefaultCustomer")
    Customer findCustomerById(@PathVariable Long id);
    
    
	/*
	 * @GetMapping("/customers/firstname:/{fname}")
	 * 
	 * @CircuitBreaker(name = "customerService", fallbackMethod =
	 * "getDefaultCustomerFN") Customer findCustomerByFirstName(@PathVariable String
	 * fname);
	 */
    
    @GetMapping("/customers")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getAllCustomers")
    List<Customer> allCustomers();
    
    
    
    

    default Customer getDefaultCustomer(Long id, Exception exception){
        Customer customer=new Customer();
        customer.setId(id);
        customer.setFirstName("Not Vailable");
        customer.setLastName("Not Vailable");
        customer.setEmail("Not Vailable");
        return customer;
    }
    
    
	/*
	 * default Customer getDefaultCustomerFN(String fname, Exception exception){
	 * Customer customer=new Customer(); //customer.setId(id);
	 * customer.setFirstName("Not Vailablefn");
	 * customer.setLastName("Not Vailablefn"); customer.setEmail("Not Vailablefn");
	 * return customer; }
	 */
    
    default List<Customer> getAllCustomers(Exception exception){
    	 // Log the exception
        System.out.println("Fallback - getAllCustomers: " + exception.getMessage());

        return List.of();
    }
}