package com.yahaha.cloud.signup.service.impl;

import com.yahaha.cloud.core.exception.BusinessException;
import com.yahaha.cloud.core.util.EncryptionUtil;
import com.yahaha.cloud.core.util.RedisTemplateUtil;
import com.yahaha.cloud.signup.service.IRSAService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class RSAServiceImpl implements IRSAService {
	@Value("${encrypt.public-key-name}")
	private String publicKeyName;

	@Value("${encrypt.private-key-name}")
	private String privateKeyName;

	private final String HASH_NAME = "signup-keypair";

	private AtomicBoolean isCreatingKeyPair = new AtomicBoolean(false);

	private final Logger logger = LoggerFactory.getLogger(RSAServiceImpl.class);

	@Override
	@Async("signupThreadPool")
	public void createKeyPair() throws Exception {
		logger.info("========start create key pair and put them to redis=======");
		KeyPair kp = EncryptionUtil.RSA.generateKeyPair();
		String publicKey = EncryptionUtil.RSA.getPublicKeyString(kp);
		String privateKey = EncryptionUtil.RSA.getPrivateKeyString(kp);
		RedisTemplateUtil.hashSet(HASH_NAME, publicKeyName, publicKey);
		RedisTemplateUtil.hashSet(HASH_NAME, privateKeyName, privateKey);
		logger.info("========end create key pair and put them to redis=======");
	}

	private Map<String, String> getKeyPairMap() {
		String publicKey = (String) RedisTemplateUtil.hashGet(HASH_NAME, publicKeyName);
		String privateKey = (String) RedisTemplateUtil.hashGet(HASH_NAME, privateKeyName);
		Map<String, String> map = new HashMap<>();
		map.put(publicKeyName, publicKey);
		map.put(privateKeyName, privateKey);
		return map;
	}

	@Override
	public String getPublicKey() throws Exception {
		String publicKey = getKeyPairMap().get(publicKeyName);
		if (publicKey == null) {
			if (isCreatingKeyPair.compareAndSet(false, true)) {
				createKeyPair();
			}
			throw new BusinessException("系统繁忙，请稍后再试");
		}
		return publicKey;
	}

	@Override
	public String getPrivateKey() throws Exception {
		String privateKey = getKeyPairMap().get(privateKeyName);
		if (privateKey == null) {
			if (isCreatingKeyPair.compareAndSet(false, true)) {
				createKeyPair();
			}
			throw new BusinessException("系统繁忙，请稍后再试");
		}
		return privateKey;
	}
}
