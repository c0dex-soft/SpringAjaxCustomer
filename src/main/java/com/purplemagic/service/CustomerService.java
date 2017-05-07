package com.purplemagic.service;

import java.util.List;

import com.purplemagic.model.Customer;

public interface CustomerService {

	List<Customer> findAll();

	Customer add(Customer customer);
	
	Customer update(Long id);

	Customer findCustomerById(Long id);
	
	List<Customer> findCustomersByName(String term);

	void delete(Long id);

}
