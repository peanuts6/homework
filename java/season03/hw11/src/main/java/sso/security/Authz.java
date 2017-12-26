package sso.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import sso.domain.*;

@Component
public class Authz {
	public boolean check(String userId, User user) {
		return userId.equals(user.getUsername());
	}
}
