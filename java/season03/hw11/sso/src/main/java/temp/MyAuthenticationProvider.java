package temp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import leader.sso.domain.User;
import leader.sso.security.MyUserDetailsService;

//被ProviderManager(->AuthenticationManager)委托完成验证的对象，获取UserDetailsService用户信息
//@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
	private final BCryptPasswordEncoder encoder;
	private final MyUserDetailsService userService;
	
	@Autowired
	public MyAuthenticationProvider(final MyUserDetailsService userService, final BCryptPasswordEncoder encoder){
		this.userService = userService;
        this.encoder = encoder;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Assert.notNull(authentication, "No authentication data provided");
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        UserDetails user =  userService.loadUserByUsername(username);
        
        // 产生一个token
        String token = ""+user.getUsername()+user.getPassword();
        
        if (password.equals(user.getPassword())) {
        //if (!encoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
        }
        
        if (user.getAuthorities() == null) throw new InsufficientAuthenticationException("User has no roles assigned");
        
        List<GrantedAuthority> authorities = user.getAuthorities().stream()
        		.map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
        		.collect(Collectors.toList());
        
//        return new MyAuthenticationToken(token,user,authorities);
        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
	
	
}
