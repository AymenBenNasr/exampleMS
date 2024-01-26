package com.example.demo.servicesimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Customer;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.services.CustomerService;

import io.micrometer.common.util.StringUtils;



@Service
public class CustomerServiceImpl implements CustomerService {
	

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    
    
    
    public Customer saveCustomer(Customer customer) {
    	

    	Optional<Customer> savedEmployee = customerRepository.findByEmail(customer.getEmail());
        if(savedEmployee.isPresent()){
            throw new ResourceNotFoundException("Customer already exist with given email:" + customer.getEmail());
        }
        // Check if firstName is empty
        if (StringUtils.isEmpty(customer.getFirstName())) {
            throw new IllegalArgumentException("First name cannot be empty");
        }
        
        if (StringUtils.isEmpty(customer.getLastName())) {
            throw new IllegalArgumentException("Last name cannot be empty");
        }
        
        if (StringUtils.isEmpty(customer.getEmail())) {
            throw new IllegalArgumentException("email cannot be empty");
        }



    	
    	return this.customerRepository.save(customer);
    	
    }
    
    public List <Customer> findAllCustomers(){
    	return this.customerRepository.findAll();
    }
    
    
    public Optional<Customer> findCustomerById(Long id) {
    	return this.customerRepository.findById(id);
    }
    
    
    public Optional<Customer> findCustomerByEmail(String email) {
    	return this.customerRepository.findByEmail(email);
    }
    
    
    public Optional<Customer> findCustomerByFirstName(String fname) {
    	return this.customerRepository.findByFirstName(fname);
    }
    
    
    public Customer  updateCustomer(Customer customer) {
    	return customerRepository.save( customer);
    }
    

    public void deleteCustomer(Long id ) {
    	 customerRepository.deleteById(id);
    }
     
}
