package leader.sso.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import leader.sso.domain.User;

public class MyUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 
//		User user = userRepository.findByName(username);
//		if (user == null) {
//			throw new UsernameNotFoundException("Could not find user " + username);
//		}
//		return new CustomUserDetails(user);
		return null;
	}
	
	private final static class CustomUserDetails extends User implements UserDetails{
		private CustomUserDetails(User user) {
			super(user);
		}
		
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
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
