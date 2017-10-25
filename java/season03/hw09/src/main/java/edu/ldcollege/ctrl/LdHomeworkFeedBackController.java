package edu.ldcollege.ctrl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
		method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity feedback(Integer id,Integer myid,
			Integer userid,
			Integer homeworkid,String levelflag, 
			Integer starcount,Integer negativecount,
			String mark){
		logger.info("feedback homework " + homeworkid+" "+id);
		try {
			LdHomeWork old = ldHomeWorkService.getHomework(id);
			LdHomeWork hw = new LdHomeWork();
			hw.setId(homeworkid);
			hw.setStarcount(starcount+old.getStarcount());
			hw.setNegativecount(negativecount+old.getNegativecount());
			ldHomeWorkService.update(hw);
			
			LdHomeWorkFB hwfb = new LdHomeWorkFB();
			hwfb.setUserid(userid);
			hwfb.setMyid(myid);
			hwfb.setHomeworkid(homeworkid);
			hwfb.setLevelflag(levelflag);
			hwfb.setMark(mark);
			ldHomeWorkService.feedback(hwfb);
			
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
	@RequestMapping(value="list/{id}",
		method = RequestMethod.GET)
	public ResponseEntity getList(@PathVariable Integer id){
		List<LdHomeWorkFB> hws = ldHomeWorkService.getAllFeedBacks(id);
		return new ResponseEntity(HttpStatus.OK.value(),hws,"success");
	}
}
