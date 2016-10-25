package com.myAmway.service;

import java.util.List;

import com.myAmway.frm.orm.Page;
import com.myAmway.model.Customer;

public interface CustomerService {
	
	Customer getCustomer(Integer id);
	
	void saveCustomer(Customer customer);
	
	Customer updateCustomer(Customer customer);
	
	void deleteCustomer(Customer customer);

	List<Customer> getCustomerList();

	List<Customer> getCustomerList(Page page);

	Integer getAllCustomerCount();


}
