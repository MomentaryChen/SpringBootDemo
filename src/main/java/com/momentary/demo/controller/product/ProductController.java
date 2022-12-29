package com.momentary.demo.controller.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.momentary.demo.constant.Constants;
import com.momentary.demo.dao.BasicRes;
import com.momentary.demo.model.product.Product;
import com.momentary.demo.model.product.ProductReq;
import com.momentary.demo.service.product.ProductService;

@RestController
@RequestMapping()
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping(value = "/product/{id}")
	public ResponseEntity<BasicRes> getProduct(HttpServletRequest http, 
			@PathVariable("id") @Valid String id)
			throws Exception {
		BasicRes res = new BasicRes();
		Product product = productService.getProduct(id);
		
		if (product != null) {
			res.setData(product);
			res.setMsg(Constants.RES_TYPE.SUCCESS.getMsg());
			res.setCode(Constants.RES_TYPE.SUCCESS.getCode());
		} else {
			res.setMsg(Constants.RES_TYPE.NOTFOUND.getMsg());
			res.setCode(Constants.RES_TYPE.NOTFOUND.getCode());
		}
		
		return ResponseEntity.ok(res);
	}
	
	@PostMapping(value = "/product")
	public ResponseEntity<BasicRes> newProduct(HttpServletRequest http,
		 @Valid @RequestBody ProductReq productReq)
			throws Exception {
		BasicRes res = new BasicRes();
		
		if(productService.newProduct(productReq)) {
			res.setMsg(Constants.RES_TYPE.SUCCESS.getMsg());
			res.setCode(Constants.RES_TYPE.SUCCESS.getCode());
		} else {
			res.setMsg(Constants.RES_TYPE.FAILURE.getMsg());
			res.setCode(Constants.RES_TYPE.FAILURE.getCode());
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(res);
	}


	@GetMapping(value = "/products")
	public ResponseEntity<BasicRes> getProducts(HttpServletRequest http) throws Exception {
		BasicRes res = new BasicRes();
		try {
			List<Product> products = productService.getProducts();
			
			res.setData(products);
			res.setMsg(Constants.RES_TYPE.SUCCESS.getMsg());
			res.setCode(Constants.RES_TYPE.SUCCESS.getCode());
		} catch (Exception ignore) {}
		
		return ResponseEntity.ok().body(res);
	}

}
