package com.cdac.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cdac.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse	> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
	System.out.println("inside global exception ");
		String message = ex.getMessage();
		ApiResponse apiResponse=new ApiResponse(message,false);
		
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
//	return new ResponseEntity(Map.of(message,""),HttpStatus.NOT_FOUND);
	
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgNotValidException(MethodArgumentNotValidException ex){
	 
		System.out.println("inside methodarg not valid exception hander");
		Map<String,String> resp=new HashMap<>();
	 //fetch all the exception message one by one
	 
	 
	 ex.getBindingResult().getAllErrors().forEach((error)->{
		 String fieldName = ((FieldError)error).getField();
		 String message=error.getDefaultMessage();
		 resp.put(fieldName, message);
		 
	 });
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);

	}
	
	//this method will handle HttpRequestMethodNotSupportedException 
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Map<String,String>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex){
		
		Map<String,String> resp=new HashMap<>();
		
		
		return null;
		
		
	}
}
