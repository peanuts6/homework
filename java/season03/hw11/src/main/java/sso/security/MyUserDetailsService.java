package sso.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import sso.domain.User;
import sso.service.UserService;

@Component
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getUserByName(username);
		if (user == null) {
			throw new UsernameNotFoundException("Could not find user " + username);
		}
		List<GrantedAuthority> authorities =  userService.getAuthorities(username);
		user.setAuthorities(authorities);
		return new CustomUserDetails(user);
	}


	private final static class CustomUserDetails extends User implements UserDetails{
		private CustomUserDetails(User user) {
			//super(user);
		}

		@Override
		public List<GrantedAuthority> getAuthorities() {
//			List<GrantedAuthority> auths = getAuthorities();
//			return auths;
			return AuthorityUtils.createAuthorityList("ROLE_USER");
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}

		private static final long serialVersionUID = 5639683223516504866L;

	}

}
