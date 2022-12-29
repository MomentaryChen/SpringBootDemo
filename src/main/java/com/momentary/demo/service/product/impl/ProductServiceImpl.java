package com.momentary.demo.service.product.impl;

import java.util.List;
import java.util.Objects;

import javax.xml.stream.events.StartDocument;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momentary.demo.dao.product.ProductDao;
import com.momentary.demo.model.product.Product;
import com.momentary.demo.model.product.ProductReq;
import com.momentary.demo.service.product.ProductService;
import com.momentary.demo.util.ExceptionUtils;

@Service
public class ProductServiceImpl implements ProductService {
	private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);
	
	@Autowired
	ProductDao productDao;
	
	@Override
	public Product getProduct(String id) throws Exception {
		logger.info("=================== Start get Product by id ===================");
		Product product = null;
		try {
			if(id.isEmpty()) {
				throw new Exception("Id is empty");
			}
			product = productDao.getProduct(id);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(ExceptionUtils.getStackTrace(e));
		} finally {
			logger.info("=================== End get Product by id ===================");
		}
		
		return product;
	}

	@Override
	public List<Product> getProducts() throws Exception{
		// TODO Auto-generated method stub
		logger.info("=================== Start get Products ===================");
		List<Product> prodcutsList = null;
		try {
			prodcutsList = productDao.getProducts();
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		} finally {
			logger.info("=================== End get Products ===================");
		}
		return prodcutsList;
	}

	@Override
	public Boolean newProduct(ProductReq req) throws Exception {
		logger.info("=================== Start new Product ===================");
		Product product = null;
		try {
			String id = productDao.getMaxIdOfProducts().getId();
			Objects.requireNonNull(id);
			
			String newId = String.format("%03d", Integer.parseInt(id) + 1);
			Objects.requireNonNull(newId);

			return productDao.newProduct( new Product(newId, req.getName(), req.getPrice()));
			
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		} finally {
			logger.info("=================== End get Products ===================");
		}
		
		return false;
	}

}
