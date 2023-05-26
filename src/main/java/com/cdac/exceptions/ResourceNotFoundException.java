package com.cdac.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter

@Setter
public class ResourceNotFoundException extends RuntimeException {

	String resourceName;
	String fieldName;
	long fieldValue;

	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) { // Passing msg to super
																							// class
	
		
		super(String.format("%s not found with this %s : %s", resourceName, fieldName, fieldValue));
		System.out.println("inside ResourceNotFoundException");
		
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	
	
	}

}
