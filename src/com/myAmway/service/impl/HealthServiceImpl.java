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
import com.myAmway.model.Health;
import com.myAmway.service.BaseServiceSupport;
import com.myAmway.service.HealthService;

@Service(value="healthService")
public class HealthServiceImpl extends BaseServiceSupport implements HealthService {
	
	@Resource(name="healthDao")
	private IDao<Health> healthDao;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveHealth(Health health) {
		try {
			healthDao.save(health);
		} catch (DataOperationException e) {
			log.error("save Health error", e);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Health getHealth(Integer healthId) {
		try {
			return healthDao.get(healthId);
		} catch (DataOperationException e) {
			log.error("get Health error [healthId: "+ healthId + "]", e);
			return null;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<Health> getHealthList() {
		Map<String, Object> criteriaMap = new HashMap<String, Object>();
		try {
			return healthDao.getAll(criteriaMap);
		} catch (DataOperationException e) {
			log.error("load healthList error", e);
			return null;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<Health> getHealthListByCustomerId(Integer customerId) {
		Map<String, Object> criteriaMap = new HashMap<String, Object>();
		criteriaMap.put("customerId", customerId);
		try {
			return healthDao.getAll(criteriaMap);
		} catch (DataOperationException e) {
			log.error("load healthList error", e);
			return null;
		}
	}

	@Override
	public List<Health> getHealthList(Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAllHealthCount() {
		// TODO Auto-generated method stub
		return null;
	}

}
