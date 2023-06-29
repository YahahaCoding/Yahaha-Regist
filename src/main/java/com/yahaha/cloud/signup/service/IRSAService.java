package com.yahaha.cloud.signup.service;

import java.util.Map;

public interface IRSAService {
	void createKeyPair() throws Exception;

	String getPublicKey() throws Exception;

	String getPrivateKey() throws Exception;
}
