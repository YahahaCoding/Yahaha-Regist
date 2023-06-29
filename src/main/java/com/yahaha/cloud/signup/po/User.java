package com.yahaha.cloud.signup.po;

import java.io.Serial;
import java.io.Serializable;

public class User extends BaseEntity implements Serializable {
	@Serial
	private static final long serialVersionUID = -8001418941167584326L;

	private String account;

	private String password;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
