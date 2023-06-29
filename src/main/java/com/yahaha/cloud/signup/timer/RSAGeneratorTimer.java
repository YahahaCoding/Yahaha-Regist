package com.yahaha.cloud.signup.timer;

import com.yahaha.cloud.signup.service.IRSAService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RSAGeneratorTimer {
	private final IRSAService rsaService;

	public RSAGeneratorTimer(IRSAService rsaService) {
		this.rsaService = rsaService;
	}

	@Scheduled(cron = "${jobs.recreate-key-pair-job.cron:0 0/30 * * * ?}")
	public void recreateKeyPair() throws Exception {
		rsaService.createKeyPair();
	}
}
