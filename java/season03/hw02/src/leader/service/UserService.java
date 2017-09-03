package leader.service;

import java.io.*;
import java.util.*;

import leader.bean.User;
import leader.bean.UserSession;

public class UserService implements Serializable{
	private User user;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Map<String,User> users = new HashMap<>();

	public boolean createUser(User user) {
		this.user = user;
		users.put(String.valueOf(user.getUserId()),user);
		return saveToFile();
	}
	public boolean deleteUser(long userId)
	{
		User user = users.remove(String.valueOf(userId));
		return saveToFile();
	}
	public boolean disableUser(long userId)
	{
		User user = users.get(String.valueOf(userId));
		user.setEnabled(false);
		users.replace(String.valueOf(userId),user);
		return saveToFile();
	}

	public HashMap<String,User> queryUsers(String userNamePrex,boolean onlyValidUser)
	{
		return loadFile();
	}

	public boolean saveToFile(){
		try {
			out = new ObjectOutputStream(new FileOutputStream("/Users/leader/projects/homework/java/season03/hw02/user_data.txt"));
			out.writeObject(users);
			out.close();
			return true;
		}catch (IOException e){
			e.printStackTrace();
		}
		return false;
	}
	public HashMap<String,User> loadFile(){
		try {
			in = new ObjectInputStream(new FileInputStream("user_data.txt"));
			HashMap u = (HashMap<String,User>) in.readObject();
			in.close();
			return u;
		}catch (IOException | ClassNotFoundException e){
			e.printStackTrace();
		}
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
