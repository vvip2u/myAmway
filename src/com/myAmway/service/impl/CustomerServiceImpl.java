package com.myAmway.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myAmway.dao.IDao;
import com.myAmway.dao.exception.DataOperationException;
import com.myAmway.frm.orm.Page;
import com.myAmway.model.Customer;
import com.myAmway.service.BaseServiceSupport;
import com.myAmway.service.CustomerService;

@Service(value="customerService")
public class CustomerServiceImpl extends BaseServiceSupport implements CustomerService{
	
	@Resource(name="customerDao")
	private IDao<Customer> customerDao;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Customer getCustomer(Integer id) {
		try {
			return customerDao.get(id);
		} catch (DataOperationException e) {
			log.error("get Customter error [customerId: "+ id + "]", e);
			return null;
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveCustomer(Customer customer) {
		try {
			customerDao.save(customer);
		} catch (DataOperationException e) {
			log.error("save Customter error", e);
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Customer updateCustomer(Customer customer) {
		try {
			return customerDao.update(customer);
		} catch (DataOperationException e) {
			log.error("update Customter error [customerId: " + customer.getCustomerId() + " ]", e);
			return null;
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteCustomer(Customer customer) {
		try {
			customerDao.delete(customer);
		} catch (DataOperationException e) {
			log.error("delete Customter error [customerId: " + customer.getCustomerId() + " ]", e);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<Customer> getCustomerList() {
		try {
			return customerDao.getAll(null);
		} catch (DataOperationException e) {
			log.error("load CustomterList error ", e);
			return null;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<Customer> getCustomerList(Page page) {
		Map<String, Object> criteriaMap;
		try {
			criteriaMap = new HashMap<String, Object>();
			criteriaMap.put("max", page.getRows());
			criteriaMap.put("first", (page.getPage() - 1) * page.getRows());
			return customerDao.getAll(criteriaMap);
		} catch (DataOperationException e) {
			log.error("load CustomterList error ", e);
			return null;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Integer getAllCustomerCount() {
		try {
			return customerDao.getCount();
		} catch (DataOperationException e) {
			log.error("load CustomterList error ", e);
			return 0;
		}
	}

}
