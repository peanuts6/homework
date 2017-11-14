package leader.sso.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
	Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@RequestMapping(value="api",method=RequestMethod.GET)
	public String admin(){
		logger.info("admin/api");
		return "admin role";
	}
}
