package sso.model;

import java.io.Serializable;

public class MyUser implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String username;
	private String password;
	private Boolean enabled;

public MyUser() {

	}
	public MyUser(MyUser user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.enabled = user.getEnabled();
	}

	public Integer getId() {
        return id;
    }
	public void setId(Integer id) {
        this.id = id;
    }

	public String getUsername() {
        return username;
    }
	public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

	public String getPassword() {
        return password;
    }
	public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

	public Boolean getEnabled() {
        return enabled;
    }
	public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}
