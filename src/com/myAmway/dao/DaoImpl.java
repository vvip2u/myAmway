package com.myAmway.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

import com.myAmway.dao.exception.DataOperationException;
import com.myAmway.dao.exception.NoDataFoundException;
import com.myAmway.frm.orm.AdvHibernateDaoSupport;

public class DaoImpl<T> extends AdvHibernateDaoSupport implements IDao<T> {
	
	@SuppressWarnings("unchecked")
	protected Class<T> entityClass = (Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	protected transient Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public T get(Serializable id) throws DataOperationException{
		T t = (T) this.getHibernateTemplate().get(entityClass, id);
		if(t == null){
			throw new NoDataFoundException(entityClass + " instance not exists!");
		}
		return t;
	}

	@Override
	public void save(T t) throws DataOperationException {
		try{
			this.getHibernateTemplate().persist(t);
		}catch(DataAccessException e) {
			log.info(e.getMessage());
			throw new DataOperationException(e.getMessage());
		}
	}

	@Override
	public T update(T t) throws DataOperationException{
		try {
			this.getHibernateTemplate().merge(t);
		} catch (DataAccessException e) {
			throw new DataOperationException(e);
		}
		return t;
	}

	@Override
	public void delete(T t) throws DataOperationException {
		try { 
			this.getHibernateTemplate().delete(t);
		} catch (DataAccessException e) {
			throw new DataOperationException(e.getMessage());
		}
	}

	@Override
	public void delete(Serializable id) throws DataOperationException  {
		T t = null;
		try {
			t = get(id);
			delete(t);
		} catch (DataOperationException e) {
			throw new DataOperationException("Could not get instance pk:" + id);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll(final Map<String, Object> criteriaMap) {
		if(criteriaMap == null)
			return getHibernateTemplate().loadAll(entityClass);
		
		
		
		return getHibernateTemplate().execute(new HibernateCallback<List<T>>() {

			@Override
			public List<T> doInHibernate(Session session) throws HibernateException,
					SQLException {
				Integer maxResults = (Integer) criteriaMap.get("max");
				Integer firstResult = (Integer) criteriaMap.get("first");
				
				Criteria criteria = session.createCriteria(entityClass);
				if(firstResult!=null && maxResults!=null) {
					criteria.setFirstResult(firstResult);
					criteria.setMaxResults(maxResults);
				}
				return criteria.list();
			}
		});
		
	}
	
	public static void main(String[] args) {
		String max = "a";
		Integer a = NumberUtils.parseNumber(max, Integer.class);
		System.out.println(a);
	}

	@Override
	public void saveAll(List<T> entities) {
		getHibernateTemplate().saveOrUpdateAll(entities);
	}

	@Override
	public void updateAll(List<T> ts) {
		getHibernateTemplate().saveOrUpdateAll(ts);
	}

	@Override
	public void deleteAll(List<T> ts) throws DataOperationException {
		for (T t : ts) {
			delete(t);
		}
	}

	@Override
	public Integer getCount() throws DataOperationException {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		criteria.setProjection(Projections.rowCount());
		return ((Long) getHibernateTemplate().findByCriteria(criteria).get(0)).intValue(); 
	}
	

}
