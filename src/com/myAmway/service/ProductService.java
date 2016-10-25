package com.myAmway.service;

import java.util.List;

import com.myAmway.frm.orm.Page;
import com.myAmway.model.Product;

public interface ProductService {
	
	Product getProduct(Integer id);
	
	void saveProduct(Product product);
	
	Product updateProduct(Product product);
	
	void deleteProduct(Product product);

	List<Product> getProductList();

	List<Product> getProductList(Page page);

	Integer getAllProductCount();

}
