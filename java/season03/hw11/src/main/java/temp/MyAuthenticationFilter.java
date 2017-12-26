package temp;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

public class MyAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	
	protected MyAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
		super(requiresAuthenticationRequestMatcher);
		setAuthenticationManager( super.getAuthenticationManager() );
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		// 取Cookie 
		String token = "";
		Cookie[] cookies = request.getCookies();
		
		Cookie sessionCookie = null;
		if(!( cookies == null || cookies.length < 1 )) {
			for( Cookie cookie : cookies ) {
				if( ( "JSESSIONID" ).equals( cookie.getName() ) ) {
					sessionCookie = cookie;
					break;
				}
			}
		}
		
		// 取不到再取Header
		if( sessionCookie == null || StringUtils.isEmpty( sessionCookie.getValue() ) ) {
			token = request.getHeader("Authorization"); // "Imking "
		} else{
			token = sessionCookie.getValue();
		}
		
//		Authentication authentication = (Authentication)new MyAuthenticationToken();
//		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	@Override
	public void doFilter(ServletRequest req,ServletResponse res,FilterChain chain) throws IOException, ServletException{
		super.doFilter(req, res, chain);
	}

}
