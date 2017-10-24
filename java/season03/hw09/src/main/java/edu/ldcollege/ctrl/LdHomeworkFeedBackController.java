package edu.ldcollege.ctrl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.ldcollege.domain.LdHomeWork;
import edu.ldcollege.domain.LdHomeWorkFB;
import edu.ldcollege.service.LdHomeWorkService;
import edu.ldcollege.utils.ResponseEntity;

@RestController
@RequestMapping(value="/ldhomeworkfb")
public class LdHomeworkFeedBackController {
	Logger logger = LoggerFactory.getLogger(LdHomeworkUploadController.class);
	
	@Autowired
	LdHomeWorkService ldHomeWorkService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity hello(){
		return new ResponseEntity(HttpStatus.OK.value(),"hello feedback","success");
	}
	
	@RequestMapping(value = "feedback", 
		method = RequestMethod.POST,
		consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity feedback(@RequestBody LdHomeWorkFB fb){
		logger.info("feedback homework " + "");
		try {
			ldHomeWorkService.feedback(fb);
			return new ResponseEntity(HttpStatus.OK.value(),null,"feedback success");
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE.value(),null,"error");
		}
	}
	
	@RequestMapping(value = "feedback", 
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity update(@RequestBody LdHomeWorkFB fb){
			logger.info("feedback homework " + "");
			try {
				ldHomeWorkService.updateFeedBack(fb);
				return new ResponseEntity(HttpStatus.OK.value(),null,"feedback success");
			} catch (Exception e) {
				return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE.value(),null,"error");
			}
	}
	
	// 某一作业所有评价
	@RequestMapping(value="list",
		method = RequestMethod.GET)
	public ResponseEntity getList(Integer hwid){
		List<LdHomeWorkFB> hws = ldHomeWorkService.getAllFeedBacks(hwid);
		return new ResponseEntity(HttpStatus.OK.value(),hws,"success");
	}
}
