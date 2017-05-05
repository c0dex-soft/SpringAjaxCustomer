package com.purplemagic.service;

import java.util.List;

import com.purplemagic.model.Customer;

public interface CustomerService {

	List<Customer> findAll();

	void save(Customer customer);
	
	Customer update(Long id);

	Customer findCustomerById(Long id);

	void delete(Long id);

}
