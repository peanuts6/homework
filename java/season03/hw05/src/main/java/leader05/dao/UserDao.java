package leader05.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import leader05.domain.User;
@Lazy
@Order(10) 
@Component("userDao")
public class UserDao implements AbstractDAO<User> {
	public UserDao()
	{
		System.out.println(this+" order "+10);
	}
}
