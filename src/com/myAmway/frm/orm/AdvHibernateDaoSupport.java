package com.myAmway.frm.orm;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AdvHibernateDaoSupport extends HibernateDaoSupport {
	
	@Resource(name="sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}
