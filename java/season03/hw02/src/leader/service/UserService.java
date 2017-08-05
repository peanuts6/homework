package leader.service;

import java.util.List;

import leader.bean.User;
import leader.bean.UserSession;

public class UserService {

	public boolean createUser(User user)
	{
		return true;
	}
	public boolean deleteUser(long userId)
	{
		return true;
	}
	public boolean disableUser(long userId)
	{
		return true;
	}
	public List<User> queryUsers(String userNamePrex,boolean onlyValidUser)
	{
		return null;
	}
	/**
	 * 如果密码不对，返回的UserSession对象里sessionId为空，客户端可以依次判断，参照UserSession.isValid方法
	 * @param userName
	 * @param md5EncodedPassword
	 * @return
	 */
	public UserSession login(String userName,String md5EncodedPassword)
	{
		return new UserSession();
	}
}
