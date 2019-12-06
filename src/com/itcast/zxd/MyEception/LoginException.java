package com.itcast.zxd.MyEception;

public class LoginException extends Exception {
	
	public LoginException(){
		
	}
	
	public LoginException(String message, Throwable couse){
		super(message, couse);
	}

	public LoginException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public LoginException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	
}
