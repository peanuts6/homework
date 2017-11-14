package leader.sso.utils;

import org.springframework.http.HttpStatus;

public class ResponseEntity<T> {
	private T obj;
	private HttpStatus status;
	private String msg;
	
	public ResponseEntity(T o,HttpStatus ok){
		this.obj = o;
		this.status = ok;
	}
	public ResponseEntity(T o,HttpStatus status,String msg){
		this.obj = o;
		this.status = status;
		this.msg = msg;
	}
}
