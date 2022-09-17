package com.greatlearning.customerManagement.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.greatlearning.customerManagement.entity.Customer;


@Repository
public class CustomerServiceImplementation implements CustomerService{

	private SessionFactory sessionFactory;
	private Session session;
	
	@Autowired
	public CustomerServiceImplementation(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
		
		try {
			session=sessionFactory.getCurrentSession();
		}
		catch(HibernateException e) {
			session=sessionFactory.openSession();
		}
	}
	
	@Transactional
	@Override
	public List<Customer> findAll() {
		Transaction tx = session.beginTransaction();
		List<Customer> customers = session.createQuery("from Customer").list();
		tx.commit();

		return customers;
	}

	@Override
	public Customer findById(int theId) {
		Customer customer = new Customer();
		
		Transaction tx = session.beginTransaction();
		customer=session.get(Customer.class, theId);
		tx.commit();
		
		return customer;
	}

	@Override
	public void save(Customer thecustomer) {
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(thecustomer);
		tx.commit();	
	}

	@Override
	public void deleteById(int theId) {
		Customer customer = new Customer();
		
		Transaction tx = session.beginTransaction();
		customer=session.get(Customer.class, theId);
		session.delete(customer);
		
		tx.commit();
		
	}

}
