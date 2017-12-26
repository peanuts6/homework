package temp;

import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;


public class MyAuthenticationToken extends AbstractAuthenticationToken {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	User principal;
	String token;
	
	public MyAuthenticationToken(String token,User principal,List<GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
		this.token = token;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return this.principal;
	}
	
	public void setToken(String token){
		this.token = token;
	}
	public String getToken(){
		return this.token;
	}

}
