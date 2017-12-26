package sso.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kingcenter")
public class KingCenterController {
	Logger logger = LoggerFactory.getLogger(KingCenterController.class);

	@RequestMapping(value="api/{id}",method = RequestMethod.GET)
	public String get(@PathVariable Integer id){
		logger.info("kingcenter/api/"+id);
		return "kingcenter your id is "+id;
	}

}
