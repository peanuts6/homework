package leader.sso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import leader.sso.domain.User;
import leader.sso.mapping.AuthorityMapper;
import leader.sso.mapping.UserMapper;

@Component
public class UserService {
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	AuthorityMapper authorityMapper;

	public User getUserByName(String username){
		return userMapper.getUserByName(username);
	}
	
	public User get(Integer id){
		return userMapper.get(id);
	}
	public void insert(User record){
		userMapper.insert(record);
	}
	public void update(User record){
		userMapper.update(record);
	}
	public void delete(Integer id){
		userMapper.delete(id);
	}
	
	public List<GrantedAuthority> getAuthorities(String username){
		return null;
//		return authorityMapper.getAllAuthorities(username);
	}

}
