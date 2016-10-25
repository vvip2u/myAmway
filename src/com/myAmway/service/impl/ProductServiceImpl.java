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
import com.myAmway.model.Product;
import com.myAmway.service.BaseServiceSupport;
import com.myAmway.service.ProductService;

@Service(value="productService")
public class ProductServiceImpl extends BaseServiceSupport implements ProductService {
	
	@Resource(name="productDao")
	private IDao<Product> productDao;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Product getProduct(Integer id) {
		try {
			return productDao.get(id);
		} catch (DataOperationException e) {
			log.error("get Product error [productId: "+ id + "]", e);
			return null;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveProduct(Product product) {
		try {
			productDao.save(product);
		} catch (DataOperationException e) {
			log.error("save Product error", e);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Product updateProduct(Product product) {
		try {
			return productDao.update(product);
		} catch (DataOperationException e) {
			log.error("update Product error [productId: " + product.getProductId() + " ]", e);
			return null;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteProduct(Product product) {
		try {
			productDao.delete(product);
		} catch (DataOperationException e) {
			log.error("delete Product error [productId: " + product.getProductId() + " ]", e);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<Product> getProductList() {
		try {
			return productDao.getAll(null);
		} catch (DataOperationException e) {
			log.error("load ProductList error ", e);
			return null;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<Product> getProductList(Page page) {
		Map<String, Object> criteriaMap;
		try {
			criteriaMap = new HashMap<String, Object>();
			criteriaMap.put("max", page.getRows());
			criteriaMap.put("first", (page.getPage() - 1) * page.getRows());
			return productDao.getAll(criteriaMap);
		} catch (DataOperationException e) {
			log.error("load ProductList error ", e);
			return null;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Integer getAllProductCount() {
		try {
			return productDao.getCount();
		} catch (DataOperationException e) {
			log.error("load ProductList error ", e);
			return 0;
		}
	}

}
