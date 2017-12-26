package sso.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sso.domain.User;
import sso.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="api/token",method=RequestMethod.GET)
	@ResponseBody
	public String token(){
		logger.info("token:");
		return "sldkjfslakjflsajdflksdjf";
	}
	
	@RequestMapping(value="api/login",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<User> login(String username,String password,String remember_me){
		logger.info("login:"+username+" "+password+" "+remember_me);
		User user = userService.getUserByName(username);
		return new ResponseEntity<User>(HttpStatus.OK);
	}
	
	@RequestMapping(value="api/logout",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> logout(){
		SecurityContextHolder.getContext().setAuthentication(null);
		return new ResponseEntity<String>("not login",HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping("api/getcookie1")
	public String hello(@CookieValue(value = "mycookie1", defaultValue = "test1") String mycookie1) {
		return "mycookie1:" + mycookie1;
	}

	@RequestMapping("api/setcookie1")
	public String setCookie(HttpServletResponse response) {
		Cookie foo = new Cookie("mycookie1", System.currentTimeMillis() + ""); // cookie
		foo.setMaxAge(1000); // set expire time to 1000 sec
		response.addCookie(foo); // put cookie in response
		return "Success";
	}
	
	@RequestMapping("api/getsession")
	public String getSession(HttpServletRequest request) {
		HttpSession ss=request.getSession();
		return "Success :"+ss;
	}
}
