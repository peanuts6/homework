package leader.sso.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

public class MyAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	
	protected MyAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
		super(requiresAuthenticationRequestMatcher);
		// TODO Auto-generated constructor stub
		setAuthenticationManager( super.getAuthenticationManager() );
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		// TODO Auto-generated method stub
		
		// 取Cookie 
		String token = "";
		Cookie[] cookies = request.getCookies();
		if( cookies == null || cookies.length < 1 ) {
			throw new AuthenticationServiceException( "Invalid Token" );
		}
		
		Cookie sessionCookie = null;
		for( Cookie cookie : cookies ) {
			if( ( "JSESSIONID" ).equals( cookie.getName() ) ) {
				sessionCookie = cookie;
				break;
			}
		}
		
		// 取不到再取Header
		if( sessionCookie == null || StringUtils.isEmpty( sessionCookie.getValue() ) ) {
			throw new AuthenticationServiceException( "Invalid Token" );
		}
		return null;
	}

}
