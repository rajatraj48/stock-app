package com.stock.dto;

import lombok.Data;
@Data
public class ResponseDTO {
	
	  	private String message;
	    private int statusCode;

	    public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public int getStatusCode() {
			return statusCode;
		}

		public void setStatusCode(int statusCode) {
			this.statusCode = statusCode;
		}

		public ResponseDTO(String message, int statusCode) {
	        this.message = message;
	        this.statusCode = statusCode;
	    }
}
