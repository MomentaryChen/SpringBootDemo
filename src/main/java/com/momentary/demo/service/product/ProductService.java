package com.momentary.demo.service.product;

import java.util.List;

import com.momentary.demo.model.product.Product;
import com.momentary.demo.model.product.ProductReq;

import io.swagger.v3.oas.annotations.Operation;

public interface ProductService {
	
	Product getProduct(String id) throws Exception;
	
	
	List<Product> getProducts() throws Exception;
	
	Boolean newProduct(ProductReq req) throws Exception;
	
}
