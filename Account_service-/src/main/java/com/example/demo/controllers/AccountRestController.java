package com.example.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//import org.springframework.kafka.core.kafkaTemplate;

import com.example.demo.clients.CustomerRestClient;
import com.example.demo.entities.BankAccount;
import com.example.demo.models.Customer;
import com.example.demo.repositories.BankAccountRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AccountRestController {
	private BankAccountRepository accountRepository;
	private CustomerRestClient customerRestClient;

	public AccountRestController(BankAccountRepository accountRepository, CustomerRestClient customerRestClient) {
		this.accountRepository = accountRepository;
		this.customerRestClient = customerRestClient;
	}

	// @Autowired
	// private kafkaTemplate <String,String> kafkaTemplate;

	/*
	 * 
	 * @PostMapping public ResponseEntity<String>account(@RequestBody String
	 * account) { {
	 * 
	 * }
	 */

	@GetMapping("/accounts")
	public List<BankAccount> accountList() {
		log.info("return all accounts");
		List<BankAccount> accountList = accountRepository.findAll();
		log.info("accounts founded");
		accountList.forEach(acc -> {
			log.info("add the next");
			Long id = acc.getCustomerId();
			log.info("add the customer with the id:{}", id);
			acc.setCustomer(customerRestClient.findCustomerById(id));
			log.info("customer added successfully");

		});
		return accountList;
	}

	@GetMapping("/accounts/{id}")
	public BankAccount bankAccountById(@PathVariable String id) {

		BankAccount bankAccount = accountRepository.findById(id).get();
		log.info("Account founded by the id :{}", id);
		log.info("The customer with this account has the id :{}", bankAccount.getCustomerId());
		Customer customer = customerRestClient.findCustomerById(bankAccount.getCustomerId());
		log.info("Customer founded");
		bankAccount.setCustomer(customer);
		return bankAccount;
	}
}