package com.myAmway.service;

import java.util.List;

import com.myAmway.frm.orm.Page;
import com.myAmway.model.Stock;

public interface StockService {
	
	Stock getStock(Integer id);
	
	void saveStock(Stock stock);
	
	Stock updateStock(Stock stock);
	
	void deleteStock(Stock stock);

	List<Stock> getStockList();

	List<Stock> getStockList(Page page);

	Integer getAllStockCount();


}
