package edu.ldcollege.ctrl;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.ldcollege.utils.ResponseEntity;

@RestController
public class WelcomeController {
	
	@RequestMapping(value="hello", method=RequestMethod.GET)
	public ResponseEntity hello(){
		return new ResponseEntity(HttpStatus.OK.value(),new String("2"),"hello");
	}
}
