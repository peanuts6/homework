package edu.ldcollege.utils;


public class ResponseEntity {
	int responseCode;
	String responseMsg;
	Object body;
	
	
	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public ResponseEntity(int rescode,Object body,String resmsg){
		this.responseCode = rescode;
		this.body = body;
		this.responseMsg = resmsg;
	}
	
}
