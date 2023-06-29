package com.yahaha.cloud.signup.service;

public interface ISessionService {
	String createSessionId() throws Exception;

	String getPublicKeyBySessionId(String sessionId);

	String getPrivateKeyBySessionId(String sessionId);
}
