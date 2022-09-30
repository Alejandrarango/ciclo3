package com.motocycle.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
	
	int status;
	String message;
	Object result;
}
