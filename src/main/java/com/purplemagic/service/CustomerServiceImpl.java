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
	public void save(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Customer> findAll() {
		List<Customer> customers = customerDao.findAll();
		for(int i=0; i < customers.size(); i++) {
			Customer currentCustomer = customers.get(i);
			Date oldDob = currentCustomer.getDob();
			
			SimpleDateFormat sdf = new SimpleDateFormat();
			sdf.applyPattern("dd-MM-yyyy");
			String newDob = sdf.format(oldDob);
			System.out.println("Datum ["+i+"] -> " +newDob);
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


}
