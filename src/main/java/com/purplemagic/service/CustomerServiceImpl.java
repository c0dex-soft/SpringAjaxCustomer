package com.purplemagic.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.purplemagic.dao.CustomerDao;
import com.purplemagic.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerDao customerDao;
	

	@Override
	public Customer add(Customer customer) {
		 Customer rCustomer = customerDao.add(customer);
		 System.out.println("DATUM IZ BAZE: " +rCustomer.getDob());
		 return rCustomer;
	}

	@Override
	public List<Customer> findAll() {
		List<Customer> customers = customerDao.findAll();
		for (Customer customer : customers) {
			System.out.println("DATUM IZ BAZE: " +customer.getDob());
		}
		return customers;
	}

	@Override
	public Customer findCustomerById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		customerDao.delete(id);
		
	}

	@Override
	public Customer update(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> findCustomersByName(String term) {
		return customerDao.findCustomersByName(term);
	}


}
