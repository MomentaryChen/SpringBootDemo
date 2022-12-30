package com.momentary.demo.controller.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping()
public class ProductController {

	@Autowired
	ProductService productService;

	@Operation(summary = "拿取單一商品資訊", description = "透過商品ID拿取商城中特定商品資訊")
	@GetMapping(value = "/product/{id}")
	public ResponseEntity<BasicRes> getProduct(HttpServletRequest http, 
			@Parameter(description = "商品ID") @PathVariable("id") @Valid String id)
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
	
	@Operation(summary = "新增商品", description = "創造新的商品到商品清單中", 
		responses = {
			@ApiResponse(responseCode = "200", description = "Create product successfully"),
			@ApiResponse(responseCode = "403", description = "Only authenticated user can create product", 
						content = @Content(schema = @Schema(implementation = BasicRes.class)))
		}
	)
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


	@Operation(summary = "全部商品資訊", description = "拿取商城中所有的商品資訊")
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
