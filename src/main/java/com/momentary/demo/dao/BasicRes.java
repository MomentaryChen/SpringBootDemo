package com.momentary.demo.dao;

import io.swagger.v3.oas.annotations.media.Schema;

public class BasicRes {
	@Schema(description = "回傳的狀態")
	String code;
	
	@Schema(description = "回傳的訊息")
	String msg;
	
	@Schema(description = "回傳的資訊")
	Object data;
	
	public BasicRes() {};
	
	public BasicRes(String code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
	
	

}
