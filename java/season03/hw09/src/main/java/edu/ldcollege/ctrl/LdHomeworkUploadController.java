package edu.ldcollege.ctrl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.ldcollege.domain.LdHomeWork;
import edu.ldcollege.service.LdHomeWorkService;
import edu.ldcollege.utils.ResponseEntity;

@RestController
@RequestMapping(value="/ldhomework")
public class LdHomeworkUploadController {
	Logger logger = LoggerFactory.getLogger(LdHomeworkUploadController.class);
	
	@Autowired
	LdHomeWorkService ldHomeWorkService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity hello(){
		return new ResponseEntity(HttpStatus.OK.value(),"hello homework upload","success");
	}
	
	// 上传作业---新增
	@RequestMapping(value = "upload", 
		method = RequestMethod.POST)
	public ResponseEntity upload(LdHomeWork hw) {
		logger.info("upload homework " + hw);
		if(hw.getUserid() == null){
			return new ResponseEntity(700,null,"用户参数错误");
		}
		if(null == hw.getClassid()){
			return new ResponseEntity(700,null,"班级参数错误");
		}
		if(null == hw.getLessionid()){
			return new ResponseEntity(700,null,"课程参数错误");
		}
		if(StringUtils.isEmpty(hw.getHomeworkfilename())){
			return new ResponseEntity(700,null,"作业名称参数错误");
		}
		MultipartFile file = hw.getFile();
		if(file==null){
			return new ResponseEntity(700,null,"作业文件参数错误");
		}
		// <=15MB
		if(file.getSize()>1048576*5){
			return new ResponseEntity(701,null,"文件超出大小限制");
		}
		hw.setHomeworkfilepath("/files/"+file.getOriginalFilename());
		
		try {
			ldHomeWorkService.deleteHomework(hw.getUserid(), hw.getClassid(), hw.getLessionid());
			ldHomeWorkService.upload(hw);
			ldHomeWorkService.saveUploadedFile(hw.getFile());
			return new ResponseEntity(HttpStatus.OK.value(),null,"upload success");
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE.value(),null,""+e.getMessage());
		}
	}
	
	// 更换作业
	@RequestMapping(value = "upload", 
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity update(@RequestBody LdHomeWork hw) {
			logger.info("upload homework " + hw);
			
			
			try {
				ldHomeWorkService.update(hw);
				return new ResponseEntity(HttpStatus.OK.value(),null,"update success");
			} catch (Exception e) {
				return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE.value(),null,""+e.getMessage());
			}
	}
	
	
	
	// 获取某一个作业
	@RequestMapping(value="get",
		method=RequestMethod.GET)
	public ResponseEntity getHomework(Integer id){
		try {
			LdHomeWork hw = ldHomeWorkService.getHomework(id);
			return new ResponseEntity(HttpStatus.OK.value(),hw,"success");
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE.value(),null,""+e.getMessage());
		}
	}
	
	// 获取某一班级所有作业
	@RequestMapping(value="list/{classid}",
			method=RequestMethod.GET)
	public ResponseEntity getHomeworkOfClass(@PathVariable Integer classid){
			try {
				List<LdHomeWork> hws = ldHomeWorkService.getHomeWorksOfClass(classid);
				return new ResponseEntity(HttpStatus.OK.value(),hws,"success");
			} catch (Exception e) {
				return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE.value(),null,""+e.getMessage());
			}
	}
	
	// 获取班级所有作业
	@RequestMapping(value="list",
		method=RequestMethod.GET)
	public ResponseEntity getAllHomework(){
		try {
			List<LdHomeWork> hws = ldHomeWorkService.getAllHomeWorks();
			return new ResponseEntity(HttpStatus.OK.value(),hws,"success");
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE.value(),null,""+e.getMessage());
		}
	}
	
}
