package com.momentary.demo.model.product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProductReq  {
	
	@NotEmpty
	String name;
	
	@NotNull
	@Min(value = 1, message = "Price should be greater than 0")
	int price;
	
	public ProductReq(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

}
