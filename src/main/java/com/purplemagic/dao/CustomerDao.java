package com.purplemagic.dao;

import java.util.List;

import com.purplemagic.model.Customer;

public interface CustomerDao {

	List<Customer> findAll();

	Customer add(Customer customer);
	
	Customer update(Long id);

	Customer findCustomerById(Long id);

	List<Customer> findCustomersByName(String term);
	
	void delete(Long id);

}
