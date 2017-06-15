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
		 Customer rCustomer = customerDao.add(stringToNull(customer));
		 return rCustomer;
	}

	@Override
	public List<Customer> findAll() {
		List<Customer> customers = customerDao.findAll();
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
	public Customer update(Customer customer) {
		return customerDao.update(stringToNull(customer));
	}

	@Override
	public List<Customer> findCustomersByName(String term) {
		return customerDao.findCustomersByName(term);
	}
	
	
	private Customer stringToNull(Customer customer) {
		if (customer.getFirstName().equals("")) {
			customer.setFirstName(null);
		}
		if (customer.getLastName().equals("")) {
			customer.setLastName(null);
		}
		if (customer.getEmail().equals("")) {
			customer.setEmail(null);
		}
		if (customer.getPhoneNumber().equals("")) {
			customer.setPhoneNumber(null);
		}		
		return customer;
	}


}
