package com.momentary.demo.dao.product;

import java.util.List;
import java.util.Optional;

import com.momentary.demo.model.product.Product;
import com.momentary.demo.model.product.ProductReq;

public interface ProductDao {
	
	public Product getProduct(String id);
	
	public List<Product> getProducts();
	
	Boolean newProduct(Product product) throws Exception;
	
	public Product getMaxIdOfProducts() throws Exception;
}
