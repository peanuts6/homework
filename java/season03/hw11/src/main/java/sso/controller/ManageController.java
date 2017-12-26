package sso.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manage")
public class ManageController {

	@RequestMapping(value="api",method = RequestMethod.GET)
	public String manage(){
		return "this is a manage role";
	}

	@RequestMapping(value="api/op_createuser",method = RequestMethod.POST)
	public String create(){
		return "op_createuser role";
	}

	@RequestMapping(value="api/op_createuser/{id}",method = RequestMethod.GET)
	public String get(){
		return "op_createuser role";
	}

	@RequestMapping(value="api/op_createuser/{id}",method = RequestMethod.PUT)
	public String update(){
		return "op_createuser role";
	}

	@RequestMapping(value="api/op_createuser/{id}",method = RequestMethod.DELETE)
	public String delete(){
		return "op_createuser role";
	}
}
