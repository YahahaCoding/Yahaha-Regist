package com.yahaha.cloud.signup.service.impl;

import com.yahaha.cloud.core.exception.BusinessException;
import com.yahaha.cloud.core.exception.SystemRunException;
import com.yahaha.cloud.core.util.RedisTemplateUtil;
import com.yahaha.cloud.core.util.SnowFlakeUtil;
import com.yahaha.cloud.signup.service.IRSAService;
import com.yahaha.cloud.signup.service.ISessionService;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements ISessionService {
	private final IRSAService rsaService;

	private static final String SNAPSHOT_PUBLIC_PREFIX = "signup-snapshot-public:";

	private static final String SNAPSHOT_PRIVATE_PREFIX = "signup-snapshot-private:";

	public SessionServiceImpl(IRSAService rsaService) {
		this.rsaService = rsaService;
	}

	@Override
	public String createSessionId() throws Exception {
		String sessionId = SnowFlakeUtil.nextId();
		snapshotKeyPair(sessionId);
		return sessionId;
	}

	private void snapshotKeyPair(String sessionId) throws Exception {
		RedisTemplateUtil.setKeyWithExpireSeconds(SNAPSHOT_PUBLIC_PREFIX + sessionId, rsaService.getPublicKey(), 3600);
		RedisTemplateUtil.setKeyWithExpireSeconds(SNAPSHOT_PRIVATE_PREFIX + sessionId, rsaService.getPrivateKey(), 3600);
	}

	@Override
	public String getPublicKeyBySessionId(String sessionId) {
		String r = (String) RedisTemplateUtil.getKey(SNAPSHOT_PUBLIC_PREFIX + sessionId);
		if (r == null) {
			throw new SystemRunException("页面超时，正在跳转");
		}
		return r;
	}

	@Override
	public String getPrivateKeyBySessionId(String sessionId) {
		String r = (String) RedisTemplateUtil.getKey(SNAPSHOT_PRIVATE_PREFIX + sessionId);
		if (r == null) {
			throw new SystemRunException("页面超时，正在跳转");
		}
		return r;
	}
}
