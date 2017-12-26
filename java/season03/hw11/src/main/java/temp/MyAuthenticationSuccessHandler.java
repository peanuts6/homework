package temp;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

//@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	@Override
    public void onAuthenticationSuccess(
                HttpServletRequest request, 
                HttpServletResponse response, 
                Authentication authentication) throws IOException, ServletException {

        if( !(authentication instanceof MyAuthenticationToken) ) {
            return;
        }
        
        MyAuthenticationToken jwtAuthenticaton = (MyAuthenticationToken) authentication;
        
        Cookie sessionCookie = new Cookie( "JSESSIONID", jwtAuthenticaton.getToken() );
        response.addCookie( sessionCookie );
        response.setHeader("Authorization", jwtAuthenticaton.getToken());
        
      //clearAuthenticationAttributes(request);
        
        super.onAuthenticationSuccess( request, response, authentication );
	}
}
