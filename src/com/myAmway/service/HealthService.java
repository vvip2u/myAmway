package com.myAmway.service;

import java.util.List;

import com.myAmway.frm.orm.Page;
import com.myAmway.model.Health;

public interface HealthService {
	
	void saveHealth(Health health);

	Health getHealth(Integer healthId);

	List<Health> getHealthList();

	List<Health> getHealthListByCustomerId(Integer customerId);

	List<Health> getHealthList(Page page);

	Integer getAllHealthCount();

}
