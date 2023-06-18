package com.tradebulls.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)  //id is incorrect
public class CartException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public CartException(String message){
		super(message); //calling
	}

}