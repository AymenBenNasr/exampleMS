package com.example.demo.repositories;


import com.example.demo.entities.Customer;

import jakarta.transaction.Transactional;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


public interface CustomerRepository extends JpaRepository<Customer,Long> {
	Optional<Customer> findByFirstName(String firstname);
	Optional<Customer> findByEmail(String email);

	
	/*
	 * @Transactional
	 * 
	 * @Modifying
	 * 
	 * @Query("UPDATE Customer c SET c.firstName = :firstName, c.lastName = :lastName, c.email = :email WHERE c.id = :id"
	 * ) void updateCustomer(@Param("id") Long id, @Param("firstName") String
	 * firstName, @Param("lastName") String lastName, @Param("email") String email);
	 */
}