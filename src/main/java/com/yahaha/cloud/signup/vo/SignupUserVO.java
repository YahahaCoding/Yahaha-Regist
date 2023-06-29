package com.yahaha.cloud.signup.vo;

import java.io.Serial;
import java.io.Serializable;

public class SignupUserVO implements Serializable {
	@Serial
	private static final long serialVersionUID = -4952246573373128103L;

	private String nickname;

	private String account;

	private String password;

	private String sessionId;

	public String getNickname() {
		return nickname;
	}

	public String getAccount() {
		return account;
	}

	public String getPassword() {
		return password;
	}

	public String getSessionId() {
		return sessionId;
	}
}
