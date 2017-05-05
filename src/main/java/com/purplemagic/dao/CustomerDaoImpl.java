package com.purplemagic.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.purplemagic.model.Customer;

@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Customer> findAll() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Customer.class);
		List<Customer> customers = criteria.list();
		return customers;
	}

	@Override
	public Customer findCustomerById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from Customer where id=:id");
		query.setLong("id", id);
		int i = query.executeUpdate();
		System.out.println("CustomerDAO returned value: "+i);

	}

	@Override
	public Customer update(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
