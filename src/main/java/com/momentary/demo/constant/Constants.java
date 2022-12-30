package com.momentary.demo.constant;

public class Constants {
	
	public enum RES_TYPE {
		SUCCESS("00", "成功"),
		ARG_ERROR("01", "參數輸入錯誤"),
		ARG_FORMAT_ERROR("02", "參數輸入錯誤(格式)"),
		NOTFOUND("03", "Not Found"),
		AUTHENTICATION_ERR("04", "無操做權限(帳密錯誤)"),
		AUTHORIZE_ERR("05", "無操做權限(權限錯誤)"),
		TIMEOUT("06", "操作逾時"),
		FAILURE("999", "失敗");
		
		private final String code;
		private final String msg;
		
		private RES_TYPE(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public String getCode() {
			return code;
		}

		public String getMsg() {
			return msg;
		}
	}
	
	public enum Role_Authority{
		ADMIN("admin"),
		USER("user"),
		ANONYMOUS("anonymous");
		
		private final String role;

		private Role_Authority(String role) {
			this.role = role;
		}

		public String getRole() {
			return role;
		}
	}

}
