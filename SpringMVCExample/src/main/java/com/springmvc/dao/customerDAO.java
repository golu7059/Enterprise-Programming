package com.springmvc.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springmvc.model.Customer;

import jakarta.transaction.Transactional;

@Component
public class customerDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	//Show all customers
	@Transactional
	public List<Customer> getCustomers() {
		return sessionFactory.getCurrentSession().createQuery("from Customer").list();
	}
	
	//Save customer ( Add and Edit )
	@Transactional
	public void saveCustomer(Customer customer) {
		sessionFactory.getCurrentSession().saveOrUpdate(customer);
	}
	
	//Get Customer by ID
	@Transactional
	public Customer getCustomerByID(int id) {
		return sessionFactory.getCurrentSession().get(Customer.class, id);
	}
	
	//Delete customer by ID
	@Transactional
	public void delCustomer(int id) {
		Customer customer = getCustomerByID(id);
		sessionFactory.getCurrentSession().remove(customer);
	}
}