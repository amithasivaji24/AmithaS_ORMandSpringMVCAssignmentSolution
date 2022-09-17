package com.greatlearning.customerManagement.service;

import java.util.List;

import com.greatlearning.customerManagement.entity.Customer;

public interface CustomerService {
	public List<Customer> findAll();

	public Customer findById(int theId);

	public void save(Customer thecustomer);

	public void deleteById(int theId);
}
