package com.yahaha.cloud.signup.runner;


import com.yahaha.cloud.core.util.EncryptionUtil;
import com.yahaha.cloud.core.util.RedisTemplateUtil;
import com.yahaha.cloud.signup.service.IRSAService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

@Component
public class RSAGeneratorRunner implements CommandLineRunner {

	private final IRSAService rsaService;

	public RSAGeneratorRunner(IRSAService rsaService) {
		this.rsaService = rsaService;
	}

	@Override
	public void run(String... args) throws Exception {
		rsaService.createKeyPair();
	}
}
