package com.yahaha.cloud.signup.config;

import org.apache.ibatis.plugin.Interceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
@ConfigurationProperties(prefix = "thread-pool")
public class ThreadPoolConfig {
	private Integer coreSize;

	private Integer minSize;

	private Integer maxSize;

	private Integer queueCapacity;

	private String name;

	private Boolean enable;

	public void setCoreSize(Integer coreSize) {
		this.coreSize = coreSize;
	}

	public void setMinSize(Integer minSize) {
		this.minSize = minSize;
	}

	public void setMaxSize(Integer maxSize) {
		this.maxSize = maxSize;
	}

	public void setQueueCapacity(Integer queueCapacity) {
		this.queueCapacity = queueCapacity;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Integer getCoreSize() {
		return coreSize;
	}

	public Integer getMinSize() {
		return minSize;
	}

	public Integer getMaxSize() {
		return maxSize;
	}

	public Integer getQueueCapacity() {
		return queueCapacity;
	}

	public String getName() {
		return name;
	}

	public Boolean getEnable() {
		return enable;
	}

	@Bean(name = "signupThreadPool")
	public Executor asyncServiceExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(coreSize);
		executor.setMaxPoolSize(maxSize);
		executor.setQueueCapacity(queueCapacity);
		executor.setThreadNamePrefix(name);
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		executor.initialize();
		return executor;
	}
}
