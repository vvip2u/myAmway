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
import com.myAmway.model.Stock;
import com.myAmway.service.BaseServiceSupport;
import com.myAmway.service.StockService;

@Service(value="stockService")
public class StockServiceImpl extends BaseServiceSupport implements StockService {
	
	@Resource(name="stockDao")
	private IDao<Stock> stockDao;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Stock getStock(Integer id) {
		try {
			return stockDao.get(id);
		} catch (DataOperationException e) {
			log.error("get Stock error [stockId: "+ id + "]", e);
			return null;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveStock(Stock stock) {
		try {
			stockDao.save(stock);
		} catch (DataOperationException e) {
			log.error("save Stock error", e);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Stock updateStock(Stock stock) {
		try {
			return stockDao.update(stock);
		} catch (DataOperationException e) {
			log.error("update Stock error [stockId: " + stock.getStockId() + " ]", e);
			return null;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteStock(Stock stock) {
		try {
			stockDao.delete(stock);
		} catch (DataOperationException e) {
			log.error("delete Stock error [stockId: " + stock.getStockId() + " ]", e);
		}
	}

	@Override
	public List<Stock> getStockList() {
		try {
			return stockDao.getAll(null);
		} catch (DataOperationException e) {
			log.error("load StockList error ", e);
			return null;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<Stock> getStockList(Page page) {
		Map<String, Object> criteriaMap;
		try {
			criteriaMap = new HashMap<String, Object>();
			criteriaMap.put("max", page.getRows());
			criteriaMap.put("first", (page.getPage() - 1) * page.getRows());
			return stockDao.getAll(criteriaMap);
		} catch (DataOperationException e) {
			log.error("load StockList error ", e);
			return null;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Integer getAllStockCount() {
		try {
			return stockDao.getCount();
		} catch (DataOperationException e) {
			log.error("load StockList error ", e);
			return 0;
		}
	}

}
