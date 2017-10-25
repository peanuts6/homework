package edu.ldcollege.utils;

public class ResponseHandler extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ResponseEntity responseEntity;
	
	public ResponseHandler(){
		
	}
	public ResponseHandler(ResponseEntity responseEntity){
		this.responseEntity = responseEntity;
	}
	
	public int getResponseCode(){
		return this.responseEntity.getResponseCode();
	}
	public String getResponseMsg(){
		return this.responseEntity.getResponseMsg();
	}
	
	public void setResponseEntity(ResponseEntity responseEntity){
		this.responseEntity = responseEntity;
	}
	
}
