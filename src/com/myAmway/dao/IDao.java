package com.myAmway.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.myAmway.dao.exception.DataOperationException;

public interface IDao<T> {
	
	T get(Serializable id) throws DataOperationException;
	
	void save(T t) throws DataOperationException;
	
	T update(T t) throws DataOperationException;
	
	void delete(T t) throws DataOperationException;
	
	void delete(Serializable id) throws DataOperationException;
	
	List<T> getAll(Map<String, Object> criteriaMap) throws DataOperationException;
	
	void saveAll(List<T> entities) throws DataOperationException;
	
	void updateAll(List<T> ts) throws DataOperationException;
	
	void deleteAll(List<T> ts) throws DataOperationException;

	Integer getCount() throws DataOperationException;
	
}
