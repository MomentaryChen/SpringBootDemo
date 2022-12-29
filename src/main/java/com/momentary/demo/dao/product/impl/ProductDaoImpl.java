package com.momentary.demo.dao.product.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.momentary.demo.dao.product.ProductDao;
import com.momentary.demo.model.product.Product;
import com.momentary.demo.model.product.ProductReq;

import io.jsonwebtoken.lang.Strings;

@Repository
public class ProductDaoImpl implements ProductDao{
	private static final Logger logger = LogManager.getLogger(ProductDaoImpl.class);
	private final List<Product> products = new ArrayList<Product>();
	
	public ProductDaoImpl() {
		products.add(new Product("001", "螺絲", 1000));
		products.add(new Product("002", "葡萄", 80));
		products.add(new Product("003", "電腦", 25000));
		
		logger.info("The Products Generated");
	}

	@Override
	public Product getProduct(String id) {
		
		Optional<Product> product =  products.stream().filter((p) -> p.getId().equals(id)).findAny();
		return product.orElse(null);
	}

	@Override
	public List<Product> getProducts() {
		
		return products;
	}
	
	@Override
	public Boolean newProduct(Product newProduct) throws Exception {
		
		products.add(newProduct);
		
		return true;
	}
	
	@Override
	public Product getMaxIdOfProducts() throws Exception{
		Product Product = products
				.stream()
				.min((p1, p2) -> p2.getId().compareTo(p1.getId()))
			     .orElseThrow(NoSuchElementException::new);
		
		return Product;
	}

}
